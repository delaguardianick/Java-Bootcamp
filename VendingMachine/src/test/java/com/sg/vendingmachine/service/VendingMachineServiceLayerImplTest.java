/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.Money;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class VendingMachineServiceLayerImplTest {
        
    VendingMachineDao testDao;
    VendingMachineServiceLayer testService;
            

    
    public VendingMachineServiceLayerImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        String testFile = "testVendingMachine.txt";
        testDao = new VendingMachineDaoFileImpl(testFile);
        testService = new VendingMachineServiceLayerImpl(testDao);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreateBalance() {
        setUp();
        BigDecimal balanceBD = new BigDecimal("16.45");
        Money balanceMoney = testService.createBalance(balanceBD);
        Money returnedBalanceMoney = testService.getTotalBalance();
        
        String balanceMoneyToString = balanceMoney.getBalance().toString();
        String returnedBalanceMoneyToString = returnedBalanceMoney.
                getBalance().toString();
        
        assertEquals(balanceMoneyToString, 
                returnedBalanceMoneyToString);
    }
    
}
