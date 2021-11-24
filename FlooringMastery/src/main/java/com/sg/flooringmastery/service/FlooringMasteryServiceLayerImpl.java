/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gordak
 */
@Component
public class FlooringMasteryServiceLayerImpl 
        implements FlooringMasteryServiceLayer {
    
        private FlooringMasteryDao dao;
        
    @Autowired
    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao) {
        this.dao = dao;
    }

    @Override
    public List<State> getAllStates() throws FlooringMasteryDaoException {
        return dao.getAllStatesObjects();
    }

    @Override
    public List<Product> getAllProducts() throws FlooringMasteryDaoException {
        return dao.getAllProducts();
    }

    @Override
    public List<String> getAllStatesAbvs() throws FlooringMasteryDaoException {
        return dao.getAllStatesAbvs();
    }

    /*
    Verifies if state is in the list of available states
    @returns weather the state is valid
    */
    @Override
    public Boolean isStateVerified(String orderStateAbv) 
            throws FlooringMasteryDaoException {
        
        List<String> allStateAbv = dao.getAllStatesAbvs();
        
        if (allStateAbv.contains(orderStateAbv)){
//            Verified
             return true;
        }
        return false;
    }

    @Override
    public State getState(String stateAbv) {
        return dao.getState(stateAbv);
    }

    @Override
    public Product getProduct(String productType) {
        return dao.getProduct(productType);
    }

    @Override
    public void addToOrders(Order newOrder) {
        dao.addToOrders(newOrder);
    }
    
    @Override
    public List<Order> getAllOrders(){
        return dao.getAllOrders();
    }

    /*
    Handles exception
    */
    @Override
    public void saveOrder(Order newOrder){
        try {
            dao.saveOrder(newOrder);
        } catch (FlooringMasteryDaoException ex) {
            System.out.println("Couldnt save order");
        }
    }
    
    /*
    Calls for Order object to be created 
    and finds out the order number it should have !!
    @returns the new order
    */
    @Override
    public Order createNewOrder(LocalDate orderDate, String orderCustomerName, 
            State orderState, Product orderProduct, Double orderArea){
        
        Order newOrder = dao.createNewOrder(orderDate,
                orderCustomerName, orderState, orderProduct, orderArea);
        
        newOrder.setOrderNumber(getLatestOrderNumber());
        return newOrder;
    }
    
    @Override
    public List<Order> displayOrdersForThisDate(LocalDate date) 
             throws FlooringMasteryDaoException{
        return dao.displayOrdersForThisDate(date);
    }

    
    @Override
    public int getLatestOrderNumber(){
        try {
            return dao.getLatestOrderNumber();
        } catch (FlooringMasteryDaoException ex) {
            System.out.println("Can't get latest order number");
            return -1;
        }
    }
    
    @Override
    public void saveAllOrders(List<Order> ordersForThisDate){
            try {
                dao.saveAllOrders(ordersForThisDate);
            } catch (FlooringMasteryDaoException ex) {
            }
    }

}
