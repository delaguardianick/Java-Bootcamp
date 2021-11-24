/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoException;
import com.sg.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.time.LocalDate;
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
public class FlooringMasteryServiceLayerImplTest {
    
    FlooringMasteryDao testDao;
    FlooringMasteryServiceLayer testService;
    
    public FlooringMasteryServiceLayerImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        testDao = new FlooringMasteryDaoFileImpl("Data/Taxes.txt",
                        "Data/Products.txt", 
                        "TestOrders/",
                        "testOrderCount.txt");
        
        testService = new FlooringMasteryServiceLayerImpl(testDao);
        try {
            testDao.resetOrderCount(1);
        } catch (FlooringMasteryDaoException ex) {
            System.out.println("Couldnt reset order count");
            System.exit(0);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /*
    Creates an Order object, calculates remaining fields and tests it
    Also writes to the file
    */
    @Test
    public void testCreateOrder() {
        setUp();
        
        LocalDate date = LocalDate.of(2022,12,12);
        String customerName = "Pablo";
        State state = new State("CA");
        state.setStateFull("California");
        state.setTaxRate("25.00");
        Product product = new Product("TestWood");
        product.setCostPerSquareFoot("5.15");
        product.setLaborCostPerSquareFoot("4.75");
        Double area = 120.0;
        
        Order newOrder = testService.createNewOrder(
                date, customerName, state, product, area);
        
        newOrder.calcOrderFields();
        testService.saveOrder(newOrder);

        assertEquals(Order.asTwoDecimals(newOrder.getTotal()).toString(), "1485.00");
    }
    
    @Test
    public void testSaveOrder(){

    }
    
}
