// COP 3330 - Gradi Tshielekeja Mbuyi
// Assignment One - August 29, 2022

import java.util.Scanner;

public class HomeworkOne {

	static void output(String name, String empNum, double rate, double hours) {
		
		// variable declaration (output)
		double grossPay = hours * rate;
		double tax = grossPay * 0.06;
		double netPay = grossPay - tax;
		
		// prints employe's information (gathered from input)
		System.out.println("--------------------------------------");
		System.out.println("Employee's name:        " + name);
		System.out.println("Employee's number:      " + empNum);
		System.out.println("Hourly rate of pay:     " + rate);
		System.out.printf("Hours worked:           %.2f\n", hours);
		
		// calculates gross and net pay
		System.out.printf("\nTotal Gross Pay:        $%.2f\n", grossPay);
		System.out.printf("\nDeductions \nTax (6%%):               %.2f\n\n", tax);
		System.out.printf("Net Pay:                %.2f Dollars\n", netPay);
		System.out.println("--------------------------------------");
		
		System.out.println("\nBye!");
	}
	
	public static void main(String[] args) {
		
		// scanner declaration (keyboard input - read from console)
		Scanner myScan = new Scanner(System.in);
		
		// variable declaration (input)
		String name, empNum; double rate, hours;
		
		// scans for employee's name
		System.out.print("Enter the Employee's full name: ");
		name = myScan.nextLine();
		
		// scans for employee's number
		System.out.print("Enter the Employee's number: ");
		empNum = myScan.nextLine();
		
		// scans for the pay rate per hour
		System.out.print("Enter the pay rate per hour: ");
		rate = myScan.nextDouble();
		
		// scans for hours worked
		System.out.print("Enter the regular hours worked: ");
		hours = myScan.nextDouble();
		
		// outputs paycheck 
		System.out.println("\nThank You!\n \nHere is your paycheck!\n");
		output(name, empNum, rate, hours);
		
	}

}
