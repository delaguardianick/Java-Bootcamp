/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Money;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Gordak
 */
public interface VendingMachineDao {
    
    void loadVendingMachine() throws VendingMachineDaoException;
    
    void saveVendingMachine() throws VendingMachineDaoException;
    
    String marshallItem(Item itemAsObject);
    
    Item unmarshallItem(String itemAsText);
    
   List<Item> getAllItems() throws VendingMachineDaoException;
   
    Money createBalance(BigDecimal balance);
    
    int getNumberOfItemsAvailable();
    
    Money getTotalBalance();

    public void dispenseItem(String itemName) 
            throws VendingMachineDaoException;
    
    Item getItem(String itemName);
}
