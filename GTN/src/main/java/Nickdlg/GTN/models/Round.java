/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.models;

import java.time.LocalDateTime;

/**
 *
 * @author Gordak
 */
public class Round {
    
    private int roundID;
    private int gameID;
    private String solution;
    private String guess;
    private int exactMatches;
    private int partialMatches;
    private String roundResult;
    private LocalDateTime time;
    
    public Round(){
        
    }
}
