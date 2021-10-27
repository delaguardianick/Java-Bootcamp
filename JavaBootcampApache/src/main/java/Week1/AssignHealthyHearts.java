import java.util.Scanner;

public class AssignHealthyHearts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask for age
        System.out.print("What is your age? ");
        int userAge = sc.nextInt();

        // Do math
        int maxHeartRate = 220 - userAge;
        System.out.printf("Your maximum heart rate should be %d beats per minute\n", maxHeartRate);
        System.out.printf("Your target HR Zone is %d - %d beats per minute", Math.round(maxHeartRate*0.5), Math.round((int)maxHeartRate*0.85));

        sc.close();
    }
    
}
