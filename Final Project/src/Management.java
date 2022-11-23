import java.util.Scanner;

public interface Management {
    String [] keyWords = {"Professor", "Adjunct", "Mathematics", "Engineering", "Sciences", "P", "F"};

    // checks if the user input the right department, rank, and status
    default String checkData(Scanner myScan, String input, String inputType, int lowIndex, int highIndex) {
        if(lowIndex == 4) input = input.substring(0).toUpperCase();
        else input = input.substring(0,1).toUpperCase() + input.substring(1);

        while(!(input.equals(keyWords[lowIndex]) || input.equals(keyWords[highIndex - 1]) || input.equals(keyWords[highIndex]))) {
            System.out.println("          \"" + input + "\" is invalid");
            System.out.print("     " + inputType + ": "); input = myScan.nextLine();
            if(lowIndex == 4) input = input.substring(0,1).toUpperCase();
            else input = input.substring(0,1).toUpperCase() + input.substring(1);
        }   return input; // returns the correct input
    }

    void createReport(Scanner myScan, String input);
    void printInfo(Scanner myScan, String type);
    void storeInfo(Scanner myScan, String type);
}
