/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gordak
 */

@Component
public class FlooringMasteryView {
        
        private UserIO io;
    
    @Autowired
    public FlooringMasteryView(UserIO io){
        this.io = io;
    }

    public void printMenu() {
        io.print("\n*****************");
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
//
//    public void requestOrderInfo() {
////        Order date:
//        LocalDate orderDate = requestOrderDate();
//        
//        String orderCustomerName = requestOrderCustomerName();
//        
//        String orderState = requestOrderState();
//        
//    }
    
    public void displayAllOrders(List<Order> listOfOrders){
        for (Order order : listOfOrders){
            displayOrderSummary(order);
        }
    }
    
    public int requestOrderNumber(){
        return io.readInt("Enter the order number: ");
    }
    
    public LocalDate requestOrderDate(){
        
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

    public String requestOrderCustomerName() {
        
        String regex = "^[a-zA-Z., ]*$";
        String customerName = "";
        
        do {
            customerName = io.readString("Please enter the customer's "
                + "name: ");
        }while(!customerName.matches(regex));
        
        return customerName;
    }
    
    public String requestOrderState(){
        return io.readString("Enter state abreviation (ex. TX): ");
    }
    
    public Product requestProductType(List<Product> products){
        int size = products.size();
        String prompt = String.format("Choose a product (1 - %d)", size);
        int productIndex = io.readInt(prompt, 1, size);
        return products.get(productIndex-1);
    }
    
    public Double requestOrderArea(){
        return io.readDouble("Enter area in sq ft.(min: 100)", 100, 
                Double.POSITIVE_INFINITY);
    }
    
    public void displayProducts(List<Product> productList){
        io.print("Choose a product from below");
        int count = 1;
        for (Product prod : productList){
            String toPrint = String.format("%d. %s: $%s/sqf :, "
                    + "$%s labor/sqft",
                    count,
                    prod.getProductType(), 
                    prod.getCostPerSquareFoot().toString(), 
                    prod.getLaborCostPerSquareFoot().toString());
            count++;
            io.print(toPrint);
        }
    }
    
     public void displayErrorMessage(String errorMsg){
        io.print("===ERROR===");
        io.print(errorMsg);       
    }
    
    public void displayOrderSummary(Order newOrder){
        io.print(String.format("\n=== Displaying summary for order %d: ===",
                newOrder.getOrderNumber()));
        io.print(String.format("Date: %s\nCustomer Name: %s"
                + "\nProduct type: %s\n"
                + "Total: $%s",
                newOrder.getDate().toString(),
                newOrder.getCustomerName(),
                newOrder.getProductType(),
                newOrder.asTwoDecimals(newOrder.getTotal()).toString()));

//                newOrder.getTaxRate().toString(),
    }
    
    public Boolean confirmSaveOrder(){
        String userResp = io.readString("Would you like to save this"
                + " order? (Y/N)");
        
        switch (userResp){
            case "Y": 
                io.print("Order saved");
                return true;
                
            case "N":
                io.print("Order not saved");
                return false;
            
            default:
                io.print("Order not saved");
                return false;
        }
    }
    
    public void editOrderFields(Order currOrder){
        String customerName = currOrder.getCustomerName();
        String area = currOrder.getArea().toString();
        
        String newCustomerName = io.readString(
                String.format("Enter customer name (%s):", customerName));
        Double newArea = io.readDouble(String.format("Enter area (%s):", area)
                , 0, Double.POSITIVE_INFINITY);

        if (newCustomerName != ""){
            currOrder.setCustomerName(newCustomerName);
        }
        
        if (newArea != 0.0){
            currOrder.setArea(newArea.toString());
        }
    }
    
    public String editStateField(Order currOrder){
        String state = currOrder.getState();
        String newState = io.readString(String.format(
                "Enter state (%s):", state));
        
        return newState;

    }
    
    public Boolean confirmDeleteOrder(){
        String userResp = io.readString("Are you sure you want to delete this"
                + " order? (Y/N)");
        
        switch (userResp){
            case "Y": 
                io.print("Order deleted");
                return true;
                
            case "N":
                io.print("Order not deleted");
                return false;
            
            default:
                io.print("Order not deleted");
                return false;
        }
    }

}
