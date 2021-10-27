import java.util.Random;
import java.util.Scanner;
import java.util.Random;

public class TuesGuessMeMore {
    public static void main(String[] args) {

        // int start = 2;
        // int stop = 16;

        // int count = 0;
        // for(int i = start; i < stop; i++){
        //     count++;
        //     System.out.println(count);
        // }
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int userNum;
        int genNum;

        do {
            System.out.println("Enter a number from -100 to 100");

            userNum = sc.nextInt();
            genNum = rand.nextInt(200) - 100;
            
            System.out.println("Number generated: " + genNum);
        }
        while (userNum != genNum);
        System.out.println("You win");
        sc.close();
    }
}