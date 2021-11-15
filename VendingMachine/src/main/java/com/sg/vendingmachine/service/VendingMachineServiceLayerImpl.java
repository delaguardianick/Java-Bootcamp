/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
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
    
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao,
            VendingMachineAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void loadVendingMachine() throws VendingMachineDaoException {
        try{
            dao.loadVendingMachine();
        }catch(Exception e){
            throw e;
        }
        
        auditDao.writeAuditEntry("Loaded Vending Machine from file");
    }

    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException {
        return dao.getAllItems();
    }

    @Override
    public Money createBalance(BigDecimal balance) {
        return dao.createBalance(balance);
    }

    @Override
    public int getNumberOfItemsAvailable() {
        return dao.getNumberOfItemsAvailable();
    }

    @Override
    public void dispenseItem(String itemName)
            throws VendingMachineDaoException{
        dao.dispenseItem(itemName);
        
        Item currItem = dao.getItem(itemName);
        auditDao.writeAuditEntry(String.format(
                "Dispensed item %s, $%s, %d units left",
                itemName, currItem.getPrice(), currItem.getUnitsInStock()));
    }

    @Override
    public Item getItem(String itemName) {
        return dao.getItem(itemName);
    }

    @Override
    public void validateItemAvailability(Item currItem) 
            throws NoItemInventoryException{
        
        if (currItem.getUnitsInStock() <= 0){
            throw new NoItemInventoryException("Item not in stock, "
                    + "please choose another one.");
        }
    }
    
    @Override
    public Money getTotalBalance() {
        return dao.getTotalBalance();
    }
}
