/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    private final String DELIMITER = ",";
    
    Map<String, State> states ;
    Map <String, Product> products ;


//    private final String TAXES_FILE;    
//    private final String PRODUCTS_FILE;
    
//    public FlooringMasteryDaoFileImpl(String taxesFile, String productsFile){
//        this.TAXES_FILE = taxesFile;
//        this.PRODUCTS_FILE = productsFile;
//    }
    
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
    
}
