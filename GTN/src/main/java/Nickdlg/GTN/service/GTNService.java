/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.service;

import Nickdlg.GTN.models.Round;

/**
 *
 * @author Gordak
 */
public interface GTNService {

    public String generateSolution();
    
    public int generateNumInRange(int min, int max);

    public int createGame(String solution);

    public void makeGuess(Round currRound);
    
}
