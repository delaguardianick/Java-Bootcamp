/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.data;

import Nickdlg.GTN.models.Game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gordak
 */
@Repository
public class GTNInMemoryDao implements GTNDao {
    
    private Map<Integer, Game> games = new HashMap<>();

    public void addGame(Game newGame){
        games.put(newGame.getGameID(), newGame);
    }
    
    public List<Game> getAllGames(){
        return new ArrayList<Game>(games.values());
    }
    
    public Game getGame(int gameId){
        
        Game currGame= games.get(gameId);
        return currGame;
    }
}
