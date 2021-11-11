/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Gordak
 */
public class VendingMachineView {
    
    private UserIO io;
    
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    public void printMenu(List<Item> items){
        int count = 1;
        for (Item item : items){
//            if (item.getUnitsInStock() == 0){
//                count++;
//                continue;
//            }
            io.print(String.format("%d: %s - $%s - %d units left.", 
                    count, item.getName(), item.getPrice(), 
                    item.getUnitsInStock()));
            
            count++;
        }
    }
    
    public BigDecimal insertMoney(){
        
        Double moneyEntered = 0.00;
        while (moneyEntered <= 0 ){
            moneyEntered = io.readDouble("Please enter money: ");
        }
        
        return new BigDecimal(moneyEntered);
    }
    
    public void displayTotalBalance(BigDecimal balance){
        io.print(String.format("You have $%s remaining",balance.toString()));
    }
    
    public int userSelectItem(int max){
        int itemNumber = io.readInt("Select item to dispense",1, max);
        return itemNumber;
    }
    
    public void displayItemDispensed(Item itemSelected){
        io.print(String.format("Dispensing %s...", itemSelected.getName()));
        io.print(String.format("Price: %s", itemSelected.getPrice()));
        
    }

    public void displayChangeToBeReturned(String change) {
        io.print(String.format("Change to be returned: %s",
                    change));
    }
}
