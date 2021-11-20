/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author Gordak
 */
public class State {
    private String stateAbv;
    private String stateFull;
    private BigDecimal taxRate;

    public State(String stateAbv){
        this.stateAbv = stateAbv;
    }

    public String getStateAbv() {
        return stateAbv;
    }

    public void setStateAbv(String stateAbv) {
        this.stateAbv = stateAbv;
    }

    public String getStateFull() {
        return stateFull;
    }

    public void setStateFull(String stateFull) {
        this.stateFull = stateFull;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = new BigDecimal(taxRate);
    }
    
    
}
