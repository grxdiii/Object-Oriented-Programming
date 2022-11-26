// Gradi Tshielekeja Mbuyi
// Project Two - November 25, 2022

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Main {

    // method to read user's inputs
    static void readInput(Personnel personnel) {
        boolean exit = false; String input = null;
        Scanner myScan = new Scanner(System.in);

        // welcomes user
        System.out.println("Welcome to my Personnel Management Program\n");
        System.out.println("Choose one of the options: \n");

        // list the menu options
        while(!exit) {
            personnel.options();
            input = myScan.nextLine();

            // reads user input
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

        // ask the user whether they would like to create a report
        System.out.print("\nWould you like to create the report? (Y/N): ");
        input = myScan.nextLine().toLowerCase();

        // creates report if the user asks for it
        if(input.equals("y")) personnel.createReport(myScan, input);
        else if(!input.equals("n")) System.out.println("invalid input. File has not been created.\n");

        myScan.close(); // closes our scanner
    }

    public static void main(String[] args) {

        // creates our object and reads the user's input
        Personnel personnel = new Personnel();
        readInput(personnel); System.out.println("Goodbye!");
    }
}

// abstract class for a person
abstract class Person {
    private String fullName;
    private String id;
    private String type;

    // constructor
    public Person(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }

    // every class inheriting "Person" will need to define print()
    public abstract void print();

    // getters and setters
    public String getFullName() {
        return this.fullName;
    }

    public String getId() {
        return this.id;
    }

    public void setType(String type) {
        this.type = type;
    }
}

// class for student objects
class Student extends Person implements Comparable<Student>{
    private float gpa;
    private int creditHour;
    private static int sortedBy;

    // constructor
    public Student(String fullName, String id, float gpa, int creditHour) {
        super(fullName, id);
        super.setType("Student");
        this.gpa = gpa;
        this.creditHour = creditHour;
    }

    // prints the student's tuition invoice
    public void print() {
        float payment = (float) (236.45 * creditHour) + 52;
        float discount = payment;
        if(gpa > 3.85) payment = (float) (payment * 0.75);
        discount = discount - payment;
        System.out.println("\n\tHere is the tuition invoice for " + super.getFullName() + ":\n");
        System.out.println("\t-------------------------------------------------------");
        System.out.println("\t" + super.getFullName() + "         " + super.getId());
        System.out.println("\t" + "Credit Hours: " + creditHour + "       ($236.45/credit hour)");
        System.out.println("\t" + "Fees: $52\n");
        System.out.println(String.format("\tTotal payment: $%.02f     ($%d discount applied)", payment, (int) discount));
        System.out.println("\t-------------------------------------------------------");
    }

    // getters and setters
    public static void setSortedBy(int sortedBy) {
        Student.sortedBy = sortedBy;
    }

    public float getGpa() {
        return gpa;
    }

    public int getCreditHour() {
        return creditHour;
    }

    // overriding the compareTo method from the Comparable interface
    @Override
    public int compareTo(Student student) {
        if(this.sortedBy == 1) return Double.compare(this.gpa, student.getGpa());
        return Integer.compare(this.creditHour, student.getCreditHour());
    }
}

// abstract class for employee
abstract class Employee extends Person {
    private String department;

    // constructor
    public Employee(String fullName, String id, String department) {
        super(fullName, id);
        this.department = department;
    }

    // getters and setters
    public String getDepartment() {
        return this.department;
    }
}

// class for faculty objects
class Faculty extends Employee {
    private String rank;

    // constructor
    public Faculty(String fullName, String id, String department, String rank) {
        super(fullName, id, department);
        super.setType("Faculty");
        this.rank = rank;
    }

    // getters and setters
    public String getRank() {
        return this.rank;
    }

    // print faculty's information
    public void print() {
        System.out.println("\n\t-------------------------------------------------------");
        System.out.println("\t" + super.getFullName() + "         " + super.getId());
        System.out.println("\t" + super.getDepartment() + " Department, " + this.rank );
        System.out.println("\t-------------------------------------------------------");
    }
}

// class for staff objects
class Staff extends Employee {
    private String status;

    // constructor
    public Staff(String fullName, String id, String department, String status) {
        super(fullName, id, department);
        super.setType("Staff");
        this.status = status;
    }

    // getters and setters
    public String getStatus() {
        return status;
    }

    // prints the staff's info
    public void print() {
        System.out.println("\n\t-------------------------------------------------------");
        System.out.println("\t" + super.getFullName() + "\t" + super.getId());
        if(status.equals("F")) System.out.println("\t" + super.getDepartment() + " Department, Full Time");
        else System.out.println("\t" + super.getDepartment() + " Department, Part Time");
        System.out.println("\t-------------------------------------------------------");
    }
}

// class for our id exception
class IdException extends Exception {

    // constructor
    IdException(){}

    IdException(String message){
        super(message);
    }

    IdException(Throwable cause){
        super(cause);
    }

    // checks the format of our id
    public static void checkFormat(String id) throws IdException {
        if(id.length() != 6 || !id.substring(0,1).matches("[a-zA-ZX]+") ||
                !id.substring(2,5).matches("[0-9]+")) throw new IdException();
    }

    // returns error message as a string
    @Override
    public String getMessage(){
        return "\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit\n";
    }
}

// class to manage our program's function
interface Management {
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

    // checks whether the user's entry is correct
    default String checkData(Scanner myScan, String input, String inputType, String type, int lowIndex, int highIndex) {

        // checks if the rank, department, or status input is correct
        if(inputType.equals("Rank") || inputType.equals("Department") || inputType.equals("Status")) {
            if (lowIndex == 5) input = input.toUpperCase();
            else input = input.substring(0, 1).toUpperCase() + input.substring(1);

            while (!(input.equals(keyWords[lowIndex]) || input.equals(keyWords[highIndex - 1]) || input.equals(keyWords[highIndex]))) {
                inputPrompt(type, "invalid input", input);
                System.out.print("\t" + inputType + ": "); input = myScan.nextLine();
                if (lowIndex == 4) input = input.substring(0, 1).toUpperCase();
                else input = input.substring(0, 1).toUpperCase() + input.substring(1);
            }

        // checks if the name format input is correct
        } else if(inputType.equals("Name")) {
            while(!(input.matches("[a-zA-Z\s]+")) || input.length() == 0 || input.matches("[\s]+")) {
                inputPrompt(null, "invalid input", input);
                inputPrompt(type, "name", null); input = myScan.nextLine();
            }

        // checks if the id format input is correct
        } else if(inputType.equals("Id")){
            boolean exit = false; while(!exit) {
                try {
                    IdException.checkFormat(input); exit = true;
                } catch (IdException e) {
                    System.out.println(e.getMessage());
                    inputPrompt(type, "id", null); input = myScan.nextLine();
                }
            }
        }   return input;   // returns the input
    }

    // list of prompts given to the user
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

    // method to catch float exception
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

    // method to catch int exception
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

    // method to get the current date (generated if user ask to create the report.txt file)
    default String currentDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date) + "\n";
    }

    // methods that will be defined in any class that implements this class
    void createReport(Scanner myScan, String input);
    void printInfo(Scanner myScan, String type);
    void storeInfo(Scanner myScan, String type);
}

