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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gordak
 */
public class FlooringMasteryServiceLayerImpl 
        implements FlooringMasteryServiceLayer {
    
        private FlooringMasteryDao dao;

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

    @Override
    public void saveOrder(Order newOrder){
            try {
                dao.saveOrder(newOrder);
            } catch (FlooringMasteryDaoException ex) {
                System.out.println("Couldnt save order");
            }
    }
    
    @Override
    public Order createNewOrder(LocalDate orderDate, String orderCustomerName, 
            State orderState, Product orderProduct, Double orderArea){
        
        return dao.createNewOrder(orderDate,
                orderCustomerName, orderState, orderProduct, orderArea);
    }
    
    @Override
    public List<Order> displayOrdersForThisDate(LocalDate date) 
             throws FlooringMasteryDaoException{
        return dao.displayOrdersForThisDate(date);
    }


    
}
