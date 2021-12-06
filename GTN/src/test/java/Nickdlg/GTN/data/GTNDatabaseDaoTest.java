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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Gordak
 */
//@ExtendWith(SpringRunner.class)
//@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GTNDatabaseDaoTest {
    
    GTNDao testDao;
    JdbcTemplate jdbcTemplate;

    
    public GTNDatabaseDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        jdbcTemplate = new JdbcTemplate();
        testDao = new GTNDatabaseDao(jdbcTemplate);
//      Would be nice to reset database each time.
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addGame method, of class GTNDatabaseDao.
     */
    @Test
    public void testAddGame() {
        setUp();
        Game testGame = new Game();
        testGame.setSolution("4321");
        testDao.addGame(testGame);
        
        int gameID = testGame.getGameID();
        assertEquals(gameID, 1);
    }

    /**
     * Test of getGame method, of class GTNDatabaseDao.
     */
//    @Test
//    public void testGetGame() {
//        System.out.println("getGame");
//        int gameID = 0;
//        GTNDatabaseDao instance = null;
//        Game expResult = null;
//        Game result = instance.getGame(gameID);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getGameToDisplay method, of class GTNDatabaseDao.
     */
//    @Test
//    public void testGetGameToDisplay() {
//        System.out.println("getGameToDisplay");
//        int gameID = 0;
//        GTNDatabaseDao instance = null;
//        Game expResult = null;
//        Game result = instance.getGameToDisplay(gameID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }

    /**
     * Test of getAllGames method, of class GTNDatabaseDao.
     */
//    @Test
//    public void testGetAllGames() {
//        System.out.println("getAllGames");
//        GTNDatabaseDao instance = null;
//        List<Game> expResult = null;
//        List<Game> result = instance.getAllGames();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of addRound method, of class GTNDatabaseDao.
     */
//    @Test
//    public void testAddRound() {
//        System.out.println("addRound");
//        Round newRound = null;
//        GTNDatabaseDao instance = null;
//        instance.addRound(newRound);
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }

    /**
     * Test of addGameRound method, of class GTNDatabaseDao.
     */
//    @Test
//    public void testAddGameRound() {
//        System.out.println("addGameRound");
//        int gameID = 0;
//        int roundID = 0;
//        GTNDatabaseDao instance = null;
//        instance.addGameRound(gameID, roundID);
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }

    /**
     * Test of updateGameStatus method, of class GTNDatabaseDao.
     */
//    @Test
//    public void testUpdateGameStatus() {
//        System.out.println("updateGameStatus");
//        Game currGame = null;
//        GTNDatabaseDao instance = null;
//        boolean expResult = false;
//        boolean result = instance.updateGameStatus(currGame);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }

    /**
     * Test of getAllRoundsForGame method, of class GTNDatabaseDao.
     */
//    @Test
//    public void testGetAllRoundsForGame() {
//        System.out.println("getAllRoundsForGame");
//        int gameID = 0;
//        GTNDatabaseDao instance = null;
//        List<Round> expResult = null;
//        List<Round> result = instance.getAllRoundsForGame(gameID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        pass("The test case is a prototype.");
//    }
//    
}
