/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Gordak
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    
    private final String TAXES_FILE = "Data/Taxes.txt";    
    private final String PRODUCTS_FILE = "Data/Products.txt";    
    private final String ORDERS_PATH = "Orders/";
    private final String ORDERCOUNT_FILE = "orderCount.txt";

    private final String DELIMITER = ",";
    
    Map<String, State> states ;
    Map <String, Product> products ;
    Map <Integer, Order> orders;


//    private final String TAXES_FILE;    
//    private final String PRODUCTS_FILE;
    
//    public FlooringMasteryDaoFileImpl(String taxesFile, String productsFile){
//        this.TAXES_FILE = taxesFile;
//        this.PRODUCTS_FILE = productsFile;
//    }
    
    @Override
    public List<Order> getAllOrders(){
        return new ArrayList<Order>(orders.values());
    }
    
    @Override
    public void addToOrders(Order newOrder){
        orders.put(newOrder.getOrderNumber(), newOrder);
    }
    
    @Override
    public void loadStates() throws FlooringMasteryDaoException{
        Scanner sc;
        
        try {
            sc = new Scanner(
                new BufferedReader(
                    new FileReader(TAXES_FILE)));
        }
        catch(FileNotFoundException e){
            throw new FlooringMasteryDaoException("Taxes file not found"); 
        }
        
        states = new HashMap<>();
        sc.nextLine();
        while(sc.hasNextLine()){
            String currLine = sc.nextLine();
            String[] taxTokens = unmarshallItem(currLine);
            
            String stateAbv = taxTokens[0];
            String stateFull = taxTokens[1];
            String taxRate = taxTokens[2];
            
            State newState = new State(stateAbv);
            newState.setStateFull(stateFull);
            newState.setTaxRate(taxRate);
            
            states.put(stateAbv, newState);
        }
        sc.close();
    }
    
    @Override
    public void loadProducts() throws FlooringMasteryDaoException{
        Scanner sc;
        
        try {
            sc = new Scanner(
                new BufferedReader(
                    new FileReader(PRODUCTS_FILE)));
        }
        catch(FileNotFoundException e){
            throw new FlooringMasteryDaoException("Product file not found"); 
        }
        
        products = new HashMap<>();
        sc.nextLine();
        while(sc.hasNextLine()){
            String currLine = sc.nextLine();
            String[] productTokens = unmarshallItem(currLine);
            
            String productType = productTokens[0];
            String costPerSquareFoot = productTokens[1];
            String laborCostPerSquareFoot = productTokens[2];
            
            Product newProduct = new Product(productType);
            newProduct.setCostPerSquareFoot(costPerSquareFoot);
            newProduct.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
            
            products.put(productType, newProduct);
        }
        sc.close();
    }
    
    @Override
    public String[] unmarshallItem(String line){
        String[] lineTokens = line.split(DELIMITER);
        return lineTokens;
    }
    
    @Override
    public List<State> getAllStatesObjects() throws FlooringMasteryDaoException{
        loadStates();
        return new ArrayList<State>(states.values());
    }
    
    @Override
    public List<String> getAllStatesAbvs() throws FlooringMasteryDaoException{
        loadStates();
        return new ArrayList<String>(states.keySet());
    }
    
    @Override
     public List<Product> getAllProducts() throws FlooringMasteryDaoException{
        loadProducts();
        return new ArrayList<Product>(products.values());
    }
     
     @Override
     public State getState(String stateAbv){
         return states.get(stateAbv);
     }
     
     @Override
     public Product getProduct(String productType){
         return products.get(productType);
     }
     
     @Override
     public void saveOrder(Order newOrder) throws FlooringMasteryDaoException{
         
        String filePath = getFilePathForOrderDate(newOrder.getDate());
         
         PrintWriter out;
        
         try {
             out = new PrintWriter(new FileWriter(filePath, true));
             
         } catch (IOException e) {
             throw new FlooringMasteryDaoException("Couldn't save to file");
         }
         
         String orderAsString = marshallOrder(newOrder);
         out.println(orderAsString);
         out.flush();
         out.close();
         
     }
     
     @Override
     public void saveAllOrders(List<Order> orders) throws FlooringMasteryDaoException{
        
         String filePath = getFilePathForOrderDate(orders.get(0).getDate());
         
         PrintWriter out;
        
         try {
             out = new PrintWriter(new FileWriter(filePath));
             
         } catch (IOException e) {
             throw new FlooringMasteryDaoException("Couldn't save to file");
         }
         
         for (Order currOrder : orders){
            String orderAsString = marshallOrder(currOrder);
            out.println(orderAsString);
            out.flush();
         }
        out.close();
     }
     
     public String getFilePathForOrderDate(LocalDate date){
          String orderDate = date.
                 format(DateTimeFormatter.ofPattern("MMddYYYY"));
         
         String filePath = ORDERS_PATH + "Orders_" + orderDate;
         return filePath;
     }
    
     
     public String marshallOrder(Order newOrder){
         
        int orderNum = newOrder.getOrderNumber();
        String customerName = newOrder.getCustomerName();
        String stateAbv = newOrder.getState();
        String taxRate = Order.asTwoDecimals(newOrder.getTaxRate()).toString();
        String productType =  newOrder.getProductType();
        String area = Order.asTwoDecimals(newOrder.getArea()).toString();
        String costPerSqFt = Order.asTwoDecimals(newOrder.getCostPerSquareFoot()).toString();
        String laborCostPerSqFt = Order.asTwoDecimals(newOrder.getLaborCostPerSquareFoot()).toString();
        String materialCost = Order.asTwoDecimals(newOrder.getMaterialCost()).toString();
        String laborCost = Order.asTwoDecimals(newOrder.getLaborCost()).toString();
        String tax = Order.asTwoDecimals(newOrder.getTax()).toString();
        String total = Order.asTwoDecimals(newOrder.getTotal()).toString();
        
        return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                orderNum, customerName,
                stateAbv, taxRate,
                productType, area,
                costPerSqFt, laborCostPerSqFt,
                materialCost, laborCost,
                tax, total);
     }
     
     public Order unmarshallOrder(String orderAsText, LocalDate date){
        String[] orderTokens = orderAsText.split(",");
         
        int orderNumber = Integer.parseInt(orderTokens[0]);
        String customerName = orderTokens[1];
        State state = new State(orderTokens[2]);
        String taxRate = orderTokens[3];
        Product productType = new Product(orderTokens[4]);
        String area = orderTokens[5];
        String costPerSqFt = orderTokens[6];
        String laborCostPerSqFt = orderTokens[7];
        
        String materialCost = orderTokens[8];
        String laborCost = orderTokens[9];
        String tax = orderTokens[10];
        String total = orderTokens[11];
        
        Order newOrder = createNewOrder(date, customerName,
                state, productType, Double.valueOf(area));
        
        newOrder.setTaxRate(new BigDecimal(taxRate)); 
        newOrder.setCostPerSquareFoot(new BigDecimal(costPerSqFt));
        newOrder.setLaborCostPerSquareFoot(new BigDecimal(laborCostPerSqFt));
        newOrder.setMaterialCost(new BigDecimal(materialCost));
        newOrder.setLaborCost(new BigDecimal(laborCost));
        newOrder.setTax(new BigDecimal(tax));

        newOrder.setOrderNumber(orderNumber);
        newOrder.setTotal(new BigDecimal(Double.valueOf(total)));

        return newOrder;

     }
    
     @Override
     public int getLatestOrderNumber() 
             throws FlooringMasteryDaoException{
         
        String filePath = ORDERS_PATH + ORDERCOUNT_FILE;
        Scanner sc;
        
        try {
            sc = new Scanner(
                new BufferedReader(
                    new FileReader(filePath)));
        }
        catch(FileNotFoundException e){
            throw new FlooringMasteryDaoException("Product file not found"); 
        }
        
        int latestOrderNumber = sc.nextInt();
        
        PrintWriter out;
        
         try {
             out = new PrintWriter(new FileWriter(filePath));
             
         } catch (IOException e) {
             throw new FlooringMasteryDaoException("Couldn't save to file");
         }
         
        out.println(latestOrderNumber + 1);
        out.flush();
        out.close();
        
        return latestOrderNumber;
     }
     
     
     
     public Order createNewOrder(LocalDate orderDate, String orderCustomerName,
            State orderState, Product orderProduct, Double orderArea){
        
        Order newOrder = new Order();
//        newOrder.setOrderNumber(getLatestOrderNumber());
        newOrder.setDate(orderDate);
        newOrder.setCustomerName(orderCustomerName);
        newOrder.setState(orderState.getStateAbv());
        newOrder.setProductType(orderProduct.getProductType());
        newOrder.setArea(orderArea.toString());
        
        newOrder.setCostPerSquareFoot(orderProduct.getCostPerSquareFoot());
        newOrder.setLaborCostPerSquareFoot(orderProduct.getLaborCostPerSquareFoot());
        newOrder.setTaxRate(orderState.getTaxRate());
        
        return newOrder;
    }
     
     public List<Order> displayOrdersForThisDate(LocalDate date) 
             throws FlooringMasteryDaoException{
         
         ArrayList<Order> ordersForDate = new ArrayList<>();
         String filePath = getFilePathForOrderDate(date);
         
         Scanner sc;
        
        try {
            sc = new Scanner(
                new BufferedReader(
                    new FileReader(filePath)));
        }
        catch(FileNotFoundException e){
            throw new FlooringMasteryDaoException("File not found"); 
        }
        
        while(sc.hasNextLine()){
            String currLine = sc.nextLine();
            Order currOrder = unmarshallOrder(currLine, date);
            ordersForDate.add(currOrder);
        }
        sc.close();
        return ordersForDate;
     }
}
