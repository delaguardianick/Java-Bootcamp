package Nickdlg.GTN.models;

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
