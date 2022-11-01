import java.util.Scanner;

//	COP 3330 - Gradi Tshielekeja Mbuyi
//	Project One - Oct 22 2022

public class Main {
	
	final static String [] keyWords = 
			
	{"Professor", "Adjunct", "Mathematics", "Engineering", "Sciences", "P", "F"};
	
	// the list of menus
	static void options() {
		
		// menu options
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
	
	// checks if the user inputed the right department, rank, and status
	static String checkData(Scanner myScan, String input, String inputType, int lowIndex, int highIndex) {
		
		// capitalize the first letter of the user's input
		if(lowIndex == 4) input = input.substring(0).toUpperCase();
		else input = input.substring(0,1).toUpperCase() + input.substring(1);

		
		// runs if user inserts wrong input
		while(!(input.equals(keyWords[lowIndex]) || input.equals(keyWords[highIndex - 1]) || input.equals(keyWords[highIndex]))) {
			System.out.println("          \"" + input + "\" is invalid");
			System.out.print("     " + inputType + ": "); input = myScan.nextLine();
						
			// the loop will end when the user inserts the right input
			if(lowIndex == 4) input = input.substring(0,1).toUpperCase();
			else input = input.substring(0,1).toUpperCase() + input.substring(1);
		}
		
		return input; // returns the correct input
	}
	
	
	// method to store person's data
	static void storeInfo(Personnel personnel, Scanner myScan, String type) {
		
		// variable to store person's data
		String name, id;
		
		//prompts user for person's data
		System.out.println("\nEnter the " + type + " info: \n");
		
		// prompts user for person's name and id
		if(!type.equals("Staff")) System.out.print("     Name of the " + type + ": "); 
		else System.out.print("     Name of the " + type + " member: ");  name = myScan.nextLine();
		
		if(!type.equals("Staff")) System.out.print("     ID: "); 
		else System.out.print("     Enter the id: "); id = myScan.nextLine(); 

		// stores student's data
		if(type.equals("Student")) {
			
			// variable to store student's data
			float gpa; int creditHours;
			
			// prompts user for data specific to the student type
			System.out.print("     GPA: "); gpa = myScan.nextFloat();
			System.out.print("     Credit hours: "); creditHours = myScan.nextInt();
			Student student = new Student(name, id, gpa, creditHours); myScan.nextLine();
			if(personnel.addPersonnel(student)) System.out.println("\nStudent added!\n");
		}
		
		// stores faculty's data
		else if(type.equals("Faculty")) {
			
			// variables to store faculty's data
			String department, rank;
			
			// prompts user for faculty's department and rank
			System.out.print("     Rank: "); rank = myScan.nextLine();
				rank = checkData(myScan, rank, "Rank", 0, 1);
			System.out.print("     Department: "); department = myScan.nextLine();
				department = checkData(myScan, department, "Department", 2, 4);
			
			// stores user input in our personnel object
			Faculty faculty = new Faculty(name, id, department, rank);
			if(personnel.addPersonnel(faculty)) System.out.println("\nFaculty added!\n");
		}
		
		// stores staff's data 
		else if(type.equals("Staff")) {
			
			// variables to store staff's data
			String department, status;
			
			// prompts user for staff's department and status
			System.out.print("     Department: "); department = myScan.nextLine();
				department = checkData(myScan, department, "Department", 2, 4);
			System.out.print("     Status, Enter P for Part Time, or Enter F for Full Time: "); status = myScan.nextLine();
				status = checkData(myScan, status, "Status", 5, 6);
			
			// stores user input in our personnel object
			Staff staff = new Staff(name, id, department, status);
			if(personnel.addPersonnel(staff)) System.out.println("\nStaff member added!\n");
			
		}
	}
	
	// method to print person's info
	static void printInfo(Personnel personnel, Scanner myScan, String type) {
		
		// variable for person's object and id
		String id; Person person;
		System.out.print("\n     Enter the " + type.toLowerCase() + "'s id: ");
		
		// stores id and search for person
		id = myScan.nextLine();
		person = personnel.search(id, type);
		
		// returns result of search
		if(person == null && !type.equals("Staff")) System.out.println("\n     No " + type.toLowerCase() + " matched!");
		else if(person == null) System.out.println("\n     No " + type.toLowerCase() + " member matched!");
		else person.print(); System.out.println();
	}
	
	// method to read user's inputs
	static void readInput(Personnel personnel) {
		
		// creates a scanner object, along with a variable to store the user's input
		Scanner myScan = new Scanner(System.in);
		boolean exit = false; String input;
		
		// welcomes and prompts user
		System.out.println("Welcome to my Personnel Management Program\n");
		System.out.println("Choose one of the options: \n");
		
		while(exit == false) { // while loop runs at least once
			
			options(); // shows user the list of menus 
			input = myScan.nextLine(); // stores user's input 
			
			if(input.equals("1")) storeInfo(personnel, myScan, "Faculty");
			else if(input.equals("2")) storeInfo(personnel, myScan, "Student");
			else if(input.equals("3")) printInfo(personnel, myScan, "Student");
			else if(input.equals("4")) printInfo(personnel, myScan, "Faculty");
			else if(input.equals("5")) storeInfo(personnel, myScan, "Staff");
			else if(input.equals("6")) printInfo(personnel, myScan, "Staff");
			else if(input.equals("7")) exit = true; // ends the program
			else System.out.println("\nInvalid entry - please try again\n");
		}
		
		myScan.close(); // closes our scanner
	}
	
	public static void main(String[] args) {
		
		Personnel personnel = new Personnel(); // creates personnel list
		
		readInput(personnel); // read input
		
		System.out.println("\nGoodbye!"); // the user has exit the program
	}
}

// abstract class for a person
abstract class Person {
	
