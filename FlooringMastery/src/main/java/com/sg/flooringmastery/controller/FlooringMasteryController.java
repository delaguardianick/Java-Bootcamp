/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryDaoException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.view.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        Boolean stillRunning = true;
        while(stillRunning){
            displayMenu();
            int userChoice = userMenuChoice();
            
            switch(userChoice){
            case 1:
//                Display Orders
//                try {
                    displayOrders();
//                    stillRunning = false;
//                }catch(FlooringMasteryDaoException e){
//                    view.displayErrorMessage("Records not found");
//                }
                
                break;
            case 2: 
//                Add an order
                addAnOrder();
                break;
            case 3:
//                edit an order
                editAnOrder();
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
    }
    
    public void displayMenu(){
        view.printMenu();
    }
    
    public int userMenuChoice(){
        return view.requestForMenuChoice();
    }
    
    
    public void displayOrders() {
        
        LocalDate dateToSearch = view.requestOrderDate();
        
        List<Order> ordersForDate = displayOrdersForThisDate(dateToSearch);
        view.displayAllOrders(ordersForDate);
    }
    
    public List<Order> displayOrdersForThisDate(LocalDate dateToSearch){
        
        List<Order> ordersForDate = null;

        try {
            ordersForDate = service.displayOrdersForThisDate(dateToSearch);
        } catch (FlooringMasteryDaoException ex) {
//            throw new FlooringMasteryDaoException("No orders for such date");
             view.displayErrorMessage("No orders for such date");
        }
        
        return ordersForDate;
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
        
       Order newOrder = requestOrderInfo();
       newOrder.calcOrderFields();
//       service.addToOrders(newOrder);
       view.displayOrderSummary(newOrder);
       Boolean userResp = view.confirmSaveOrder();
       service.saveOrder(newOrder);

    }
    
    public Order requestOrderInfo(){
        List<State> states = getAllStates();
        List<Product> products = getAllProducts();

        LocalDate orderDate = view.requestOrderDate();
        
        String orderCustomerName = view.requestOrderCustomerName();
        
        State orderState = requestOrderState();
        
        displayProductList(products);
        Product orderProduct = requestOrderProductType(products);
        
        Double orderArea = view.requestOrderArea();

//      ------------------------
        Order newOrder = service.createNewOrder(orderDate, orderCustomerName,
                orderState, orderProduct, orderArea);
        
        return newOrder;
    }
    
    
    public Product requestOrderProductType(List<Product> products){
        return view.requestProductType(products);
    }
    
    public State requestOrderState(){
        
        String orderStateAbv = null;
        do{
            orderStateAbv = view.requestOrderState();
        }while(!verifyState(orderStateAbv));
        
        return service.getState(orderStateAbv);
    }
    
    public Boolean verifyState(String orderStateAbv){
        
        try {
            return service.isStateVerified(orderStateAbv);
        } catch (FlooringMasteryDaoException ex) {
            view.displayErrorMessage("Can't verify state");
        }
        return false;
    }
    
    public void displayProductList(List<Product> products){
        view.displayProducts(products);
    }
    
    public List<State> getAllStates(){
        
        List<State> states = null;
        
        try {
            states = service.getAllStates();
        } catch (FlooringMasteryDaoException ex) {
            view.displayErrorMessage("Could not load states list");
        }
        return states;
    }
    
    public List<Product> getAllProducts(){
        List<Product> products = null;
        
        try{
            products = service.getAllProducts();
        }catch(FlooringMasteryDaoException e){
            view.displayErrorMessage("Could not load product list");
        }
        return products;
    }

    private void editAnOrder(){
        Order currOrder = getOrderToEdit();
        
        if (currOrder == null){
            view.displayErrorMessage("No Order ID found for this date");
            return;
        }
        
        System.out.println(currOrder.getCustomerName());
    }
    
    private Order getOrderToEdit() {
        LocalDate date = view.requestOrderDate();
        int orderNumber = view.requestOrderNumber();
        
        List<Order> ordersForThisDate = displayOrdersForThisDate(date);
        
        for (Order order : ordersForThisDate){
            if (order.getOrderNumber() == orderNumber){
                return order;
            }
        }
        return null;
        
    }
    
}
