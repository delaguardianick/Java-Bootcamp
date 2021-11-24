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

/**
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

    /*
    Main loop and functionality of the program
    Displays menu and asks commands the DTO's, service, 
    and view layer accordingly
    */
    public void run() {
        
        Boolean stillRunning = true;
        while(stillRunning){
            displayMenu();
            int userChoice = userMenuChoice();
            
            switch(userChoice){
            case 1:
                displayOrders();
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
                removeAnOrder();
//                remove an order
                break;
            case 5:
//                export all data
                view.displayErrorMessage("Feature not implemented in this iteration.");
                break;
            case 6:
//               Quit
                System.exit(0);
            }
        }
    }
    
    /*
    Prints the main menu
    */
    public void displayMenu(){
        view.printMenu();
    }
    
    /*
    Asks user to choose an option
    */
    public int userMenuChoice(){
        return view.requestForMenuChoice();
    }
    
    /*
    Displays the orders for a specific date in time
    Requests the date, returns all orders for that date and displays them
    */
    public void displayOrders() {
        LocalDate dateToSearch = view.requestOrderDate();
        
        List<Order> ordersForDate = displayOrdersForThisDate(dateToSearch);
        view.displayAllOrders(ordersForDate);
    }
    
    /*
    Wrapper for service.displayOrdersForThisDate to handle exception
    @returns list of orders for a specific date
    */
    public List<Order> displayOrdersForThisDate(LocalDate dateToSearch){
        
        List<Order> ordersForDate = null;

        try {
            ordersForDate = service.displayOrdersForThisDate(dateToSearch);
        } catch (FlooringMasteryDaoException ex) {
//            throw new FlooringMasteryDaoException("No orders for such date");
             view.displayErrorMessage("No orders for such date");
             System.exit(0);
        }
        return ordersForDate;
    }

    /*
    Parameters:
        LocalDate Order Date: must be in the future 
        String Customer Name: not be blank, contains [a-z][0-9][,.]
        State State: checked against TAX file
            if state not in tax file -> Exception
            must fetch most recent tax file each time its called
        String Product Type: Show list of available products & prices to choose
            must fetch most recent product list each time
        Area: positive decimal, units: sq ft, minimum: 100
    
    Requests info and adds the parameters to an Order object
    Saves the marshalled order in a text file according to the date
    */
    public void addAnOrder(){
       Order newOrder = requestOrderInfo();
       newOrder.calcOrderFields();
//       service.addToOrders(newOrder);
       view.displayOrderSummary(newOrder);
       Boolean userResp = view.confirmSaveOrder();
       service.saveOrder(newOrder);

    }
    
    /*
    Uses view to request for parameters (stated above) of a new order
    and creates order
    List of available products and states are needed to cross check & validate
    @returns Order object filled
    */
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
    
    /*
    Helper functions 
    Asks for user to select a product from the displayed product list
    @returns chosen Product
    */
    public Product requestOrderProductType(List<Product> products){
        return view.requestProductType(products);
    }
    
    /*
    Requests and verifies the state is available in the states list
    @returns valid state object
    */
    public State requestOrderState(){
        
        String orderStateAbv = null;
        do{
            orderStateAbv = view.requestOrderState();
        }while(!verifyState(orderStateAbv));
        
        return service.getState(orderStateAbv);
    }
    
    /*
    Helper function
    Verifies the state is in the states list (found in Taxes.txt)
    @returns weather state is valid or not
    */
    public Boolean verifyState(String orderStateAbv){
        
        try {
            return service.isStateVerified(orderStateAbv);
        } catch (FlooringMasteryDaoException ex) {
            view.displayErrorMessage("Can't verify state");
        }
        return false;
    }
    
    /*
    Prints the product list
    */
    public void displayProductList(List<Product> products){
        view.displayProducts(products);
    }
    
    /*
    Wrapper to handle exceptions from getting all states from Taxes.txt
    @returns list of available states
    */
    public List<State> getAllStates(){
        
        List<State> states = null;
        
        try {
            states = service.getAllStates();
        } catch (FlooringMasteryDaoException ex) {
            view.displayErrorMessage("Could not load states list");
        }
        return states;
    }
    
    /*
    Wrapper to get all available products
    @returns list of products
    */
    public List<Product> getAllProducts(){
        List<Product> products = null;
        
        try{
            products = service.getAllProducts();
        }catch(FlooringMasteryDaoException e){
            view.displayErrorMessage("Could not load product list");
        }
        return products;
    }

    /*
    Helper to editAnOrder()
    Asks and verifies parameters to be changed
    */
    private void editThisOrder(Order currOrder){
        
        view.editOrderFields(currOrder);
        String newState = view.editStateField(currOrder);
        if (verifyState(newState)){
            currOrder.setState(newState);
        };
        view.displayErrorMessage("Couldn't update State - not available\n");
        
        List<Product> products = getAllProducts();
        view.displayProducts(products);
        Product newProduct = view.requestProductType(products);
        currOrder.setProductType(newProduct.getProductType());
    }
    
    /*
    Main function to edit an order
    Receives list with all orders for a date, and edits the object that
    has the same order number with editThisOrder()
    Additional confirmation and writes the new list to file
    */
    private void editAnOrder() {
        LocalDate date = view.requestOrderDate();
        int orderNumber = view.requestOrderNumber();
        
        List<Order> ordersForThisDate = displayOrdersForThisDate(date);
        
        for (Order currOrder : ordersForThisDate){
            if (currOrder.getOrderNumber() == orderNumber){
                editThisOrder(currOrder);
                view.displayOrderSummary(currOrder);
                
                if (view.confirmSaveOrder()){
                     service.saveAllOrders(ordersForThisDate);
                }
            }
        }
    }

    /*
    Similar to editAnOrder() (might be able to be combined)
    Receives list with all orders for a date, and deletes the object that
    has the requested order number.
    Additional confirmation and writes the new list to file
    */
    private void removeAnOrder() {
//        Order currOrder = getOrderToEdit();

        LocalDate date = view.requestOrderDate();
        int orderNumber = view.requestOrderNumber();

        List<Order> ordersForThisDate = displayOrdersForThisDate(date);

        for (Order currOrder : ordersForThisDate){
            if (currOrder.getOrderNumber() == orderNumber){
                view.displayOrderSummary(currOrder);
                
                if (view.confirmDeleteOrder()){
                    ordersForThisDate.remove(currOrder);
                    break;
                }
            }
        }
        service.saveAllOrders(ordersForThisDate);
    }
    
}
