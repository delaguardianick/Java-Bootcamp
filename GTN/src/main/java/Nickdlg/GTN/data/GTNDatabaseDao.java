/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.data;

import Nickdlg.GTN.models.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;


/**
 *
 * @author Gordak
 */

@Repository
public class GTNDatabaseDao implements GTNDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GTNDatabaseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addGame(Game newGame) {
        final String sql = "INSERT INTO game(solution, finished)"
                + "VALUES(?,?);";
        
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, newGame.getSolution());
            statement.setBoolean(2, newGame.getFinished());

            return statement;

        }, keyHolder);
        
        newGame.setGameID(keyHolder.getKey().intValue());
//        MIGHT CAUSE PROBLEMS ^^
    }

    @Override
    public Game getGame(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT CASE"
                + "WHEN finished = FALSE THEN gameID, finished"
                + "WHEN finished = TRUE THEN gameID, solution, finished"
                + "END"
                + "from Game";
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}
