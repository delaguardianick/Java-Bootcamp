package Nickdlg.GTN.controllers;

import Nickdlg.GTN.models.Game;
import Nickdlg.GTN.models.Round;
import Nickdlg.GTN.service.GTNService;
import java.util.List;
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
    
    /*
    Responds to a POST begin call
    starts a new game
    @returns 201 CREATED and gameID of new game
    */
    @PostMapping("/begin")
    public ResponseEntity beginGame(){
        String solution = service.generateSolution();

        int gameID = service.createGame(solution);
        
        ResponseEntity response = new ResponseEntity(gameID, HttpStatus.CREATED);
        return response;
    }
    
    /*
    Input format JSON:
        {
            "guess" : "2451",
            "gameID" : 1
        }
    
    Responds to a POST guess call
    makes a guess for the corresponding game
    @returns filled round object, with results of round
    */
    @PostMapping("/guess")
    public Round guess(@RequestBody Round currRound){
        
        service.makeGuess(currRound);
        
        return currRound;
    }
    
    /*
    Displays all games, if game is not finished, hides the solution
    @returns list of all games
    */
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }
    
    /*
    Displays the game for a specific gameID
    if game is not finished, hides the solution
    @returns requested game
    */
    @GetMapping("/game/{gameId}")
    public Game getGame(@PathVariable int gameId){
        return service.getGameToDisplay(gameId);
    }
    
    /*
    @returns all rounds of a specific game
    */
    @GetMapping("/rounds/{gameId}")
    public List<Round> getRounds(@PathVariable int gameId){
            
        return service.getAllRoundsForGame(gameId);
    }
    
}
