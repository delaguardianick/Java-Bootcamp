package Nickdlg.GTN.service;

import Nickdlg.GTN.data.GTNDao;
import Nickdlg.GTN.models.Game;
import Nickdlg.GTN.models.Round;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class GTNInMemoryService implements GTNService {
    

    private final GTNDao dao;
    
    public GTNInMemoryService(GTNDao dao){
        this.dao = dao;
    }
    
    /*
    Adds a new Game object to the database,
    The gameID is assigned in the dao
    @returns gameID
    */
    @Override
    public int createGame(String solution){
        Game newGame = new Game(solution);
        
        dao.addGame(newGame);
        int gameID = newGame.getGameID();
        
        return gameID;
    }

    /*
    Generates a random 4 digit solution with all unique digits
    @returns solution as string
    */
    @Override
    public String generateSolution() {
        String solution = "";
        while (solution.length() < 4) {
            String newDigit = String.valueOf(generateNumInRange(0, 9));
            
            if (solution.contains(newDigit))
            {
                continue;
            }
            solution += newDigit;
        }
        return solution;
    }
    
    /*
    Helper for generateSolution()
    @returns random number from min to max inclusive
    */
    @Override
    public int generateNumInRange(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    /*
    Called by POST ("/guess")
    Given Round with only gameID and a guess,
    Compares guess to game solution and returns round result
    Checks if game if solution is guessed, finishes game
    Calls dao to add round to the database
    */
    @Override
    public void makeGuess(Round currRound) {
        
        // Get the Game the round belongs to
        int gameID = currRound.getGameID();
        Game currGame = dao.getGame(gameID);
        
        if (currGame.getFinished()){
            currRound.setLastRound(Boolean.TRUE);
//            Can throw exception saying game already finished,
//            and no more rounds playable
        }
        
        // Generate round results, add to round object
        calculateRoundResult(currRound, currGame);
        // Change to finished 
        dao.updateGameStatus(currGame);

        
//      Add round to Database
        dao.addRound(currRound);
        
//      Add roundID to GameRound table, associating it with a gameID
        int roundID = currRound.getRoundID();
        dao.addGameRound(gameID, roundID);
        
    }
    
    /*
    @params round and game it belongs to
    Checks for exact and partial matches
    Adds result to round object 
    */
    @Override
    public void calculateRoundResult(Round currRound, Game currGame){
        
        String guess = currRound.getGuess();
        String solution = currGame.getSolution();
        int exact = 0;
        int partial = 0;
        
        for (int i=0; i < solution.length(); i++){
            char solChar = solution.charAt(i);
            char guessChar = guess.charAt(i);
            
            if (solChar == guessChar){
                exact++;
                continue;
            }
            
            if (solution.indexOf(guessChar) != -1){
                partial++;
                continue;
            }
        }
        currRound.setExactMatches(exact);
        currRound.setPartialMatches(partial);
        currRound.setRoundResult(String.format("e:%dp:%d", exact, partial));
        
        if (currRound.getExactMatches() == currRound.getGuess().length())
            {
                currRound.setLastRound(true);
                currGame.setFinished(true);
            }
    }
    
    /*
    @returns all games
    */
    @Override
    public List<Game> getAllGames(){
        return dao.getAllGames();
    }
    
    /*
    searches game by gameID in database
    @returns searched game
    */
    @Override
    public Game getGame(int gameID){
        return dao.getGame(gameID);
    }

    /*
    Similar to getGame, but censors the solution if the game is unfinished
    */
    @Override
    public Game getGameToDisplay(int gameID) {
        return dao.getGameToDisplay(gameID);
    }
    
    /*
    @returns a list of all rounds for a specific gameID
    */
    @Override
    public List<Round> getAllRoundsForGame(int gameID){
        return dao.getAllRoundsForGame(gameID);
    }

    
}
