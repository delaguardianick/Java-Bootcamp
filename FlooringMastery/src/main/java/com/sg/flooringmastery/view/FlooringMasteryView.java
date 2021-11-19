/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

/**
 *
 * @author Gordak
 */
public class FlooringMasteryView {
        
        private UserIO io;
    
    public FlooringMasteryView(UserIO io){
        this.io = io;
    }

    public void printMenu() {
        io.print("*****************");
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders");     
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");

        io.print("*****************");

    }
}
