/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

/**
 *
 * @author Gordak
 */
public class VendingMachineDaoException extends Exception{
    
    public VendingMachineDaoException(String message){
        super(message);
    }
    
    public VendingMachineDaoException(String message, Throwable cause){
        super(message, cause);
    }
}
