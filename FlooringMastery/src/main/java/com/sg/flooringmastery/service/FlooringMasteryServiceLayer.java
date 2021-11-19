/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryDaoException;
import com.sg.flooringmastery.dto.State;
import java.util.List;

/**
 *
 * @author Gordak
 */
public interface FlooringMasteryServiceLayer {

    public List<State> getAllStates() throws FlooringMasteryDaoException ;
    
}
