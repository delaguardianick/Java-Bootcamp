/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Gordak
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    
    private final String TAXES_FILE = "Data/Taxes.txt";    
    private final String PRODUCTS_FILE = "Data/Products.txt";
    private final String DELIMITER = ",";
    
    ArrayList<State> states ;

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
            throw new FlooringMasteryDaoException("File not found"); 
        }
        
        states = new ArrayList<>();
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
            
            states.add(newState);
        }
        sc.close();
    }
    
    public String[] unmarshallItem(String line){
        String[] lineTokens = line.split(DELIMITER);
        return lineTokens;
    }
    
}
