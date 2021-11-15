/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Money;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Gordak
 */
public interface VendingMachineServiceLayer {
    
    void loadVendingMachine() throws VendingMachineDaoException;
    
    List<Item> getAllItems() throws VendingMachineDaoException;
    
    public Money createBalance(BigDecimal balance);
    
    int getNumberOfItemsAvailable();

//    public void getItem(int itemNumber);

    public void dispenseItem(String itemName)
            throws VendingMachineDaoException;

    public Item getItem(String itemName);

    public void validateItemAvailability(Item currItem) 
            throws NoItemInventoryException;

   Money getTotalBalance();
}
