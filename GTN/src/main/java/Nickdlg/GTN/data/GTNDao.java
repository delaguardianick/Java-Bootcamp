/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.data;

import Nickdlg.GTN.models.Game;
import Nickdlg.GTN.models.Round;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Gordak
 */
public interface GTNDao {
    
    public void addGame(Game newGame);
    
    public Game getGame(int gameId);
    
    public List<Game> getAllGames();

    public void addRound(Round currRound);
    
    public void addGameRound(int roundID, int gameID);
    
}
