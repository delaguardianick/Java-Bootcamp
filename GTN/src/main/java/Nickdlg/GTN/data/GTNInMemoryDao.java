/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.data;

import Nickdlg.GTN.models.Game;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gordak
 */
public class GTNInMemoryDao implements GTNDao {
    
    private Map<String, Game> games = new HashMap<>();

}
