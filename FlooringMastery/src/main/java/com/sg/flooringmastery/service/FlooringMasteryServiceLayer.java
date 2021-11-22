/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryDaoException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.util.List;

/**
 *
 * @author Gordak
 */
public interface FlooringMasteryServiceLayer {

    public List<State> getAllStates() throws FlooringMasteryDaoException ;
    
    public List<Product> getAllProducts() throws FlooringMasteryDaoException ;
    
    public List<String> getAllStatesAbvs() throws FlooringMasteryDaoException;

    public Boolean isStateVerified(String orderStateAbv) throws FlooringMasteryDaoException;

    public State getState(String stateAbv);

    public Product getProduct(String productType);

    public void addToOrders(Order newOrder);

    public List<Order> getAllOrders();

    public void saveOrder(Order newOrder);

    
}