	// each person has a name and id
	private String fullName;
	private String id;
	
	// each person is either a student, faculty, or staff
	private String type;

	// constructor
	public Person(String fullName, String id) {
		this.fullName = fullName;
		this.id = id;
	}
	
	// every class will need to define print
	public abstract void print();
	
	// getters and setters
	public String getFullName() {
		return fullName;
	}

	public String getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}

// class for student objects
class Student extends Person {
	
	// data exclusive to the student class
	private float gpa;
	private int creditHour;

	
	// constructor
	public Student(String fullName, String id, float gpa, int creditHour) {
		
		// sets the name and id of person
		super(fullName, id);
		
		// sets the GPA and creditHour
		this.gpa = gpa;
		this.creditHour = creditHour;
		
		// class type is stored
		super.setType("Student");
	}

	// prints the student's tuition invoice
	public void print() {
		float payment = (float) (236.45 * creditHour) + 52;
		float discount = payment;
		
		if(gpa > 3.85) payment = (float) (payment * 0.75); 
		discount = discount - payment;
		
		System.out.println("\n     Here is the tuition invoice for " + super.getFullName() + ":\n");
		System.out.println("     ------------------------------------------------------- \n");
		System.out.println("     " + super.getFullName() + "         " + super.getId() + "\n");
		System.out.println("     Credit Hours: " + creditHour + "       ($236.45/credit hour) \n");
		System.out.println("     Fees: $52 \n");
		System.out.println("     Total payment: $" + payment + "     ($" + (int) discount + " discount applied)\n");
		System.out.println("     -------------------------------------------------------");
	}
	
}

// abstract class for employee
abstract class Employee extends Person {
	
	// every employee works in a department
	private String department;
	
	// constructor
	public Employee(String fullName, String id, String department) {
		super(fullName, id);
		this.department = department;
	}

	// setters and getters
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}

// class for faculty objects
class Faculty extends Employee {
	
	// every faculty has a rank
	private String rank;
	
	// constructor
	public Faculty(String fullName, String id, String department, String rank) {
		super(fullName, id, department);
		this.rank = rank;
		
		// class type is stored
		super.setType("Faculty");
	}
	
	// prints the faculty's info
	public void print() {
		System.out.println();
		
		System.out.println("     -------------------------------------------------------\n");
		System.out.println("     " + super.getFullName() + "         " + super.getId() + "\n");
		System.out.println("     " + super.getDepartment() + " Department, " + rank + "\n");
		System.out.println("     -------------------------------------------------------");
	}
}

// class for staff objects
class Staff extends Employee {
	
	// every staff has a status
	private String status;
	
	// constructor
	public Staff(String fullName, String id, String department, String status) {
		super(fullName, id, department);
		this.status = status;
		
		// class type is stored
		super.setType("Staff");
	}
	
	// prints the staff's info
	public void print() {
		System.out.println();
		
		System.out.println("     -------------------------------------------------------\n");
		System.out.println("     " + super.getFullName() + "         " + super.getId() + "\n");
		if(status.equals("F")) System.out.println("     " + super.getDepartment() + " Department, Full Time\n");
		else System.out.println("     " + super.getDepartment() + " Department, Part Time\n");
		System.out.println("     -------------------------------------------------------");;
	}
}


// class for personnel
class Personnel {
	
	// variable for list of personnel
	private Person [] list;
	
	// constructor
	public Personnel() {
		
		// list of personnel contains 100 spaces
		list = new Person[100];
		for(int i = 0; i < 100; i++) list[i] = null;	
	}
	
	// add person to our list of personnel
	public boolean addPersonnel(Person person) {
		
		// looks for available space
		for(int i = 0; i < 100; i++) {
		
			if(list[i] == null) {
				
				// returns true if person is added
				list[i] = person; return true;
			}
			
		}	return false; // returns false if person is not added
	}
	
	// search for person given their id
	public Person search(String id, String type) {
		
		// looks for person in our list
		for(int i = 0; i < 100; i++) {
			if(list[i] != null && list[i].getId().equals(id) && list[i].getType().equals(type)) return list[i];
		}	return null; // returns null if person is not found
	}
}
