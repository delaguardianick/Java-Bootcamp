/*
JAVA BASICS
Programs, Statements, and Variables

A Java identifier must follow these rules:
    It cannot span multiple lines.
    It must only contain numbers, letters, underscores (_), dashes (-), and dollar signs ($).
    It must start with a letter, underscore, dash, or dollar sign.
    It cannot contain any spaces.

Variables -> camelCase

Runtime Error: No syntax error
    ex. insufficient memory

DataTypes:
single quotes ' ' -> character
double quotes " " -> string

Operators:
Unary -> both increment/decrease variable applied to
    prefix -> ++x -> update value before using variable
    postfix -> x++ -> use the current value and update variable after
Binary -> a + b, a / 2
Ternary 

*/

//Scanner:
import java.util.Scanner;

public class Tuesday{
    public static void main(String[] args) {
        // declare the number variables and initialize to 0
        int sum = 0;
        int operand1 = 0;
        int operand2 = 0;

        // declare and initialize a Scanner object - the Scanner reads
        // input from the console
        Scanner myScanner = new Scanner(System.in);

        // declare and initialize String (text) variables to hold the
        // values that the user types in - the Console only accepts
        // text, it knows nothing about numbers
        String stringOperand1 = "";
        String stringOperand2 = "";

        // ask the user to input the first operand
        System.out.println("Please enter the first number to be added:");

        // now wait until the user types something in - put the value
        // in stringOperand1
        stringOperand1 = myScanner.nextLine();

        // ask the user to input the second operand:
        System.out.println("Please enter the second number to be added:");

        // now wait until the user types something in - put the value
        // in stringOperand2
        stringOperand2 = myScanner.nextLine();

        // in order to add the values input by the user we must
        // convert the String values into int values.  We use the
        // parseInt method for this:
        operand1 = Integer.parseInt(stringOperand1);
        operand2 = Integer.parseInt(stringOperand2);

        // assign the sum of operand1 and operand2 to sum
        sum = operand1 + operand2;

        // print the sum to the console
        System.out.println("Sum is: " + sum);
    }
    
    /*
    try, catch
    NumberFormatException
    https://academy.engagelms.com/mod/page/view.php?id=45749&noredirect=1
    */
    public static void NumberFormatException(){
        
        Scanner myScanner = new Scanner(System.in);
        try {

            String input = myScanner.nextLine();
            int number = Integer.parseInt(input);

        } catch(NumberFormatException ex) {
            // Think of this kind of like an 'else' block for now. This is the code that will run
            // if the user doesn't enter a number.
            System.out.println("Input could not be parsed into an integer");
        }
    }
}

