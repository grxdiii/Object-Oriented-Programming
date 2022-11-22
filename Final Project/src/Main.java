// Gradi Tshielekeja Mbuyi
// Project Two
// November 22, 2022

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    final static String [] keyWords =
            {"Professor", "Adjunct", "Mathematics", "Engineering", "Sciences", "P", "F"};

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

    // checks if the user input the right department, rank, and status
    static String checkData(Scanner myScan, String input, String inputType, int lowIndex, int highIndex) {
        if(lowIndex == 4) input = input.substring(0).toUpperCase();
        else input = input.substring(0,1).toUpperCase() + input.substring(1);

        while(!(input.equals(keyWords[lowIndex]) || input.equals(keyWords[highIndex - 1]) || input.equals(keyWords[highIndex]))) {
            System.out.println("          \"" + input + "\" is invalid");
            System.out.print("     " + inputType + ": "); input = myScan.nextLine();
            if(lowIndex == 4) input = input.substring(0,1).toUpperCase();
            else input = input.substring(0,1).toUpperCase() + input.substring(1);
        }   return input; // returns the correct input
    }

    static String checkId(Personnel personnel, Scanner myScan, String type){
        return null;
    }

    // method to store person's data
    static void storeInfo(Personnel personnel, Scanner myScan, String type) {
        String name, id;
        System.out.println("\nEnter the " + type + " info: \n");

        if(!type.equals("Staff")) System.out.print("     Name of the " + type + ": ");
        else System.out.print("     Name of the " + type + " member: ");  name = myScan.nextLine();

        if(!type.equals("Staff")) System.out.print("     ID: ");
        else System.out.print("     Enter the id: "); id = myScan.nextLine();
        //id = checkId(personnel, myScan, type);

        if(type.equals("Student")) {
            float gpa; int creditHours;
            System.out.print("     GPA: "); gpa = myScan.nextFloat();
            System.out.print("     Credit hours: "); creditHours = myScan.nextInt();
            Student student = new Student(name, id, gpa, creditHours); myScan.nextLine();
            if(personnel.addPersonnel(student)) System.out.println("\nStudent added!\n");

        } else if(type.equals("Faculty")) {
            String department, rank;
            System.out.print("     Rank: "); rank = myScan.nextLine();
            rank = checkData(myScan, rank, "Rank", 0, 1);
            System.out.print("     Department: "); department = myScan.nextLine();
            department = checkData(myScan, department, "Department", 2, 4);
            Faculty faculty = new Faculty(name, id, department, rank);
            if(personnel.addPersonnel(faculty)) System.out.println("\nFaculty added!\n");

        } else if(type.equals("Staff")) {
            String department, status;
            System.out.print("     Department: "); department = myScan.nextLine();
            department = checkData(myScan, department, "Department", 2, 4);
            System.out.print("     Status, Enter P for Part Time, or Enter F for Full Time: "); status = myScan.nextLine();
            status = checkData(myScan, status, "Status", 5, 6);
            Staff staff = new Staff(name, id, department, status);
            if(personnel.addPersonnel(staff)) System.out.println("\nStaff member added!\n");

        }
    }

    // method to print person's info
    static void printInfo(Personnel personnel, Scanner myScan, String type) {
        String id; Person person; System.out.print("\n     Enter the " + type.toLowerCase() + "'s id: ");
        id = myScan.nextLine(); person = personnel.search(id, type);
        if(person == null && !type.equals("Staff")) System.out.println("\n     No " + type.toLowerCase() + " matched!");
        else if(person == null) System.out.println("\n     No " + type.toLowerCase() + " member matched!");
        else person.print(); System.out.println();
    }

    static void createReport(Personnel personnel, Scanner myScan, String input){
        PrintWriter printWriter = null;
        System.out.print("Would you like to sort your students by (1) gpa or (2) credit hours: "); input = myScan.nextLine();
        while(!(input.equals("1") || input.equals("2"))) {
            System.out.print("Invalid entry - please try again: "); input = myScan.nextLine();
        }

        try {
            printWriter = new PrintWriter("report.txt");
            personnel.print(printWriter, Integer.parseInt(input));
            System.out.println("Report created and saved on your hard drive!");
        } catch (FileNotFoundException e) {
            System.out.println("Could not create nor find the file...");
        }

        printWriter.close();
    }

    // method to read user's inputs
    static void readInput(Personnel personnel) {
        Scanner myScan = new Scanner(System.in); boolean exit = false; String input = null;
        System.out.println("Welcome to my Personnel Management Program\n");
        System.out.println("Choose one of the options: \n");

        while(!exit) {
            options(); input = myScan.nextLine();
            if(input.equals("1")) storeInfo(personnel, myScan, "Faculty");
            else if(input.equals("2")) storeInfo(personnel, myScan, "Student");
            else if(input.equals("3")) printInfo(personnel, myScan, "Student");
            else if(input.equals("4")) printInfo(personnel, myScan, "Faculty");
            else if(input.equals("5")) storeInfo(personnel, myScan, "Staff");
            else if(input.equals("6")) printInfo(personnel, myScan, "Staff");
            else if(input.equals("7")) exit = true; // ends the program
            else System.out.println("\nInvalid entry - please try again\n");
        }

        System.out.print("\nWould you like to create the report? (Y/N): "); input = myScan.nextLine().toLowerCase();
        if(input.equals("y")) createReport(personnel, myScan, input);
        else if(!input.equals("n")) System.out.println("invalid input. File has not been created.\n");
        myScan.close();
    }

    public static void main(String[] args) {
        Personnel personnel = new Personnel();
        readInput(personnel); System.out.println("Goodbye!");
    }
}



