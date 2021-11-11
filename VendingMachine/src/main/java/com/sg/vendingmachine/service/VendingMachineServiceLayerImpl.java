/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Money;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Gordak
 */
public class VendingMachineServiceLayerImpl implements 
        VendingMachineServiceLayer {
    
    VendingMachineDao dao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao){
        this.dao = dao;
    }

    @Override
    public void purchaseItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadVendingMachine() throws VendingMachineDaoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            dao.loadVendingMachine();
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public void saveVendingMachine() throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String marshallItem(Item itemAsObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item unmarshallItem(String itemAsText) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException {
        return dao.getAllItems();
    }

    @Override
    public void modifyBalance(BigDecimal modifier) {
        dao.modifyBalance(modifier);
    }

    @Override
    public void createBalance(BigDecimal balance) {
        dao.createBalance(balance);
    }

    @Override
    public int getNumberOfItemsAvailable() {
        return dao.getNumberOfItemsAvailable();
    }

//    @Override
//    public void getItem(int itemNumber) {
//        return dao.getItem(int itemNumber);
//    }

    @Override
    public Money getChangeInPennies(String price) {
        Money totalBalance = dao.getTotalBalance();
        Money itemPrice = new Money(price);
        
//        return itemPrice;
        return totalBalance.subtract(itemPrice);
    }
    
}
