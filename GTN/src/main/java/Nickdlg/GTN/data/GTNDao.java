/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.data;

import Nickdlg.GTN.models.Game;
import java.util.List;

/**
 *
 * @author Gordak
 */
public interface GTNDao {
    
    public void addGame(Game newGame);
    
    public Game getGame(int gameId);
    
    public List<Game> getAllGames();
}
