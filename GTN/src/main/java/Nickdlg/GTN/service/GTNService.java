package Nickdlg.GTN.service;

import Nickdlg.GTN.models.Game;
import Nickdlg.GTN.models.Round;
import java.util.List;


public interface GTNService {

    public String generateSolution();
    
    public int generateNumInRange(int min, int max);

    public int createGame(String solution);

    public void makeGuess(Round currRound);
    
    public List<Game> getAllGames();

    public Game getGame(int gameID);

    public Game getGameToDisplay(int gameID);
    
//    public LocalDateTime convertStringToDate(String dateString);

    public List<Round> getAllRoundsForGame(int gameID);
    
    public void calculateRoundResult(Round currRound, Game currGame);


}
