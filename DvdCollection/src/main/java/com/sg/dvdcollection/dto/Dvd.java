/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dto;

/**
 *
 * @author Gordak
 */
public class Dvd {
//    private String dvdID;
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studioName;    
    private String userNotes;

    public Dvd(String title){
        this.title = title;
    }
    
    // Getters
//    public String getDvdID(){
//        return dvdID;
//    }
    
    public String getTitle(){
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getStudioName() {
        return studioName;
    }

    public String getUserNotes() {
        return userNotes;
    }

    // Setters
//    public void setDvdId(String dvdID){
//        this.dvdID = dvdID;
//    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }
    
    
}
