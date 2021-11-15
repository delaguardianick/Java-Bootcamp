/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Gordak
 */
public class VendingMachineDaoFileImplTest {
   
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        createTestFile();
        try {
            testDao.loadVendingMachine();
        } catch (VendingMachineDaoException ex) {
            System.out.println("Cant setup");
        }
    }
    
    public void createTestFile(){
        String testFile = "testVendingMachine.txt";
        testDao = new VendingMachineDaoFileImpl(testFile);

        PrintWriter out = null;
//        PrintWriter out = new PrintWriter(new FileWriter("testVendingMachine.txt"));
        try {
            out = new PrintWriter(new FileWriter("testVendingMachine.txt"));
        }catch(IOException e){
            System.out.println("cant create test file");
        }
        
        ArrayList<String> vmItems = setupVendingMachine();
        
        for (String testItem : vmItems){
            out.println(testItem);
            out.flush();
        }
        out.close();
    }
    
    public ArrayList<String> setupVendingMachine(){
        String water = "Water::1.50::2";      
        String snack = "Snack Bar::2.50::3";
        String chips = "Chips::3.99::5";
        String muffin = "Muffin::4.99::4";
        String juice = "Juice::2.15::4";
        String coffee = "Coffee::2.00::0";
        String chocolate = "Chocolate::3.99::1";

        ArrayList<String> vmItems = new ArrayList<>();
        vmItems.add(water);
        vmItems.add(snack);
        vmItems.add(chips);
        vmItems.add(muffin);
        vmItems.add(juice);
        vmItems.add(coffee);
        vmItems.add(chocolate);
        
        return vmItems;
    }
    
    @AfterEach
    public void tearDown() {
    }

    @org.junit.jupiter.api.Test
    public void testLoad() throws VendingMachineDaoException {
        setUp();
        assertEquals(testDao.getNumberOfItemsAvailable(), 7);
    }
    
    @Test
    public void testMarshallItem(){
        setUp();
        Item testItem = testDao.getItem("Water");
        String testItemAsString = testDao.marshallItem(testItem);
        assertEquals(testItemAsString, "Water::1.50::2");
    }
   
    @Test 
    public void testDispenseItem() throws VendingMachineDaoException{
        setUp();
        int prevStock = testDao.getItem("Chocolate").getUnitsInStock();
        testDao.dispenseItem("Chocolate");
        int currStock = testDao.getItem("Chocolate").getUnitsInStock();
        
        assertEquals(prevStock - 1, currStock);
    }
    
    @Test 
    public void testGetItem(){
        setUp();
        String itemPrice = testDao.getItem("Muffin").getPrice();
        assertEquals(itemPrice, "4.99");
    }
    
    
//    Have to implement Item comparison..
//    @Test
//    public void testUnmarshallItem(){
//        setUp();
//        String itemAsString = "Water::1.50::2";
//        Item testItemAsObject = testDao.unmarshallItem(itemAsString);
//        Item whatItShouldBe = testDao.getItem("Water");
//
//        assertEquals(testItemAsObject, whatItShouldBe);
//    }
    
//    private Writer FileWriter(String testVendingMachinetxt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
