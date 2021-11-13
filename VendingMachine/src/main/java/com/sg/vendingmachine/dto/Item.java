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
public class Item {
    private String name;
    private Money price;
    private int unitsInStock;
    
    public Item(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price.toString();
    }
    
    public Money getPriceMoney(){
        return this.price;
    }

    public void setPrice(String price) {
        BigDecimal priceBD = new BigDecimal(price);
        this.price = new Money(priceBD);
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }
    
    
}
