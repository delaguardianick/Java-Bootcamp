/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.models;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Gordak
 */
public class Game {
    private static int count = 1;
    private int gameID;
    private String solution;
    private Boolean finished;
    
    
    public Game(){
        this.finished = false;
    }
    
    public Game(String solution){
        this.solution = solution;
        this.gameID = count++;
        this.finished = false;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
