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
        
        public void run() throws VendingMachineDaoException{
            printMenu();
            userInsertMoney();
            
    }
        
        public void printMenu() throws VendingMachineDaoException {
            List<Item> items = service.getAllItems();
            view.printMenu(items);
        }
        
        public void userInsertMoney(){
            BigDecimal balance = view.insertMoney();
            service.createBalance(balance);
            view.displayTotalBalance(balance);
        }
}
