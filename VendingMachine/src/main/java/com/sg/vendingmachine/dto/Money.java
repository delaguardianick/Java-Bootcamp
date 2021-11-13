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

    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
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
    
    public void compareToMoney(Money itemPrice){
        BigDecimal balanceBD = this.getBalance();
        BigDecimal itemPriceBD = itemPrice.getBalance();
        
//      balanceBD > itemPriceBD -> 1  
        switch (balanceBD.compareTo(itemPriceBD)){
            case 1:
                System.out.println("You can afford item, continue");
                break;
            case 0:
                System.out.println("You barely afford item, continue");
                break;
            case -1:
                System.out.println("Insufficient funds");
                break;
            default:
                System.out.println("no switch case met compareToMoney");
                break;
        }
        

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
        setDollars(dollarsBD.intValue());
        currBalance = currBalance.subtract(dollarsBD);
        
        changeInCoins[0] = getDollars();
        
//      QUARTERS
        currBalance = splitQDNP(currBalance, quarterValue, Coin.QUARTER);
        changeInCoins[1] = getQuarters();

//        DIMES
        currBalance = splitQDNP(currBalance, dimeValue, Coin.DIME);
        changeInCoins[2] = getDimes();

//        NICKELS
        currBalance = splitQDNP(currBalance, nickelValue, Coin.NICKEL);
        changeInCoins[3] = getNickels();

//        PENNIES
        currBalance = splitQDNP(currBalance, pennyValue, Coin.PENNY);
        changeInCoins[4] = getPennies();
        
        return changeInCoins;
    }
    
    public BigDecimal splitQDNP(BigDecimal currBalance, BigDecimal coinVal, 
            Coin coinType ){
        
        BigDecimal numOfCoin = currBalance.divide(coinVal, 2, 
                  RoundingMode.HALF_UP);
        
        if (numOfCoin.intValue() >= 1){
            
            switch(coinType){
                case QUARTER:
                    setQuarters(numOfCoin.intValue());
                    break;

                case DIME:
                    setDimes(numOfCoin.intValue());
                    break;
                    
                case NICKEL:
                    setNickels(numOfCoin.intValue());
                    break;
                    
                case PENNY:
                    setPennies(numOfCoin.intValue());
                    break;
            }
            currBalance = currBalance.subtract(
                    coinVal.multiply(BigDecimal.valueOf(numOfCoin.intValue())));

            return currBalance;
        } 
        return currBalance;
    }
}
