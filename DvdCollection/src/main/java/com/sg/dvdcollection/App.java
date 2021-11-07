/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection;
import com.sg.dvdcollection.controller.DvdCollectionController;
/**
 *
 * @author Gordak
 */
public class App {
    
    public static void main(String[] args){
        DvdCollectionController controller = new DvdCollectionController();
        controller.run();
    }
}
