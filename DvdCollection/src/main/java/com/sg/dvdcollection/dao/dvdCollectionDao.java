package com.sg.dvdcollection.dao;
import com.sg.dvdcollection.dto.Dvd;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gordak
 */
public interface dvdCollectionDao {
    
    /**
     * Adds the given dvd to the collection and associates it with the given
     * title. If there is already a dvd associated with the given
     * title it will return that dvd object, otherwise it will
     * return null.
     *
     * @param title title of dvd with which dvd is to be associated
     * @param dvd dvd to be added to the roster
     * @return the dvd object previously associated with the given
     * dvd id if it exists, null otherwise
     */
    Dvd addDvd(String title, Dvd dvd);
    
//  @return collection of DVDs
    List<Dvd> getAllDvds();
    
//  @return dvd object associated with title
    Dvd getDvd(String title);
    
//  @return removed dvd object
    Dvd removeDvd(String title);
    
// @return edited dvd object
    Dvd editDvd(String title, int fieldToEdit, String newInfo);
    
//  @return requested dvd object
    Dvd searchDvd(String title);
    
    
    
    
}
