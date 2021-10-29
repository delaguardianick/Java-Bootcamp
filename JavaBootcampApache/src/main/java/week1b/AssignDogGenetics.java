package week1b;

import Week1.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class AssignDogGenetics {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();        
        int numBreeds = 5;

        ArrayList<Integer> breedRec = new ArrayList<Integer>(numBreeds);

        int total = 100;

        // For the first breed, choose a random num from 0 to total(100)
        // For the second, choose a num from 0 to (total -previous breed percentage)
        // Repeat, this assures they add up to total
        // Save the percentages in an array for easy access
        for (int i=0; i < numBreeds-1; i++){
            int breedPercent = rand.nextInt(total);
            breedRec.add(breedPercent);
            total -= breedPercent;
        }
        // For the last breed, add the remaining total
        breedRec.add(total);

        // Take input and display
        System.out.println("What is your dog's name?");
        String dogName = sc.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here.");
        System.out.println(dogName + " is:" + "\n");

        // Access array and print each percent
        // For formating inside printf, %d is for displaying integers
        // the double %% is to actually display the % symbol
        System.out.printf("%d%% St. Bernard\n", breedRec.get(0));
        System.out.printf("%d%% Chihuahua\n", breedRec.get(1));
        System.out.printf("%d%% Dramatic RedNosed Asian Pug\n", breedRec.get(2));
        System.out.printf("%d%% Common Cur\n", breedRec.get(3));
        System.out.printf("%d%% King Doberman\n", breedRec.get(4));
        

    }
}
