/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.service;

import Nickdlg.GTN.models.Game;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gordak
 */
@Repository
public class GTNInMemoryService implements GTNService {
    

    @Override
    public int createGame(String solution){
        Game newGame = new Game(solution);
        int gameID = newGame.getGameID();
        
        return gameID;
    }

    @Override
    public String generateSolution() {
        String solution = "";
        while (solution.length() < 4) {
            String newDigit = String.valueOf(generateNumInRange(0, 9));
            
            if (solution.contains(newDigit))
            {
                continue;
            }
            solution += newDigit;
        }
        return solution;
    }
    
    @Override
    public int generateNumInRange(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
    
}
