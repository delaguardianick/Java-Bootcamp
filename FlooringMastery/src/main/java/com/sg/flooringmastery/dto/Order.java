/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Gordak
 */
public class Order {
    private Integer orderNumber;
    private LocalDate date;
    private String customerName;
    private String state;
    private String productType;
    private BigDecimal area;
    
    private BigDecimal taxRate;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost ;
    private BigDecimal laborCost ;
    private BigDecimal tax;
    private BigDecimal total;

    public Order(){
        
    }
}
