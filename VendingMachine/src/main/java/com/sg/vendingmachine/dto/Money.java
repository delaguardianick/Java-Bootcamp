/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.sg.vendingmachine.dto.Coin;
import java.math.MathContext;

/**
 *
 * @author Gordak
 */
public class Money {
    private BigDecimal balance;
    private int dollars;
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    
    private BigDecimal dollarValue = new BigDecimal(Coin.DOLLAR.getValue());
    private BigDecimal quarterValue = new BigDecimal(Coin.QUARTER.getValue());
    private BigDecimal dimeValue = new BigDecimal(Coin.DIME.getValue());
    private BigDecimal nickelValue = new BigDecimal(Coin.NICKEL.getValue());
    private BigDecimal pennyValue = new BigDecimal(Coin.PENNY.getValue());

    
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
    
    public Money subtract(Money itemPrice){
        return new Money(this.balance.subtract(itemPrice.getBalance()));
//        this.balance.subtract(itemPrice.getBalance());

    }
    
    public String toString(){
        return this.balance.toString();
    }
    
    public Money getAsTwoDecimals(){
        BigDecimal changeBD = this.getBalance();
        changeBD = changeBD.setScale(2, RoundingMode.HALF_UP);
        return new Money(changeBD);
    }
    
    public int[] splitInCoins(){
        BigDecimal currBalance = this.balance;
        int[] changeInCoins = new int[5]; 
        
//      DOLLARS
        BigDecimal dollarsBD = currBalance.subtract(
                currBalance.remainder(dollarValue));
        this.dollars = dollarsBD.intValue();
        currBalance = currBalance.subtract(dollarsBD);
        
        changeInCoins[0] = this.dollars;
        
//      QUARTERS
        currBalance = splitQDNP(currBalance, quarterValue, Coin.QUARTER);
        changeInCoins[1] = this.quarters;

//        DIMES
        currBalance = splitQDNP(currBalance, dimeValue, Coin.DIME);
        changeInCoins[2] = this.dimes;

//        NICKELS
        currBalance = splitQDNP(currBalance, nickelValue, Coin.NICKEL);
        changeInCoins[3] = this.nickels;

//        PENNIES
        currBalance = splitQDNP(currBalance, pennyValue, Coin.PENNY);
        changeInCoins[4] = this.pennies;
        
        return changeInCoins;
    }
    
    public BigDecimal splitQDNP(BigDecimal currBalance, BigDecimal coinVal, 
            Coin coinType ){
        
        BigDecimal numOfCoin = currBalance.divide(coinVal, 2, 
                  RoundingMode.HALF_UP);
        
        if (numOfCoin.intValue() >= 1){
            
            switch(coinType){
                case QUARTER:
                    this.quarters = numOfCoin.intValue();
                    break;

                case DIME:
                    this.dimes = numOfCoin.intValue();
                    break;
                    
                case NICKEL:
                    this.nickels = numOfCoin.intValue();
                    break;
                    
                case PENNY:
                    this.pennies = numOfCoin.intValue();
                    break;
            }
            currBalance = currBalance.subtract(
                    coinVal.multiply(BigDecimal.valueOf(numOfCoin.intValue())));

            return currBalance;
        } 
        return currBalance;
    }
}