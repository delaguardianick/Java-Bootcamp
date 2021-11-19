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
    }
    
    public void displayMenu(){
        view.printMenu();
    }
    
}
