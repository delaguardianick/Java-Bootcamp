/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
    
    public int requestForMenuChoice(){
        return io.readInt("Please choose a menu option", 1, 6);
    }

    public void requestOrderInfo() {
//        Order date:
        LocalDate orderDate = requestOrderDate();
        
        String orderCustomerName = requestOrderCustomerName();
        
        String orderState = requestOrderState();
    }
    
    private LocalDate requestOrderDate(){
        
//        io.print("INPUT ORDER DATE (IN THE FUTURE)");
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        LocalDate orderDate ;
        LocalDate currentDate = LocalDate.now();
        
//       + if orderDate > currentDate
        do {
            orderDate = io.readDate("Enter Date in future (MM/DD/YYYY): ");
        }while(orderDate.compareTo(currentDate) < 0);

        return orderDate;
    }

    private String requestOrderCustomerName() {
        
        String regex = "^[a-zA-Z., ]*$";
        String customerName = "";
        
        do {
            customerName = io.readString("Please enter the customer's "
                + "name: ");
        }while(!customerName.matches(regex));
        
        return customerName;
    }
    
    private String requestOrderState(){
        return "";
    }
    
    private Double requestOrderArea(){
        return io.readDouble("Enter area in sq ft.(min: 100)", 100, 
                Double.POSITIVE_INFINITY);
    }
    
    
    
    
}
