/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Money;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Gordak
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Item> vendingMachine = new HashMap<>();
    private Money totalBalance = new Money("0");
    
    private final String VM_FILE;
    public static final String DELIMITER = "::";

    
    public VendingMachineDaoFileImpl(){
//        vendingMachine.txt
        VM_FILE = "vendingMachine.txt";
    }
    
    public VendingMachineDaoFileImpl(String VMTextFile){
//        vendingMachine.txt
        VM_FILE = VMTextFile;
    }
    
    @Override
    public void loadVendingMachine() throws VendingMachineDaoException{
        Scanner sc;
        
        try {
            sc = new Scanner(
                new BufferedReader(
                    new FileReader(VM_FILE)));
        }
        catch(FileNotFoundException e){
            throw new VendingMachineDaoException("File not found"); 
        }
        
        while(sc.hasNextLine()){
            String currLine = sc.nextLine();
            Item currItem = unmarshallItem(currLine);
            vendingMachine.put(currItem.getName(), currItem);
        }
        sc.close();
    }
    
     @Override
    public void saveVendingMachine() throws VendingMachineDaoException{
        PrintWriter out;
        
         try {
             out = new PrintWriter(new FileWriter(VM_FILE));
             
         } catch (IOException e) {
             throw new VendingMachineDaoException("Couldn't save to file");
         }
         
        List<Item> allItems = getAllItems();
        for (Item itemAsObject : allItems){
            String itemAsText = marshallItem(itemAsObject);
            
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
    
    @Override
    public String marshallItem(Item itemAsObject) {
        String name = itemAsObject.getName();
        String price = itemAsObject.getPrice();
        int units = itemAsObject.getUnitsInStock();
        
        String itemAsText = String.format("%s::%s::%d",
                name, price, units);
         
        return itemAsText;
    }

    @Override
    public Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        String itemName = itemTokens[0];
        
        Item itemFromFile = new Item(itemName);
        itemFromFile.setPrice(itemTokens[1]);
        itemFromFile.setUnitsInStock(Integer.valueOf(itemTokens[2]));
        
        return itemFromFile;

    }

    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException {
        loadVendingMachine();
        return new ArrayList<Item>(vendingMachine.values());
    }

    @Override
    public Money createBalance(BigDecimal balance) {
        totalBalance.setBalance(balance);
        return totalBalance;
    }

    @Override
    public int getNumberOfItemsAvailable(){
        return vendingMachine.size();
    }

    @Override
    public Money getTotalBalance() {
        return this.totalBalance;
    }

    @Override
    public void dispenseItem(String itemName) 
            throws VendingMachineDaoException{
        
        loadVendingMachine();
        Item dispensedItem = getItem(itemName);
        dispensedItem.setUnitsInStock(
                dispensedItem.getUnitsInStock() - 1);
        saveVendingMachine();
        
//        Could add NoItemInventoryException here
    }
    
    public Item getItem(String itemName){
        return vendingMachine.get(itemName);
    }

   
    
}
