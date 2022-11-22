// COP 3330 - Gradi Tshielekeja Mbuyi
// November 21, 2022

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Main {

    // search for classroom given user input
    static String search(ArrayList<String> classList, String userInput){
        String classroom = null; String [] classroomInfo;

        // loops through our classList
        for(String c : classList) {
            classroomInfo = c.split(",");
            if (classroomInfo.length == 2) classroom = classroomInfo[1];
            else if (classroomInfo.length == 7) classroom = classroomInfo[5];
            if (classroom != null && classroom.toLowerCase().equals(userInput.toLowerCase())) return classroomInfo[0];
        }   return null;
    }

    public static void main(String[] args) {
        // scanner and printer to deal with our files
        Scanner scanner = null; PrintWriter printWriter = null;

        // reads file
        try {
            scanner = new Scanner(new File("lec.txt"));
            String [] classInfoArr; String classInfo, userInput, classroom; int numOnlineClass = 0;

            // array list to store our classroom types
            ArrayList<String> onlineClass = new ArrayList<>();
            ArrayList<String> lectureWithNoLab = new ArrayList<>();
            ArrayList<String> classSearch = new ArrayList<>();

            // scans our given file
            while(scanner.hasNextLine()) {

                // stores class info
                classInfo = scanner.nextLine();

                // categorize the classroom type
                if(classInfo.split(",").length == 5) {
                    onlineClass.add(classInfo); numOnlineClass++;
                } else {
                    classInfoArr = classInfo.split(",");
                    if(classInfoArr.length == 7 && classInfoArr[6].toLowerCase().equals("no")) lectureWithNoLab.add(classInfo);
                }   classSearch.add(classInfo); // adds every classroom
            }

            // outputs the number of online class offered
            System.out.println("- There are " + numOnlineClass + " online lectures offered");

            // allows user to search for classroom
            System.out.print("- Enter the classroom: ");
            userInput = new Scanner(System.in).nextLine();
            classroom = search(classSearch, userInput);

            // returns the result of our search
            if(classroom == null) System.out.println("\tThere are no such classroom in the database");
            else System.out.println("\tThe crns held in " + userInput + " are " + classroom);

            // writes in our output file
            try {
                // tells the user if the output file was successfully opened
                printWriter = new PrintWriter("lecturesOnly.txt");
                System.out.println("- lecturesOnly.txt is created");

                // prints info given the context of lec.txt
                printWriter.println("Given the context of lec.txt, lecturesOnly.txt should be: \n");

                // prints online classes
                if(!onlineClass.isEmpty()) {
                    printWriter.println("Online Lectures");
                    for(String s : onlineClass) printWriter.println(s);
                    printWriter.println();
                }

                // prints classes with no lab
                if(!lectureWithNoLab.isEmpty()) {
                    printWriter.println("Lectures with No Lab");
                    for(String s : lectureWithNoLab) printWriter.println(s);
                }
            } catch (FileNotFoundException e) { // tells the user that the lecturesOnly file could not be opened
                System.out.println("Could not create nor find the file...");
            }

            System.out.println("\nGoodbye!");

        } catch (FileNotFoundException e) { // tells the user that the lec.txt file could not be found
            System.out.println("File not found...");
        }

        // closes our scanner and print writer
        scanner.close();
        printWriter.close();
    }
}