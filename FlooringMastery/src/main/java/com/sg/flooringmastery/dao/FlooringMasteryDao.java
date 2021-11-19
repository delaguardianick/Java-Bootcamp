/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.State;
import java.util.List;

/**
 *
 * @author Gordak
 */
public interface FlooringMasteryDao {
    
    public void loadStates() throws FlooringMasteryDaoException;

    public List<State> getAllStates() throws FlooringMasteryDaoException;
    
    public void loadProducts() throws FlooringMasteryDaoException;

}
