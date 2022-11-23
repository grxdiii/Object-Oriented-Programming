import java.util.Scanner;

public interface Management {
    String [] keyWords = {"Professor", "Adjunct", "Mathematics", "Engineering", "Sciences", "P", "F"};

    // the list of menus
    default void options() {
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
    default String checkData(Scanner myScan, String input, String inputType, int lowIndex, int highIndex) {
        if(lowIndex == 4) input = input.toUpperCase();
        else input = input.substring(0,1).toUpperCase() + input.substring(1);

        while(!(input.equals(keyWords[lowIndex]) || input.equals(keyWords[highIndex - 1]) || input.equals(keyWords[highIndex]))) {
            System.out.println("          \"" + input + "\" is invalid");
            System.out.print("     " + inputType + ": "); input = myScan.nextLine();
            if(lowIndex == 4) input = input.substring(0,1).toUpperCase();
            else input = input.substring(0,1).toUpperCase() + input.substring(1);
        }   return input;
    }

    default float catchFloatException(String input, Scanner myScan){
        float gpa = -1; while(gpa < 0 || gpa > 4) {
            try {
                gpa = Float.parseFloat(input);
                if(gpa < 0 || gpa > 4) throw new Exception();
            } catch (Exception e) {
                System.out.println("          \"" + input + "\" is invalid");
                System.out.print("     GPA: "); input = myScan.nextLine();
            }
        }   return gpa;
    }

    default int catchIntException(String input, Scanner myScan){
        int creditHours = -1; while(creditHours < 0) {
            try {
                creditHours = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("          \"" + input + "\" is invalid");
                System.out.print("     Credit hours: "); input = myScan.nextLine();
            }
        }   return creditHours;
    }

    void createReport(Scanner myScan, String input);
    void printInfo(Scanner myScan, String type);
    void storeInfo(Scanner myScan, String type);
}
