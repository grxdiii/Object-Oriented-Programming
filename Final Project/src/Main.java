// Gradi Tshielekeja Mbuyi
// Project Two - November 22, 2022

import java.util.Scanner;

public class Main {

    // method to read user's inputs
    static void readInput(Personnel personnel) {
        boolean exit = false; String input = null;
        Scanner myScan = new Scanner(System.in);

        System.out.println("Welcome to my Personnel Management Program\n");
        System.out.println("Choose one of the options: \n");

        while(!exit) {
            personnel.options();
            input = myScan.nextLine();

            switch (input) {
                case "1" -> personnel.storeInfo(myScan, "Faculty");
                case "2" -> personnel.storeInfo(myScan, "Student");
                case "3" -> personnel.printInfo(myScan, "Student");
                case "4" -> personnel.printInfo(myScan, "Faculty");
                case "5" -> personnel.storeInfo(myScan, "Staff");
                case "6" -> personnel.printInfo(myScan, "Staff");
                case "7" -> exit = true; // ends the program
                default -> System.out.println("\nInvalid entry - please try again\n");
            }
        }

        System.out.print("\nWould you like to create the report? (Y/N): ");
        input = myScan.nextLine().toLowerCase();

        if(input.equals("y")) personnel.createReport(myScan, input);
        else if(!input.equals("n")) System.out.println("invalid input. File has not been created.\n");

        myScan.close();
    }

    public static void main(String[] args) {
        Personnel personnel = new Personnel();
        readInput(personnel); System.out.println("Goodbye!");
    }
}



