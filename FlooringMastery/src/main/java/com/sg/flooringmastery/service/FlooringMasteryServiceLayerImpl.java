/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoException;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.util.List;

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
    
}
