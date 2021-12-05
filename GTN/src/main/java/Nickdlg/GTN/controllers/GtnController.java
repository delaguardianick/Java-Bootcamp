/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nickdlg.GTN.controllers;

import Nickdlg.GTN.models.Game;
import Nickdlg.GTN.models.Round;
import Nickdlg.GTN.service.GTNService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/gtn")
public class GtnController {

    private final GTNService service;
    
    public GtnController(GTNService service){
        this.service = service;
    }
    
    @PostMapping("/begin")
    public ResponseEntity beginGame(){
        String solution = service.generateSolution();

//        Start game
        int gameID = service.createGame(solution);
        
        ResponseEntity response = new ResponseEntity(gameID, HttpStatus.CREATED);
        return response;
    }
    
    /*
    {
        "guess" : "2451",
        "gameID" : 1
    }
    */
    @PostMapping("/guess")
    public Round guess(@RequestBody Round currRound){
        
        service.makeGuess(currRound);
        
        return currRound;
    }
    
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }
    
    @GetMapping("/game/{gameId}")
    public Game getGame(@PathVariable int gameId){
        return service.getGameToDisplay(gameId);
    }
    
    @GetMapping("/rounds/{gameId}")
    public List<Round> getRounds(@PathVariable int gameId){
        List<Round> rounds = service.getAllRoundsForGame(gameId);
//        List<LocalDateTime> roundTimes = rounds.stream().
//                map((round) -> round.getTime()).
//                collect(Collectors.toList());
        
//        Map<String, Round> jsonRounds = new HashMap<>();
//        
//        for (Round currRound : rounds){
//            String roundTime = currRound.getTime().format(
//                    DateTimeFormatter.ofLocalizedDateTime(
//                            FormatStyle.MEDIUM, FormatStyle.MEDIUM));
//            
//            jsonRounds.put(roundTime, currRound);
//        }
            
        return rounds;
    }
    
    @PostMapping("/calculate")
    public String calculate(int op1, String operator, int op2){
        int result = 0;
        switch (operator){
            case "+":
                result = op1 + op2;
                break;
            case "-":
                result = op1 - op2;
                break;
            case "*":
                result = op1 * op2;
                break;
            case "/":
                result = op1 / op2;
                break;
            default:
                String message = String.format("operator '%s' is invalid", operator);
                throw new IllegalArgumentException(message);
        }
        return String.format("%s %s %s = %s", op1, operator, op2, result);
    }
}
