/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.Dvd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gordak
 */
public class DvdCollectionDaoFileImpl implements dvdCollectionDao{
    
    private Map<String, Dvd> dvds = new HashMap<>();

    @Override
    public Dvd addDvd(String title, Dvd dvd) {
        Dvd prevDvd = dvds.put(title, dvd);
        return prevDvd;
    }

    @Override
    public List<Dvd> getAllDvds() {
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dvd removeDvd(String title) {
        Dvd prevDvd = dvds.remove(title);
        return prevDvd;
    }
    
    @Override
    public Dvd editDvd(String title, int fieldToEdit, String newInfo){
        Dvd currDvd = dvds.get(title);
        
        switch(fieldToEdit){
            case 1:
                currDvd.setReleaseDate(newInfo);
                
            case 2:
                currDvd.setMpaaRating(newInfo);
                
            case 3:
                currDvd.setDirectorName(newInfo);
           
            case 4:
                currDvd.setStudioName(newInfo);
        }
        return currDvd;
//        NOT WORKING
    }
    
    @Override
    public Dvd searchDvd(String title){
        Dvd currDvd = dvds.get(title);
        return currDvd;
    }
}
