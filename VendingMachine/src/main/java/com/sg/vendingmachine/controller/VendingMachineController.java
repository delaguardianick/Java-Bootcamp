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

/**
 *
 * @author Gordak
 */
public class VendingMachineController {
    
        private VendingMachineView view;
        private VendingMachineServiceLayer service;
        private VendingMachineDao dao = new VendingMachineDaoFileImpl();
        private UserIO io = new UserIOConsoleImpl();


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
        
        public void printMenu()  {
            List<Item> items = null;
            try {
                items = service.getAllItems();
            } catch (VendingMachineDaoException ex) {
                view.displayErrorMessage("Couldn't load menu");
            }
            view.displayMenuBanner();
            view.printMenu(items);
        }
        
        public BigDecimal userInsertMoney(){
            BigDecimal balance = view.insertMoney();
            Money totalBalance = service.createBalance(balance);
            view.displayTotalBalance(totalBalance);
            return balance;
        }
        
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
        
        public void dispenseItem(String itemName) {
            
            try {
                service.dispenseItem(itemName);
            } catch (VendingMachineDaoException ex) {
                view.displayErrorMessage("Couldn't dispense item");
            }
            Item currItem = service.getItem(itemName);
            view.displayItemDispensed(currItem);
        }
   
        public void returnChange(Money change){
            view.displayChangeToBeReturned(change);
            int[] changeInCoins = change.splitInCoins();
            view.displayChangeInCoins(changeInCoins);
        }

//          check if you can afford item
        private void validateSufficientFunds(Money VMBalance, String itemName){
        
            Money itemPrice = service.getItem(itemName).getPriceMoney();

            try {
                VMBalance.compareToMoney(itemPrice);
            }catch(InsufficientFundsException e){
                view.displayErrorMessage("Insufficient funds to "
                        + "purchase item. Exiting program");
                view.displayTotalBalance(VMBalance);
                System.exit(0);
            }

        }

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
