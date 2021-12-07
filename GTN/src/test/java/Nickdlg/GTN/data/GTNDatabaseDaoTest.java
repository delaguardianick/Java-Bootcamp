/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.data;

import Nickdlg.GTN.models.Game;
import Nickdlg.GTN.models.Round;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Gordak
 */
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = Nickdlg.GTN.TestApplicationConfiguration.class)

public class GTNDatabaseDaoTest {
    @Autowired
    GTNDao testDao;
    
    public GTNDatabaseDaoTest(){
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
//        testDao = new GTNDatabaseDao(jdbcTemplate);

//      Would be nice to reset database each time.
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addGame method, of class GTNDatabaseDao.
     * Test of getGame method, of class GTNDatabaseDao.
     */
    @Test
    public void testAddGame() {
        Game testGame = new Game();
        testGame.setSolution("4321");
        testDao.addGame(testGame);
        
        Game newGame = testDao.getGame(testGame.getGameID());
        assertEquals("4321", newGame.getSolution());
    }

   
    /**
     * Test of getGameToDisplay method, of class GTNDatabaseDao.
     */
    @Test
    public void testGetGameToDisplay() {
        Game testGame = new Game();
        testGame.setSolution("4321");
        testDao.addGame(testGame);
        
        Game newGame = testDao.getGameToDisplay(testGame.getGameID());
        assertEquals(null, newGame.getSolution());
    }

    /**
     * Test of getAllGames method, of class GTNDatabaseDao.
     */
    @Test
    public void testGetAllGames() {
        Game testGame = new Game();
        testGame.setSolution("4321");
        testGame.setFinished(Boolean.TRUE);
        testDao.addGame(testGame);
        
        List<Game> games = testDao.getAllGames();
        Game lastGame = games.get(games.size() - 1);
        assertEquals("4321", lastGame.getSolution());
    }

    /**
     * Test of addRound method, of class GTNDatabaseDao.
     */
    @Test
    public void testAddRound() {
        Round newRound = new Round();
        newRound.setGameID(1);
        newRound.setGuess("1234");
        newRound.setExactMatches(0);
        newRound.setPartialMatches(4);
        newRound.setRoundResult("e:0p:4");
        
        testDao.addRound(newRound);
        List<Round> rounds = testDao.getAllRoundsForGame(1);
        
        Round lastRound = rounds.get(rounds.size() - 1);
        assertEquals("1234", lastRound.getGuess());
    }
}