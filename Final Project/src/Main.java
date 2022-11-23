// Gradi Tshielekeja Mbuyi
// Project Two - November 22, 2022

import java.util.Scanner;

public class Main {

    // the list of menus
    static void options() {
        System.out.println("1- Enter the information of a faculty");
        System.out.println("2- Enter the information of a student");
        System.out.println("3- Print tuition invoice");
        System.out.println("4- Print faculty information");
        System.out.println("5- Enter the information of a staff member");
        System.out.println("6- Print the information of a staff member");
        System.out.println("7- Exit Program\n");

        // prompts the user to make a selection
        System.out.print("     Enter your selection: ");
    }

    // method to read user's inputs
    static void readInput(Personnel personnel) {
        Scanner myScan = new Scanner(System.in); boolean exit = false; String input = null;
        System.out.println("Welcome to my Personnel Management Program\n");
        System.out.println("Choose one of the options: \n");

        while(!exit) {
            options(); input = myScan.nextLine();
            if(input.equals("1")) personnel.storeInfo(myScan, "Faculty");
            else if(input.equals("2")) personnel.storeInfo(myScan, "Student");
            else if(input.equals("3")) personnel.printInfo(myScan, "Student");
            else if(input.equals("4")) personnel.printInfo(myScan, "Faculty");
            else if(input.equals("5")) personnel.storeInfo(myScan, "Staff");
            else if(input.equals("6")) personnel.printInfo(myScan, "Staff");
            else if(input.equals("7")) exit = true; // ends the program
            else System.out.println("\nInvalid entry - please try again\n");
        }

        System.out.print("\nWould you like to create the report? (Y/N): "); input = myScan.nextLine().toLowerCase();
        if(input.equals("y")) personnel.createReport(myScan, input);
        else if(!input.equals("n")) System.out.println("invalid input. File has not been created.\n");
        myScan.close();
    }

    public static void main(String[] args) {
        Personnel personnel = new Personnel();
        readInput(personnel); System.out.println("Goodbye!");
    }
}



