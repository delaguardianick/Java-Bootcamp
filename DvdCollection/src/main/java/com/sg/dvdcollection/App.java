/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection;
import com.sg.dvdcollection.controller.DvdCollectionController;
import com.sg.dvdcollection.dao.*;
import com.sg.dvdcollection.ui.*;
        
/**
 * Main driver of the application
  Creates instances for the DAO, View and UserIO with 
  loosely coupled and corresponding implementations
 */
  
public class App {


    public static void main(String[] args){
        dvdCollectionDao myDao = new DvdCollectionDaoFileImpl();
        UserIO myIo = new UserIOConsoleImpl();
        DvdCollectionView myView = new DvdCollectionView(myIo);
        DvdCollectionController controller = 
                new DvdCollectionController(myDao, myView);
        controller.run();
    }
}
