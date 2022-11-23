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

    private boolean addPersonnel(Faculty f) {
        return this.faculties.add(f);
    }

    private boolean addPersonnel(Staff s){
        return this.staff.add(s);
    }

    private boolean addPersonnel(Student s){
        return this.students.add(s);
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
        printWriter.println("Report created on ");
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
            System.out.print("Invalid entry - please try again: ");
            input = myScan.nextLine();
        } try {
            printWriter = new PrintWriter("report.txt");
            print(printWriter, Integer.parseInt(input));
            System.out.println("Report created and saved on your hard drive!");
        } catch (FileNotFoundException e) {
            System.out.println("Could not create nor find the file...");
        }

        if (printWriter != null) printWriter.close();
    }

    @Override
    public void printInfo(Scanner myScan, String type) {
        String id; Person person; System.out.print("\n     Enter the " + type.toLowerCase() + "'s id: ");
        id = myScan.nextLine(); person = search(id, type);
        if(person == null && !type.equals("Staff")) System.out.println("\n     No " + type.toLowerCase() + " matched!");
        else if(person == null) System.out.println("\n     No " + type.toLowerCase() + " member matched!");
        else person.print(); System.out.println();
    }

    private void storeFaculty(String name, String id, Scanner myScan){
        String department, rank;

        System.out.print("     Rank: "); rank = myScan.nextLine();
        rank = checkData(myScan, rank, "Rank", 0, 1);
        System.out.print("     Department: "); department = myScan.nextLine();
        department = checkData(myScan, department, "Department", 2, 4);
        Faculty faculty = new Faculty(name, id, department, rank);
        if(addPersonnel(faculty)) System.out.println("\nFaculty added!\n");
    }


    private void storeStudent(String name, String id, Scanner myScan){
        float gpa; int creditHours;

        System.out.print("     GPA: ");
        gpa = catchFloatException(myScan.nextLine(), myScan);

        System.out.print("     Credit hours: ");
        creditHours = catchIntException(myScan.nextLine(), myScan);

        Student student = new Student(name, id, gpa, creditHours);
        if(addPersonnel(student)) System.out.println("\nStudent added!\n");
    }

    private void storeStaff(String name, String id, Scanner myScan) {
        String department, status;

        System.out.print("     Department: "); department = myScan.nextLine();
        department = checkData(myScan, department, "Department", 2, 4);
        System.out.print("     Status, Enter P for Part Time, or Enter F for Full Time: "); status = myScan.nextLine();
        status = checkData(myScan, status, "Status", 5, 6);
        Staff staff = new Staff(name, id, department, status);
        if(addPersonnel(staff)) System.out.println("\nStaff member added!\n");
    }

    @Override
    public void storeInfo(Scanner myScan, String type) {
        String name, id;

        System.out.println("\nEnter the " + type + " info: \n");
        if(!type.equals("Staff")) System.out.print("     Name of the " + type + ": ");
        else System.out.print("     Name of the " + type + " member: ");  name = myScan.nextLine();
        if(!type.equals("Staff")) System.out.print("     ID: ");
        else System.out.print("     Enter the id: "); id = myScan.nextLine();

        if(type.equals("Student")) storeStudent(name, id, myScan);
        else if(type.equals("Faculty")) storeFaculty(name, id, myScan);
        else storeStaff(name, id, myScan);
    }
}
