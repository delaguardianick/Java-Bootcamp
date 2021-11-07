/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.controller;

import com.sg.dvdcollection.dao.DvdCollectionDaoFileImpl;
import com.sg.dvdcollection.dao.dvdCollectionDao;
import com.sg.dvdcollection.dto.Dvd;
import com.sg.dvdcollection.ui.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gordak
 */
public class DvdCollectionController {
    
    private UserIO io = new UserIOConsoleImpl();
    private DvdCollectionView view = new DvdCollectionView();
    private dvdCollectionDao dao = new DvdCollectionDaoFileImpl();
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        while (keepGoing){
            menuSelection = getMenuSelection();
            
            switch (menuSelection) {
                case 1:
//                    io.print("List DVDs");
                    listDvds();
                    break;
                case 2:
//                    io.print("Add DVD");
                    addDvd();
                    break;
                case 3:
//                    io.print("Search DVD");
                    searchAndDisplayDvd();
                    break;
                case 4:
//                    io.print("Delete DVD");
                    removeDvd();
                    break;    
                case 5:
//                    io.print("Edit DVD");
                    editDvd();
                    break;
                case 6:
                    io.print("Reset Collection");
                    break;
                case 7:
                    io.print("Save Collection");
                    break;
                case 8:
                    keepGoing = false;
                    break;
                default:
                    view.displayUnknownCommandBanner();
            }
        }
        view.displayExitBanner();
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void addDvd() {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }
    
    private void listDvds() {
        view.displayListCollectionBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.listDvdCollection(dvdList);
    }
    
    private Dvd searchAndDisplayDvd(){
        String title = view.searchDvd();
        Dvd currDvd = dao.searchDvd(title);
        view.displayDvdInfo(currDvd);
        return currDvd;
    }
    
    private void editDvd(){     
        Dvd currDvd = searchAndDisplayDvd();
        view.editDvd(currDvd);
    }    
    
    private void removeDvd(){
//        Dvd currDvd = searchAndDisplayDvd();
        String title = view.searchDvd();
        Boolean delete = view.removeDvd();
        if (delete){
            dao.removeDvd(title);
        }
    }
    
    public void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    public void exitMessage(){
        view.displayExitBanner();
    }
}
