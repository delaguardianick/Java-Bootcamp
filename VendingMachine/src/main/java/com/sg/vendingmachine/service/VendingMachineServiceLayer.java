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
    
    void purchaseItem();
    
    void loadVendingMachine() throws VendingMachineDaoException;
    
    void saveVendingMachine() throws VendingMachineDaoException;
    
    String marshallItem(Item itemAsObject);
    
    Item unmarshallItem(String itemAsText);
    
    List<Item> getAllItems() throws VendingMachineDaoException;
    
    void modifyBalance(BigDecimal modifier);

    public Money createBalance(BigDecimal balance);
    
    int getNumberOfItemsAvailable();

//    public void getItem(int itemNumber);

    public Money getChangeInPennies(Money itemPrice);


    public void changeInCoins(Money totalChange);

        
}
