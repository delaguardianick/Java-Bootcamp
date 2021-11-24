/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
public class FlooringMasteryDaoFileImplTest {
    
    FlooringMasteryDao testDao;
    
    public FlooringMasteryDaoFileImplTest() {
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
                        "orderCount.txt");
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testStateMethods() throws FlooringMasteryDaoException {
        setUp();

        testDao.loadStates();
        List<String> states = testDao.getAllStatesAbvs();
        
        List<String> mockStates = Arrays.asList("TX", "WA", "KY", "CA");
        assertEquals(states, mockStates);
    }
    
    @Test
    public void testProductMethods() throws FlooringMasteryDaoException{
        setUp();
        
        testDao.loadProducts();
        List<Product> products = testDao.getAllProducts();
        List<String> productTypes = products.stream().
                    map(p -> p.getProductType()).
                    collect(Collectors.toList());
        
        List<String> mockProducts = Arrays.asList(
                "Carpet", "Laminate", "Tile", "Wood");
                
        assertEquals(productTypes.size(), mockProducts.size());
    }
}
