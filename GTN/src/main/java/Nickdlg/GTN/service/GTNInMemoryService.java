/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.service;

import Nickdlg.GTN.data.GTNDao;
import Nickdlg.GTN.models.Game;
import Nickdlg.GTN.models.Round;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gordak
 */
@Repository
public class GTNInMemoryService implements GTNService {
    

    private final GTNDao dao;
    
    public GTNInMemoryService(GTNDao dao){
        this.dao = dao;
    }
    
    @Override
    public int createGame(String solution){
        Game newGame = new Game(solution);
        int gameID = newGame.getGameID();
        
        dao.addGame(newGame);
        
        return gameID;
    }

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
    
    @Override
    public int generateNumInRange(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void makeGuess(Round currRound) {
        int gameID = currRound.getGameID();
        Game currGame = dao.getGame(gameID);
        
        String solution = currGame.getSolution();
        currRound.setSolution(solution);
        
        calculateRoundResult(currRound);
        
//        Add round to game
        dao.addRound(currRound);
        
        int roundID = currRound.getRoundID();
        dao.addGameRound(gameID, roundID);
        
//        if (isGameFinished(currRound)){
//            currGame.setFinished(Boolean.TRUE);
//        }
    }
    
    public void calculateRoundResult(Round currRound){
        
        String guess = currRound.getGuess();
        String solution = currRound.getSolution();
        
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
        
    }
    
    public Boolean isGameFinished(Round currRound){
        if (currRound.getExactMatches() == currRound.getSolution().length()){
            currRound.setLastRound(Boolean.TRUE);
            return true;
        }
        return false;
    }
    
    @Override
    public List<Game> getAllGames(){
        return dao.getAllGames();
    }
    
    @Override
    public Game getGame(int gameID){
        return dao.getGame(gameID);
    }
    
}
