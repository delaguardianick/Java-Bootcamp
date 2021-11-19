/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

public class FlooringMasteryDaoException extends Exception{
    
    public FlooringMasteryDaoException(String message){
        super(message);
    }
    
    public FlooringMasteryDaoException(String message, Throwable cause){
        super(message, cause);
    }
}