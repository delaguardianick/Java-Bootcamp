/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.controller;

import com.sg.dvdcollection.dao.DvdCollectionDaoException;
import com.sg.dvdcollection.dao.DvdCollectionDaoFileImpl;
import com.sg.dvdcollection.dao.dvdCollectionDao;
import com.sg.dvdcollection.dto.Dvd;
import com.sg.dvdcollection.ui.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Controller of the application
 * delegates the work to the DAO and VIEW
 */
public class DvdCollectionController {
    
    private UserIO io = new UserIOConsoleImpl();
    
//    Tightly coupled dependency injection:
//    private dvdCollectionDao dao = new DvdCollectionDaoFileImpl();
//    private DvdCollectionView view = new DvdCollectionView();
    
//    Loosely coupled dependency injection: + constructor
    private DvdCollectionView view;
    private dvdCollectionDao dao;
    
    public DvdCollectionController(dvdCollectionDao dao, 
            DvdCollectionView view){
        this.dao = dao;
        this.view = view;
    }
    
//  main function
//  displays menu and calls appropiate functions in the dao and view
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try{
            
        while (keepGoing){
            menuSelection = getMenuSelection();
            
            switch (menuSelection) {
                case 1:
                    listDvds();
                    break;
                case 2:
                    addDvd();
                    break;
                case 3:
                    searchAndDisplayDvd();
                    break;
                case 4:
                    removeDvd();
                    break;    
                case 5:
                    editDvd();
                    break;
                case 6:
                    loadCollection();
                    break;
                case 7:
                    saveCollection();
                    break;
                case 8:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        saveCollection();
        exitMessage();
        }catch(DvdCollectionDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
//    adds a dvd to the collection
    private void addDvd() throws DvdCollectionDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }
    
//    lists all dvds in the collection
    private void listDvds() throws DvdCollectionDaoException{
        view.displayListCollectionBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.listDvdCollection(dvdList);
    }
    
//    searches and displays info of a specific dvd in the collection
    private Dvd searchAndDisplayDvd() throws DvdCollectionDaoException {
        String title = view.inputTitleToSearch();
        Dvd currDvd;
        try {
            currDvd = dao.searchDvd(title);
            view.displayDvdInfo(currDvd);
            return currDvd;

        }
        catch (DvdCollectionDaoException e){
            throw new DvdCollectionDaoException("Dvd not found");
        }
    }
    
//    edits a specific dvd in the collection
    private void editDvd() throws DvdCollectionDaoException{     
        Dvd currDvd = searchAndDisplayDvd();
        view.editDvd(currDvd);
        dao.saveCollection();
    }    
    
//    removes a dvd from the collection
    private void removeDvd() throws DvdCollectionDaoException{
//        Dvd currDvd = searchAndDisplayDvd();
        String title = view.inputTitleToSearch();
        Boolean delete = view.confirmRemoveDvd();
        if (delete){
            dao.removeDvd(title);
        }
    }
    
//    loads the collection from an external file into a hashmap
//    unmarhsalls data
    private void loadCollection() throws DvdCollectionDaoException{
        dao.loadCollection();
        view.displayCollectionLoaded();
    }
    
//    saves the collection of dvds in a hashmap to an external txt file
//    marshalls data
    private void saveCollection() throws DvdCollectionDaoException{
        dao.saveCollection();
        view.displayCollectionSavedToFile();
    }
    
    public void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    public void exitMessage(){
        view.displayExitBanner();
    }
    
    public void errorMessage(String errorMsg){
        view.displayErrorMessage(errorMsg);
    }
}
