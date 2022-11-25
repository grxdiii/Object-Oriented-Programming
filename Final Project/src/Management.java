import java.text.SimpleDateFormat;
import java.util.Date;
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
        System.out.print("\tEnter your selection: ");
    }

    default String checkData(Scanner myScan, String input, String inputType, String type, int lowIndex, int highIndex) {
        if(inputType.equals("Rank") || inputType.equals("Department") || inputType.equals("Status")) {
            if (lowIndex == 5) input = input.toUpperCase();
            else input = input.substring(0, 1).toUpperCase() + input.substring(1);

            while (!(input.equals(keyWords[lowIndex]) || input.equals(keyWords[highIndex - 1]) || input.equals(keyWords[highIndex]))) {
                inputPrompt(type, "invalid input", input);
                System.out.print("\t" + inputType + ": "); input = myScan.nextLine();
                if (lowIndex == 4) input = input.substring(0, 1).toUpperCase();
                else input = input.substring(0, 1).toUpperCase() + input.substring(1);
            }

        } else if(inputType.equals("Name")) {
            while(!(input.matches("[a-zA-Z\s]+")) || input.length() == 0 || input.matches("[\s]+")) {
                inputPrompt(null, "invalid input", input);
                inputPrompt(type, "name", null); input = myScan.nextLine();
            }

        } else if(inputType.equals("Id")){
            boolean exit = false; while(!exit) {
                try {
                    IdException.checkFormat(input); exit = true;
                } catch (IdException e) {
                    System.out.println(e.getMessage());
                    inputPrompt(type, "id", null); input = myScan.nextLine();
                }
            }
        }   return input;
    }

    default void inputPrompt(String type, String key, String input){
        if(key.equals("name")) {
            if (!type.equals("Staff")) System.out.print("\tName of the " + type + ": ");
            else System.out.print("\tName of the " + type + " member: ");

        } else if(key.equals("id")) {
            if(!type.equals("Staff")) System.out.print("\tID: ");
            else System.out.print("\tEnter the id: ");
        }

        else if(key.equals("department")) System.out.print("\tDepartment: ");
        else if(key.equals("status")) System.out.print("\tStatus, Enter P for Part Time, or Enter F for Full Time: ");
        else if(key.equals("rank")) System.out.print("\tRank: ");
        else if(key.equals("gpa")) System.out.print("\tGPA: ");
        else if(key.equals("credit hours")) System.out.print("\tCredit hours: ");
        else if(key.equals("sorted by")) System.out.print("Would you like to sort your students by (1) gpa or (2) credit hours: ");
        else if(key.equals("invalid input")) System.out.println("\t\t\"" + input + "\" is invalid");
        else if(key.equals("invalid entry")) System.out.print("Invalid entry - please try again: ");
    }

    default float catchFloatException(String input, Scanner myScan){
        float gpa = -1; while(gpa < 0 || gpa > 4) {
            try {
                gpa = Float.parseFloat(input);
                if(gpa < 0 || gpa > 4) throw new Exception();
            } catch (Exception e) {
                inputPrompt(null, "invalid input", input);
                inputPrompt(null, "gpa", input); input = myScan.nextLine();
            }
        }   return gpa;
    }

    default int catchIntException(String input, Scanner myScan){
        int creditHours = -1; while(creditHours < 0) {
            try {
                creditHours = Integer.parseInt(input);
                if(creditHours < 0) throw new Exception();
            } catch (Exception e) {
                inputPrompt(null, "invalid input", input);
                inputPrompt(null, "credit hours", input); input = myScan.nextLine();
            }
        }   return creditHours;
    }

    default String currentDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date) + "\n";
    }

    void createReport(Scanner myScan, String input);
    void printInfo(Scanner myScan, String type);
    void storeInfo(Scanner myScan, String type);
}
