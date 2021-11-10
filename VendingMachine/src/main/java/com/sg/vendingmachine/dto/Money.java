/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Gordak
 */
public class Money {
    private BigDecimal balance;
    
    public Money(String balance){
        BigDecimal balanceBD = new BigDecimal(balance);
        this.balance = balanceBD;
    }
    
    public Money(BigDecimal balance){
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public void modifyBalance(BigDecimal modifier){
        this.balance = this.balance.add(modifier);
    }
    
    
}
