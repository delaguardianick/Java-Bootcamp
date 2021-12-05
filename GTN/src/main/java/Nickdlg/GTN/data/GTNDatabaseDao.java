/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.data;

import Nickdlg.GTN.models.Game;
import Nickdlg.GTN.models.Round;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
//        return newGame;
    }

    @Override
    public Game getGame(int gameID) {
        final String sql = "SELECT gameID, solution, finished "
                + "FROM game WHERE gameID = ?";
        
        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameID);

    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT CASE"
                + "WHEN finished = FALSE THEN gameID, finished"
                + "WHEN finished = TRUE THEN gameID, solution, finished"
                + "END"
                + "from Game";
        
        return jdbcTemplate.query(sql, new GameMapper());
    }
    
    @Override
    public void addRound(Round newRound){
        final String sql = "INSERT INTO round(guess, exactMatches, "
                + "partialMatches, LastRound)"
                + "VALUES(?,?,?,?);";
        
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, newRound.getGuess());
            statement.setInt(2, newRound.getExactMatches());
            statement.setInt(3, newRound.getPartialMatches());
//            statement.setDate(2, Date.valueOf(newRound.getTime()));
            statement.setBoolean(4, newRound.getLastRound());

            return statement;

        }, keyHolder);
        
        newRound.setRoundID(keyHolder.getKey().intValue());
//        return newRound;
    }
    
    @Override 
    public void addGameRound(int gameID, int roundID){
        final String sql = "INSERT INTO GameRound(gameID, roundID)"
                + "VALUES(?,?);";
        
         GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, gameID);
            statement.setInt(2, roundID);

            return statement;

        }, keyHolder);
    }
    
    private static final class GameMapper implements RowMapper<Game> {
        
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException{
            Game newGame = new Game();
            newGame.setGameID(rs.getInt("gameID"));
            newGame.setSolution(rs.getString("solution"));
            newGame.setFinished(rs.getBoolean("finished"));
            return newGame;
        }
    }
    
}
