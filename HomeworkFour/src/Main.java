// COP 3330 - Gradi Tshielekeja Mbuyi
// Assignment Four - August 31, 2022

public class Main {
	
	public static void main(String[] args) {
		
		String fullName = "Erika T. Jones";
		String employeeNumber = "ej789";
		double payRate = 100.0, hoursWorked = 1.0; //TA will change these to test your code
		
		Employee e = new Employee(fullName, employeeNumber, payRate, hoursWorked);

		System.out.println(e); // To Test your toString method

		e.printCheck();

		System.out.println("Bye!");
	}
}

// class for employee
class Employee {
	private String fullname;
	private String employeeNumber;
	private double payRate;
	private double hoursWorked;
	
	// constructor for employee
	Employee(String fullName, String employeeNumber, double payRate, double hoursWorked){
		this.fullname = fullName;
		this.employeeNumber = employeeNumber;
		this.payRate = payRate;
		this.hoursWorked = hoursWorked;
	}
	
	// returns employee's net pay
	private double netPay() {
		double grossPay = hoursWorked * payRate;
		double tax = grossPay * 0.06;
		return grossPay - tax;
	}
	
	// prints employee's check 
	public void printCheck() {
		// prints employe's information (gathered from input)
		System.out.println("\nEmployee's name:        " + fullname);
		System.out.println("Employee's number:      " + employeeNumber);
		System.out.println("Hourly rate of pay:     " + payRate);
		System.out.printf("Hours worked:           %.2f\n", hoursWorked);
				
		// calculates gross and net pay
		System.out.printf("\nTotal Gross Pay:        $%.2f\n", hoursWorked * payRate);
		System.out.printf("\nDeductions \nTax (6%%):               %.2f\n\n", hoursWorked * payRate * 0.06);
		System.out.printf("Net Pay:                %.2f Dollars\n", netPay());
	}

	// getter for fullname
	public String getFullname() {
		return fullname;
	}

	// setter for fullname
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	// getter for employeeNumber
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	// setter for employeeNumber
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	// getter for payRate
	public double getPayRate() {
		return payRate;
	}

	// setter for payRate
	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	// getter for hoursWorked
	public double getHoursWorked() {
		return hoursWorked;
	}

	// setter for hoursWorked
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	// toString method is override 
	public String toString() {
		return "[" + employeeNumber + "/" + fullname + ", " + hoursWorked + " Hours @ " + payRate + " per hour]";
	}
}