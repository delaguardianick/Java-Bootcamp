package Nickdlg.GTN.data;

import Nickdlg.GTN.models.Game;
import Nickdlg.GTN.models.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;


@Repository
public class GTNDatabaseDao implements GTNDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GTNDatabaseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    Adds game to database and generates gameID according to records
    */
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
    }
    
    /*
    searches a gameID in database
    @returns requested Game object
    */
    @Override
    public Game getGame(int gameID) {
        final String sql = "SELECT gameID, solution, finished "
                + "FROM game WHERE gameID = ?";
        
        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameID);
    }
    /*
    searches a gameID in database
    if game is unfinished, solution is hidden (null)
    @returns requested Game object 
    */
    @Override
    public Game getGameToDisplay(int gameID){
         final String sql = "SELECT gameID, finished, "
                 + "CASE WHEN finished = TRUE THEN solution END as Solution "
                + "FROM game WHERE gameID = ?";
        
        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameID);
    }

    /*
    @returns all games 
    if game is unfinished, solution is hidden (null)
    */
    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT GameID, finished, "
                + "CASE WHEN finished = TRUE THEN solution END as Solution "
                + "from Game";
        
        return jdbcTemplate.query(sql, new GameMapper());
    }
    
    /*
    Adds a round to database
    Sets roundID according to next available roundID in database
    */
    @Override
    public void addRound(Round newRound){
        final String sql = "INSERT INTO round(guess, exactMatches, "
                + "partialMatches, LastRound, roundResult)"
                + "VALUES(?,?,?,?,?);";
        
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, newRound.getGuess());
            statement.setInt(2, newRound.getExactMatches());
            statement.setInt(3, newRound.getPartialMatches());
            statement.setBoolean(4, newRound.getLastRound());
            statement.setString(5, newRound.getRoundResult());

            return statement;

        }, keyHolder);
        
        newRound.setRoundID(keyHolder.getKey().intValue());
//        return newRound;
    }
    
    /*
    Adds roundID and gameID to translation table
    It associates each round with its corresponding game
    */
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
    
    /*
    Updates the 'finished' column to 1 if game is finished
    @returns true if succesfully updated
    */
    @Override
    public boolean updateGameStatus(Game currGame){
        
        final String sql = "UPDATE game SET "
                + "finished = ? "
                + "WHERE gameID = ?";
        
        return jdbcTemplate.update(sql,
                currGame.getFinished(),
                currGame.getGameID()) > 0;
    }
    
    /*
    Uses intermediary table to display all the rounds for a gameID
    @returns all rounds of a game
    */
    @Override
    public List<Round> getAllRoundsForGame(int gameID){
        final String sql = "select * "
                + "from game "
                + "inner join gameround on game.gameID = gameround.gameID "
                + "inner join round on gameround.roundID = round.roundID "
                + "where game.gameID = ?;";
        
        return jdbcTemplate.query(sql, new RoundMapper(), gameID);
        
    }
    
    /*
    RowMapper is used to translate the jbdcTemplate query result 
    into the appropiate object its supposed to represent
    
    GameMapper creates a new Game object from the Game db table.
    
    RoundMapper creates a new Round object from the Round db table.
    */
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
    
    private static final class RoundMapper implements RowMapper<Round> {
        
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException{
            Round newRound = new Round();
            newRound.setRoundID(rs.getInt("roundID"));
            newRound.setGameID(rs.getInt("gameID"));
            newRound.setGuess(rs.getString("guess"));
            newRound.setExactMatches(rs.getInt("exactMatches"));
            newRound.setPartialMatches(rs.getInt("partialMatches"));
            newRound.setLastRound(rs.getBoolean("lastround"));
            newRound.setRoundResult(rs.getString("roundResult"));
            String dateString = rs.getString("datenTime");

            DateTimeFormatter formatter = DateTimeFormatter.
                    ofPattern("yyyy-MM-dd HH:mm:ss");   

            LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
            newRound.setTime(dateTime);

            return newRound;
        }
        
    }
    
}
