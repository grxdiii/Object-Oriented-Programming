import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// class for personnel
class Personnel implements Management{
    private ArrayList<Faculty> faculties;
    private ArrayList <Staff> staff;
    private ArrayList <Student> students;

    public Personnel() {
        this.faculties = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.students = new ArrayList<>();
    }

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

    @Override
    public void printInfo(Scanner myScan, String type) {
        String id; Person person; System.out.print("\n\tEnter the " + type.toLowerCase() + "'s id: ");
        id = myScan.nextLine(); person = search(id, type);
        if(person == null && !type.equals("Staff")) System.out.println("\n\tNo " + type.toLowerCase() + " matched!");
        else if(person == null) System.out.println("\n\tNo " + type.toLowerCase() + " member matched!");
        else person.print(); System.out.println();
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