// class for personnel
class Personnel implements Management{
    private ArrayList<Faculty> faculties;
    private ArrayList <Staff> staff;
    private ArrayList <Student> students;

    // constructor
    public Personnel() {
        this.faculties = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    // adds person to our personnel object
    private void addPersonnel(Faculty f) {
        this.faculties.add(f);
        System.out.println("\nFaculty added!\n");
    }

    private void addPersonnel(Staff s){
        this.staff.add(s);
        System.out.println("\nStaff member added!\n");
    }

    private void addPersonnel(Student s){
        this.students.add(s);
        System.out.println("\nStudent added!\n");
    }

    // search for person in our personnel object
    private Faculty searchFaculty(String id)
    {
        for(Faculty f : this.faculties){
            if(id.equals(f.getId())) return f;
        }   return null;
    }

    private Staff searchStaff(String id){
        for(Staff s : this.staff){
            if(id.equals(s.getId())) return s;
        }   return null;
    }

    private Student searchStudent(String id){
        for(Student s : this.students){
            if(id.equals(s.getId())) return s;
        }   return null;
    }

    private Person search(String id, String type) {
        if(type.equals("Faculty")) return searchFaculty(id);
        else if(type.equals("Staff")) return searchStaff(id);
        else return searchStudent(id);
    }

    // prints the data stored in our personnel object
    private void printFaculties(PrintWriter printWriter){
        printWriter.println("Faculty Members\n"); int i = 1;

        for(Faculty faculty : this.faculties) {
            printWriter.println("\t" + i + ". " + faculty.getFullName()); i++;
            printWriter.println("\t" + "ID: " + faculty.getId());
            printWriter.println("\t" + faculty.getRank() + ", " + faculty.getDepartment());
            printWriter.println();
        }
    }

    private void printStaff(PrintWriter printWriter){
        printWriter.println("Staff Members\n"); int i = 1;

        for(Staff s : this.staff) {
            printWriter.println("\t" + i + ". " + s.getFullName()); i++;
            printWriter.println("\t" + "ID: " + s.getId());
            if(s.getStatus().toLowerCase().equals("f")) printWriter.println("\t" + s.getDepartment()+ ", Full Time");
            else printWriter.println("\t" + s.getDepartment()+ ", Part Time");
            printWriter.println();
        }
    }

    private void printStudent(PrintWriter printWriter, int sortedBy){
        if(sortedBy == 1) printWriter.println("Students (Sorted by gpa)\n");
        else if(sortedBy == 2) printWriter.println("Students (Sorted by credit hours)\n");

        Student.setSortedBy(sortedBy);
        Collections.sort(this.students, Collections.reverseOrder()); int i = 1;

        for(Student student : this.students){
            printWriter.println("\t" + i + ". " + student.getFullName()); i++;
            printWriter.println("\t" + "ID: " + student.getId());
            printWriter.println("\t" + "Gpa: " + student.getGpa());
            printWriter.println("\t" + "Credit hours: " + student.getCreditHour());
            printWriter.println();
        }
    }

    private void print(PrintWriter printWriter, int sortedBy){
        printWriter.println("Report created on " + currentDate());
        if(!this.faculties.isEmpty()) printFaculties(printWriter);
        if(!this.staff.isEmpty()) printStaff(printWriter);
        if(!this.students.isEmpty()) printStudent(printWriter, sortedBy);
    }

    @Override
    public void printInfo(Scanner myScan, String type) {
        String id; Person person; System.out.print("\n\tEnter the " + type.toLowerCase() + "'s id: ");
        id = myScan.nextLine(); person = search(id, type);
        if(person == null && !type.equals("Staff")) System.out.println("\n\tNo " + type.toLowerCase() + " matched!");
        else if(person == null) System.out.println("\n\tNo " + type.toLowerCase() + " member matched!");
        else person.print(); System.out.println();
    }

    // creates report.txt file
    @Override
    public void createReport(Scanner myScan, String input){
        PrintWriter printWriter = null;
        System.out.print("Would you like to sort your students by (1) gpa or (2) credit hours: ");
        input = myScan.nextLine();

        while(!(input.equals("1") || input.equals("2"))) {
            inputPrompt(null, "invalid entry", null);
            input = myScan.nextLine();
        } try {
            printWriter = new PrintWriter("report.txt");
            print(printWriter, Integer.parseInt(input));
            System.out.println("Report created and saved on your hard drive!\n");
        } catch (FileNotFoundException e) {
            System.out.println("Could not create nor find the file...");
        }

        if (printWriter != null) printWriter.close();
    }

    // store faculty information
    private void storeFaculty(String name, String id, Scanner myScan){
        String department, rank;
        inputPrompt(null, "rank", null);
        rank = checkData(myScan, myScan.nextLine(), "Rank", null,0, 1);
        inputPrompt(null, "department", null);
        department = checkData(myScan, myScan.nextLine(), "Department", null, 2, 4);
        addPersonnel(new Faculty(name, id, department, rank));
    }

    // store student information
    private void storeStudent(String name, String id, Scanner myScan){
        float gpa; int creditHours;
        inputPrompt(null, "gpa", null); gpa = catchFloatException(myScan.nextLine(), myScan);
        inputPrompt(null, "credit hours", null); creditHours = catchIntException(myScan.nextLine(), myScan);
        addPersonnel(new Student(name, id, gpa, creditHours));
    }

    // store staff information
    private void storeStaff(String name, String id, Scanner myScan) {
        String department, status;
        inputPrompt(null, "department", null); department = myScan.nextLine();
        department = checkData(myScan, department, "Department", null,2, 4);
        inputPrompt(null, "status", null); status = myScan.nextLine();
        status = checkData(myScan, status, "Status", null, 5, 6);
        addPersonnel(new Staff(name, id, department, status));
    }

    // store person information
    @Override
    public void storeInfo(Scanner myScan, String type) {
        // initial prompt
        String name, id; System.out.println("\nEnter the " + type + " info: \n");

        // stores name and id of user
        inputPrompt(type, "name", null);
        name = checkData(myScan, myScan.nextLine(), "Name", type, 0, 0);

        inputPrompt(type, "id", null);
        id = checkData(myScan, myScan.nextLine(), "Id", type, 0, 0);

        // stores other information based on the type of person
        if(type.equals("Student")) storeStudent(name, id, myScan);
        else if(type.equals("Faculty")) storeFaculty(name, id, myScan);
        else storeStaff(name, id, myScan);
    }
}

