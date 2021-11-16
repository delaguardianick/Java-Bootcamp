/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Money;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendingMachineController {
    
        private VendingMachineView view;
        private VendingMachineServiceLayer service;
        private VendingMachineDao dao = new VendingMachineDaoFileImpl();
        private UserIO io = new UserIOConsoleImpl();

        @Autowired
        public VendingMachineController(VendingMachineServiceLayer service, 
                VendingMachineView view){
            this.service = service;
            this.view = view;
    }
        
//      MAIN DRIVER FUNCTION
        public void run() {
            printMenu();
            Money VMBalance = new Money(userInsertMoney());
            
            /**
             * Ask for user to choose an item from menu
             * and validate if sufficient money in machine -> else quit program
             * if item not in stock, repeat
            */
            String itemName ;
            do {
                itemName = selectItem();
                validateSufficientFunds(VMBalance, itemName);
            } while(isItemInStock(itemName) == false);

            dispenseItem(itemName);

            /*
              Subtract itemPrice to balance and return change in coins
            */
            Money itemPrice = new Money(service.getItem(itemName).getPrice());
            Money totalChange = VMBalance.subtract(itemPrice);
            returnChange(totalChange);
        }
        
        /*
        Requests list of all items and prints the menu
        Displays error if list not available
        */
        public void printMenu()  {
            List<Item> items = null;
            try {
                items = service.getAllItems();
            } catch (VendingMachineDaoException ex) {
                view.displayErrorMessage("Couldn't load menu");
            }
            
            view.displayMenuBanner();
            view.printMenu(items);
//            view.printUnvailableItems(items);
        }
        
        /*
        Prompts user to insert the money in the vending machine
        Displays the balance
        @returns the balance in BigDecimal
        */
        public BigDecimal userInsertMoney(){
            BigDecimal balance = view.insertMoney();
            Money totalBalance = service.createBalance(balance);
            view.displayTotalBalance(totalBalance);
            return balance;
        }
        
        /*
        Prompts user to select an item from the menu
        The menu number reflects the index of the item in the List Items - 1
        @returns the item if available, displays error if not
        */
        public String selectItem() {
            try {
                int itemCount = service.getNumberOfItemsAvailable();
                int itemSelected = view.userSelectItem(itemCount) - 1;
                List<Item> items = service.getAllItems();
                String itemName = items.get(itemSelected).getName();
                return itemName;
            } catch (VendingMachineDaoException ex) {
                view.displayErrorMessage("Couldn't select item");
            }
            
            return null;
        }
        
        /*
        Searches the item chosen and displays it is being dispensed
        Subtracts the unit count in the HashMap and in the file
        */
        public void dispenseItem(String itemName) {
            
            view.displayEmptyLine();
            try {
                service.dispenseItem(itemName);
            } catch (VendingMachineDaoException ex) {
                view.displayErrorMessage("Couldn't dispense item");
            }
            Item currItem = service.getItem(itemName);
            view.displayItemDispensed(currItem);
        }
   
        /*
            @returns the change to be returned in dollars, quarters, dimes, 
             nickels and pennies
        */
        public void returnChange(Money change){
            view.displayChangeToBeReturned(change);
            int[] changeInCoins = change.splitInCoins();
            view.displayChangeInCoins(changeInCoins);
        }

        /*
            Check if you can afford the item, if not display error and quit.
        */
        private void validateSufficientFunds(Money VMBalance, String itemName){
        
            Money itemPrice = service.getItem(itemName).getPriceMoney();

            try {
                VMBalance.canAfford(itemPrice);
            }catch(InsufficientFundsException e){
                view.displayErrorMessage("Insufficient funds to "
                        + "purchase item. Exiting program");
                view.displayTotalBalance(VMBalance);
                System.exit(0);
            }

        }

        /*
        Validates if item has units left in stock
        if not, display error message and return false;
        */
        private Boolean isItemInStock(String itemName) {

            Item currItem = service.getItem(itemName);

            try{
                service.validateItemAvailability(currItem);
            }catch(NoItemInventoryException e){
                view.displayErrorMessage("No units remaining, "
                        + "please choose another item");

                return false;
            }
            return true;
        }
}
