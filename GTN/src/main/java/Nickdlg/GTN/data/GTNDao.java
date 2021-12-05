package Nickdlg.GTN.data;

import Nickdlg.GTN.models.Game;
import Nickdlg.GTN.models.Round;
import java.util.List;

public interface GTNDao {
    
    public void addGame(Game newGame);
    
    public Game getGame(int gameId);
    
    public Game getGameToDisplay(int gameID);
    
    public List<Game> getAllGames();

    public void addRound(Round currRound);
    
    public void addGameRound(int roundID, int gameID);

    public boolean updateGameStatus(Game currGame);
    
    public List<Round> getAllRoundsForGame(int gameID);

    
}
