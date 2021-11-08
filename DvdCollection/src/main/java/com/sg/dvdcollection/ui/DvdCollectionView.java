/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.ui;
//import com.sg.dvdcollection.ui.*;

import com.sg.dvdcollection.dto.Dvd;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * View of the application
 */
public class DvdCollectionView {
    
    private UserIO io;
    
//    constructor to implement corresponding inteface
    public DvdCollectionView(UserIO io){
        this.io = io;
    }

//    prints the menu
    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. List all DVDs");
        io.print("2. Add DVD to collection");
        io.print("3. Search and display DVD info");
        io.print("4. Delete DVD from collection");
        io.print("5. Edit DVD info");
        io.print("6. Load collection from file");            
        io.print("7. Save collection to file");
        io.print("8. Exit");

        return io.readInt("Please select from the " + 
                "above choices.", 1, 8);
    }
    
//    prompt user for information of new DVD being added
    public Dvd getNewDvdInfo(){
        String title = io.readString("Enter the title of the DVD: ");
        String releaseDate = io.readString("Enter the Release Date: ");
        String mpaaRating = io.readString("Enter the MPAA Rating: ");
        String directorName = io.readString("Enter the Director's Name: ");
        String studioName = io.readString("Enter the Studio's Name: ");
        String userNote = io.readString("Enter a note for the DVD: ");

        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudioName(studioName);
        
        currentDvd.setUserNotes(userNote);
        
        return currentDvd;
    }
    
    public void displayCreateDvdBanner() {
        io.print("=== Adding DVD to collection ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully added. Please hit enter to continue");
    }
    
//    Lists all dvds in the collection and their information
    public void listDvdCollection(List<Dvd> dvdList){
        for (Dvd currentDvd : dvdList){
            displayDvdInfo(currentDvd);
        }
        io.readString("Please hit enter to continue");
    }
    
    public void displayListCollectionBanner(){
        io.print("=== Displaying all DVDs ===");
    }
    
    public String inputTitleToSearch(){
        String title = io.readString("Enter title of DVD to search: ");
        return title;
    }
    
//    Displays information for 1 dvd object
    public void displayDvdInfo(Dvd reqDvd){
        String dvdInfo = String.format("Title: %s \n"
                    + "1. Release Date: %s \n"
                    + "2. Mpaa Rating: %s \n"
                    + "3. Director's Name: %s \n"
                    + "4. Studio Name: %s \n"
                    + "5. User notes: %s \n", 

                    reqDvd.getTitle(),
                    reqDvd.getReleaseDate(),
                    reqDvd.getMpaaRating(),
                    reqDvd.getDirectorName(),
                    reqDvd.getStudioName(),
                    reqDvd.getUserNotes()
                    );
            
        io.print(dvdInfo);
    }
    
//  prompts user which information they want to change for a dvd record
//  and changes the object's properties to reflect it
    public void editDvd(Dvd currDvd){
        String resp = "y";
        
        while (resp.equals("y")){
            int fieldToEdit = io.readInt("Which field would you like to edit? "
                    + "(1-5)", 1, 5);
            String newInfo = io.readString("Enter what the new information"
                    + " should be:");
            
            switch(fieldToEdit){
                case 1:
                    currDvd.setReleaseDate(newInfo);
                    io.print("Release date succesfully changed");
                    break;

                case 2:
                    currDvd.setMpaaRating(newInfo);
                    io.print("MPAA Rating succesfully changed");
                    break;

                case 3:
                    currDvd.setDirectorName(newInfo);
                    io.print("Director's Name succesfully changed");
                    break;

                case 4:
                    currDvd.setStudioName(newInfo);
                    io.print("Studio Name succesfully changed");
                    break;
                
                case 5:
                    currDvd.setUserNotes(newInfo);
                    io.print("User notes succesfully changed");
                    break;
           }
            
            String keepEditing = io.readString("\nWould you like to keep "
                    + "editing the DVD's info? (y/n)");
            
            if (keepEditing.equals("n")){
                resp = "n";
            }
        }
    }
    
//  confirms if user wants to remove DVD from collection
    public Boolean confirmRemoveDvd(){
        String resp = io.readString("Are you sure you want to "
                + "remove this DVD? (y/n)");
        
        if (resp.equals("y")){
            io.print("DVD deleted");
            return true;
        }
        io.print("DVD not deleted");
        return false;
             
    }
    
    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!");
    }
    
    public void displayExitBanner(){
        io.print("Bye!");
    }
    
    public void displayErrorMessage(String errorMsg){
        io.print("===ERROR===");
        io.print(errorMsg);       
    }
    
    public void displayCollectionLoaded(){
        io.print("Collection succesfully loaded from the file");
    }
    
    public void displayCollectionSavedToFile(){
        io.print("Collection succesfully saved to the file");
    }
}
