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
    
    private static int id = 1;
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
        this.orderNumber = id++;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Order.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = new BigDecimal(area);
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void calcMaterialCost() {
        this.materialCost = this.area.multiply(this.costPerSquareFoot);
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void calcLaborCost() {
        this.laborCost = this.area.multiply(this.laborCostPerSquareFoot);
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void calcTax() {
        this.tax = (this.materialCost.add(this.laborCost)).
                multiply(this.taxRate.divide(new BigDecimal("100")));
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void calcTotal() {
        this.total = this.materialCost.add(this.laborCost).add(this.tax);
    }
    
    public void calcOrderFields(){
        calcMaterialCost();
        calcLaborCost();
        calcTax();
        calcTotal();
    }
    
    
}
