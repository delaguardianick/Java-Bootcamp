/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.view.FlooringMasteryView;

/**
 *
 * @author Gordak
 */
public class FlooringMasteryController {
    
    private FlooringMasteryServiceLayer service;
    private FlooringMasteryView view;

    public FlooringMasteryController(FlooringMasteryServiceLayer service,
            FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        displayMenu();
        
        int userChoice = userMenuChoice();
        switch(userChoice){
            case 1:
//                Display Orders
                break;
            case 2: 
//                Add an order
                addAnOrder();
                break;
            case 3:
//                edit an order
                break;
            case 4:
//                remove an order
                break;
            case 5:
//                export all data
                break;
            case 6:
//               Quit
                System.exit(0);
        }
    }
    
    public void displayMenu(){
        view.printMenu();
    }
    
    public int userMenuChoice(){
        return view.requestForMenuChoice();
    }

    /*
    Request:
        Order Date: must be in the future
        Customer Name: not be blank, contains [a-z][0-9][,.]
        State: checked against TAX file
            if state not in tax file -> Exception
            must fetch most recent tax file each time its called
        Product Type: Show list of available products & prices to choose
            must fetch most recent product list each time
        Area: positive decimal, units: sq ft, minimum: 100
            
    */
    public void addAnOrder(){
        view.requestOrderInfo();
    }
    
}
