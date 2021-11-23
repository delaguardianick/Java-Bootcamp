/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Gordak
 */
public interface FlooringMasteryDao {

    public int getLatestOrderNumber() throws FlooringMasteryDaoException;
    
    public void loadStates() throws FlooringMasteryDaoException;

    public List<State> getAllStatesObjects() throws FlooringMasteryDaoException;

    public List<Product> getAllProducts() throws FlooringMasteryDaoException;

    public void loadProducts() throws FlooringMasteryDaoException;
    
    public List<String> getAllStatesAbvs() throws FlooringMasteryDaoException;
    
    public String[] unmarshallItem(String line);
    
    public State getState(String stateAbv);

    public Product getProduct(String productType);
    
    public void addToOrders(Order newOrder);
    
    public List<Order> getAllOrders();
    
    public void saveOrder(Order newOrder) throws FlooringMasteryDaoException;

    public Order createNewOrder(LocalDate orderDate, String orderCustomerName,
            State orderState, Product orderProduct, Double orderArea);

    public List<Order> displayOrdersForThisDate(LocalDate date) 
             throws FlooringMasteryDaoException;
}
