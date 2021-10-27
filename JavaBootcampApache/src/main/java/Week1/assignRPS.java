import java.util.Random;
import java.util.Scanner;

public class assignRPS {

    public static void start(){
        int numRounds = setRounds();
        int ties = 0;
        int userWins = 0;
        int userLosses = 0;

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

    public static int setRounds(){
        Scanner sc = new Scanner(System.in);

        // Ask for number of rounds
        System.out.println("Enter number of rounds (1-10):");
        // check for invalid inputs
        int numRounds = sc.nextInt();

        if (numRounds < 1 || numRounds > 10){
            System.out.println("Outside range, exiting.");
            System.exit(0);
        }

        // sc.close();
        return numRounds;
    }

    public static int userChoice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose r, p or s - (Rock) (Paper) (Scissors)");
        // check for invalid input
        String choiceStr = sc.next();
        // System.out.println("input: " + choiceStr.strip());
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
        };
        return choiceNum;
    }

    public static int playRound(){
        int userChoice = userChoice();

        Random rand = new Random();
        int npcChoice = rand.nextInt(3) + 1;
        
        System.out.println("You: " + intToRPS(userChoice));
        System.out.println("Computer: " + intToRPS(npcChoice));

        int userResult = userChoice - npcChoice;
        int winloss = 0;

        switch(userResult){
            case 1:
                // System.out.println("You win this round!");
                winloss = 1;
                break;
            case -2:
                // System.out.println("You win this round!");
                winloss = 1;
                break;
            case 0:
                // System.out.println("You tied this round!");
                winloss = 0;
                break;
            case -1:
                // System.out.println("You lost this round!");
                winloss = -1;
                break;
            case 2:
                // System.out.println("You win this round!");
                winloss = -1;
                break;
        }

        return winloss;
        // Return 1 = user wins
        // Return 0 = user ties
        // Return -1 = user loses
        // Rock   Paper   Scissors
        // 1      2        3

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

        // WIN -> -2, 1
        // TIE -> 0
        // LOSE -> -1, 2

    }

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

    public static void main(String[] args) {
        start();
    }
}
