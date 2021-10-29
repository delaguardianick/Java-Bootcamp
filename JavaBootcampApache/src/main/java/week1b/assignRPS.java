package week1b;

import Week1.*;
import java.util.Random;
import java.util.Scanner;

public class assignRPS {

    // Where the game starts
    public static void start(){
        // ask user for number of rounds
        int numRounds = setRounds();
        int ties = 0;
        int userWins = 0;
        int userLosses = 0;

        // Play each round and keep track of win/tie/loss
        for (int i = 0; i < numRounds; i++){
            System.out.println("Round " + (i+1) + ":");
            int result = playRound();

            switch (result){
                case 1:
                    System.out.println("You win this round!");
                    userWins++;
                    break;
                case 0:
                    System.out.println("You tied this round!");
                    ties++;
                    break;
                case -1:
                    System.out.println("You lost this round!");
                    ++userLosses;
                    break;
            }

            System.out.println("Wins: " + userWins + ", Ties: " + ties + ", Losses: " + userLosses + "\n");
        }

        // Once all rounds are played
        // Display final result according to wins/losses
        // End of game
        if (userWins > userLosses){
            System.out.println("You win the game! :)");
            System.exit(0);
        } 
        if (userWins < userLosses){
            System.out.println("You lose the game! :(");
            System.exit(0);
        }
        else{
            System.out.println("The final result is a tie!");
            System.exit(0);
        }
    }

    // Ask user to enter num of rounds, returns it.
    // If ouside range, exit
    public static int setRounds(){
        Scanner sc = new Scanner(System.in);

        // Ask for number of rounds
        // check for invalid inputs
        int numRounds = 0;
        do{
            System.out.println("Enter number of rounds (1-10):");
            
            // try{
            //     if (!sc.hasNextInt()){
            //         numRounds = sc.nextInt();
            //     }
            // }
            // catch(Exception e){
            //     System.out.println("Please enter a number");
            // }

            // TO DO Validate strings
            while (!sc.hasNextInt()){
                
                numRounds = sc.nextInt();
                System.out.println("Not a valid input");
            }
            numRounds = sc.nextInt();

        }while(numRounds < 1 || numRounds > 10);

        return numRounds;
    }

    public static int userChoice(){
        Scanner sc = new Scanner(System.in);
        String choiceStr = "";
        do {
            System.out.println("Choose r, p or s - (Rock) (Paper) (Scissors)");
            choiceStr = sc.next();

        }while(!(choiceStr.equals("r") || choiceStr.equals("p") || choiceStr.equals("s")));
        // check for invalid input
        // System.out.println("input: " + choiceStr.strip());

        int choiceNum = rpsToInt(choiceStr);
        return choiceNum;
    }

    // Main game logic
    // Request user to choose R, P, or S. Generate computer choice
    // Compare and return winner
    /*
    GAME LOGIC:
    Rock = 1
    Paper = 2
    Scissors = 3

    By calculating choiceDiff = (userChoice - computerChoice), the difference 
    is telling of the winner. 
    
    if:
        user chooses paper (2) and
        NPC chooses rock (1)
        paper - rock = 2 - 1 = 1 = USER WIN

    That is choiceDiff.

    By checking all possible scenarios, a choiceDiff table can be made:
        user WIN if -2 or 1
        user TIE if 0
        user LOSE if -1 or 2
    
    method returns winner of round:
        1 = user wins
        0 = user ties
        -1 = user loses
    */
    public static int playRound(){
        int userChoice = userChoice();

        Random rand = new Random();
        int npcChoice = rand.nextInt(3) + 1;
        
        System.out.print("\nYou: " + intToRPS(userChoice));
        System.out.println(", Computer: " + intToRPS(npcChoice));

        int choiceDiff = userChoice - npcChoice;
        int winloss = 0;

        switch(choiceDiff){
            case 1:
                // WIN
                winloss = 1;
                break;
            case -2:
                // WIN
                winloss = 1;
                break;
            case 0:
                // TIE
                winloss = 0;
                break;
            case -1: 
                // LOSE
                winloss = -1;
                break;
            case 2:
                // LOSE
                winloss = -1;
                break;
        }
        return winloss;
        

        // All scenarios:
        // user - npc
        // rock - paper = 1 - 2 = -1 LOSE
        // rock - rock = 0 = TIE
        // rock - scissors = 1 - 3 = -2 WIN

        // paper - paper = 2 - 2 = 0 TIE
        // paper - rock = 2 - 1 = 1 = WIN
        // paper - scissors = 2 - 3 = -1 = LOSE

        // scissors - paper = 3 - 2 = 1 WIN
        // scissors - rock = 3 - 1 = 2 LOSE
        // scissors - scissors = 3 - 3 = TIE
    }

    // Translate from 1, 2, 3 to Rock, paper, or scissors
    public static String intToRPS(int rpsVal){
        String rps = "a";
        switch(rpsVal){
            case 1:
                rps = "Rock";
                break;
            case 2:
                rps = "Paper";
                break;
            case 3:
                rps = "Scissors";
                break;
        }
        return rps;
    }

    public static int rpsToInt(String choiceStr){
        int choiceNum = -1;

        switch(choiceStr){
            case "r":
                choiceNum = 1;
                break;
            case "p":
                choiceNum = 2;
                break;
            case "s":
                choiceNum = 3;
                break;
        }
        return choiceNum;
    }



    public static void main(String[] args) {
        start();
    }
}
