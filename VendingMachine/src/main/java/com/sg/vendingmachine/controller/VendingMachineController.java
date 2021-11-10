/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Gordak
 */
public class VendingMachineController {
    
        
        private VendingMachineView view;
        private VendingMachineDao dao;

        public VendingMachineController(VendingMachineDao dao, 
                VendingMachineView view){
            this.dao = dao;
            this.view = view;
    }
        
        public void run(){
            printMenu();
    }
        
        public void printMenu(){
            view.printMenu();
        }
}
