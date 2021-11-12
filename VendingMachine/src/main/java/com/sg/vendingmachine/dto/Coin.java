/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

/**
 *
 * @author Gordak
 */
public enum Coin{
    DOLLAR("1.00"), QUARTER("0.25"), DIME("0.10"), 
    NICKEL("0.05"), PENNY("0.01");
    
    private String value;
    
    Coin (String value){
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
    
    
}
