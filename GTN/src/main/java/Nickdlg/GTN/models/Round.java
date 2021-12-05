package Nickdlg.GTN.models;

import java.time.LocalDateTime;

public class Round {
    
    private int roundID;
    private int gameID;
    private String guess;
    private int exactMatches;
    private int partialMatches;
    private String roundResult;
    private LocalDateTime time;
    private Boolean lastRound ;
    
    public Round(){
        this.time = LocalDateTime.now();
        this.lastRound = false;
    }
    
    public Round(int roundID){
        this.roundID = roundID;
    }

    public int getRoundID() {
        return roundID;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public int getExactMatches() {
        return exactMatches;
    }

    public void setExactMatches(int exactMatches) {
        this.exactMatches = exactMatches;
    }

    public int getPartialMatches() {
        return partialMatches;
    }

    public void setPartialMatches(int partialMatches) {
        this.partialMatches = partialMatches;
    }

    public String getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(String roundResult) {
        this.roundResult = roundResult;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Boolean getLastRound() {
        return lastRound;
    }

    public void setLastRound(Boolean lastRound) {
        this.lastRound = lastRound;
    }
    
    
}
