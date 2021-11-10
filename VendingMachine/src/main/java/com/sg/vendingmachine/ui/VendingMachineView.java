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
        for (Item item : items){
            io.print(String.format("%s - $%s - %d units left.", 
                    item.getName(), item.getPrice(), item.getUnitsInStock()));
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
}
