/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Money;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class VendingMachineView {
    
    private UserIO io;
    
    @Autowired
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    /*
    Prints all items in the list with price and units in stock
    Indexes correspond to position of item in list + 1
    ex. 6: Chocolate - $3.99 - 4 units left.
    */
    public void printMenu(List<Item> items){
        int count = 1;
        for (Item item : items){
            io.print(String.format("%d: %s - $%s - %d units left.", 
                    count, item.getName(), item.getPrice(), 
                    item.getUnitsInStock()));
            count++;
        }
    }
    
    /*
    Uses lambda function to print unavailable items
    Currently unused but works
    */
    public void printUnvailableItems(List<Item> items){
        io.print("Unavailable items: ");
        items.stream().filter((item) -> item.getUnitsInStock() == 0).
                map((item) -> item.getName()).forEach((item) -> io.print(item));
    }
    
    public void displayMenuBanner(){
        io.print("=== Vending Machine ===");
    }
    
    /*
    Prompts user to enter money into machine
    */
    public BigDecimal insertMoney(){
        Double moneyEntered = 0.00;
        while (moneyEntered <= 0 ){
            moneyEntered = io.readDouble("Please enter money: ");
        }
        return new BigDecimal(moneyEntered);
    }
    
    public void displayTotalBalance(Money balance){
        io.print(String.format("Balance: $%s available",
                balance.getAsTwoDecimals().toString()));
    }
    
    public int userSelectItem(int max){
        int itemNumber = io.readInt("Select item to dispense",1, max);
        return itemNumber;
    }
    
    public void displayItemDispensed(Item itemSelected){
        io.print(String.format("Dispensing %s... Price: $%s ",
                itemSelected.getName(), itemSelected.getPrice()));
    }

    public void displayChangeToBeReturned(Money change) {
        String changeString = change.getAsTwoDecimals().toString();
        io.print(String.format("Change to be returned: $%s",
                    changeString));
    }

    /*
    Gets list of coin split from the Money.splitInCoins() function
    Displays each coin quantity accordingly
    */
    public void displayChangeInCoins(int[] changeInCoins) {
        
        String toDisplay = "";
        for (int i=0; i < changeInCoins.length; i++){
            int numOfCoins = changeInCoins[i];
            
            if (numOfCoins == 0){
                continue;
            }
            String temp = "";
            switch(i){
                case 0:
                    temp = String.format("%d dollars", numOfCoins);
                    break;
                
                case 1:
                    temp = String.format("%d quarters", numOfCoins);
                    break;
                  
                case 2:
                    temp = String.format("%d dimes", numOfCoins);
                    break;
                  
                case 3:
                    temp = String.format("%d nickels", numOfCoins);
                    break;
                    
                case 4:
                    temp = String.format("%d pennies", numOfCoins);
                    break;
            }
            if (i != changeInCoins.length - 1){
                toDisplay += temp + ", ";
                continue;
            }
            
            toDisplay += temp + ".";
            
        }
        io.print(toDisplay);
    }
    
    public void displayErrorMessage(String errorMsg){
        io.print("===ERROR===");
        io.print(errorMsg);       
    }
    
    public void displayEmptyLine(){
        io.print("");
    }
}
