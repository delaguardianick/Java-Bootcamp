/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * 
 */
public class DvdCollectionDaoFileImpl implements dvdCollectionDao{
    
    private Map<String, Dvd> dvds = new HashMap<>();
    
    public static final String COLLECTION_FILE = "dvdCollection.txt";
    public static final String DELIMITER = "::";

//  Adds a dvd to the hashmap
    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdCollectionDaoException{
        Dvd prevDvd = dvds.put(title, dvd);
        saveCollection();
        return prevDvd;
    }

//  @returns a list with all the Dvd objects in the hashmap
    @Override
    public List<Dvd> getAllDvds() throws DvdCollectionDaoException{
        loadCollection();
        return new ArrayList<Dvd>(dvds.values());
    }

//  removes a DVD from the hashmap
//    @returns removed DVD
    @Override
    public Dvd removeDvd(String title) throws DvdCollectionDaoException{
        loadCollection();
        Dvd prevDvd = dvds.remove(title);
        saveCollection();
        return prevDvd;
    }
    
//    searches for a title of a dvd inside the hashmap
//    @returns the found dvd object
    @Override
    public Dvd searchDvd(String title) throws DvdCollectionDaoException{
        
        Dvd currDvd = dvds.get(title);
        return currDvd;
    }
    
//    loads the dvd collection from an external txt file
//    unmarshalls the dvd objects

    @Override
    public void loadCollection() throws DvdCollectionDaoException{
        Scanner sc;
        
        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(COLLECTION_FILE)));
        }
        
        catch (FileNotFoundException e){
            throw new DvdCollectionDaoException("Couldn't load file");
        }
        
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            Dvd unmarshalledDvd = unmarshallDvd(line);
            dvds.put(unmarshalledDvd.getTitle(), unmarshalledDvd);
           
        }
        sc.close();
    }
    
//    Saves the dvd collection into an external txt file
//    marshalls the dvd objects
    @Override
    public void saveCollection() throws DvdCollectionDaoException{
        PrintWriter out;
        
        try{
            
            out = new PrintWriter(new FileWriter(COLLECTION_FILE));
        }
        catch (IOException e){
            throw new DvdCollectionDaoException("Couldn't save collection");
        }

        List<Dvd> dvds = getAllDvds();
        for (Dvd dvdAsObject : dvds){
            String dvdAsText = marshallStudent(dvdAsObject);
            
            out.println(dvdAsText);
            out.flush();
            }
        out.close();
    }
    
//  transforms Dvd objects into a marshalled string with delimiters
//      [title::releaseDate::mpaaRating::director::studio:notes]  

    @Override
    public String marshallStudent(Dvd dvdAsObject){
        String title = dvdAsObject.getTitle();
        String releaseDate = dvdAsObject.getReleaseDate();
        String mpaaRating = dvdAsObject.getMpaaRating();
        String directorName = dvdAsObject.getDirectorName();
        String studioName = dvdAsObject.getStudioName();
        String userNotes = dvdAsObject.getUserNotes();
        
        String dvdAsText = String.format("%s::%s::%s::%s::%s::%s",
                title, releaseDate, mpaaRating, directorName,
                studioName, userNotes);
         
        return dvdAsText;
    }
    
//  transforms marshalled string with delimiters into Dvd objects
    @Override
    public Dvd unmarshallDvd(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        
        String title = dvdTokens[0]; 
        Dvd dvdFromFile = new Dvd(title);
        
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudioName(dvdTokens[4]);
        dvdFromFile.setUserNotes(dvdTokens[5]);

        return dvdFromFile;
    }
}
