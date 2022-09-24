// COP 3330 - Gradi Tshielekeja Mbuyi

import java.util.Scanner;

public class HomeworkTwo {

	public static void main(String[] args) {
		// scanner
		Scanner myScan = new Scanner(System.in);
		
		// var for user input 
		double dollarAmount;
		
		// scans user's input
		System.out.print("Enter your dollar amount: ");
		dollarAmount = myScan.nextDouble();
		
		System.out.println("\nYou have: \n");
	
		System.out.printf("  - %d hundred(s)\n", (int) dollarAmount / 100);
		dollarAmount %= 100;
		
		System.out.printf("  - %d fifty(s)\n", (int) dollarAmount / 50);
		dollarAmount %= 50;
		
		System.out.printf("  - %d twenty(s)\n", (int) dollarAmount / 20);
		dollarAmount %= 20;
		
		System.out.printf("  - %d ten(s)\n", (int) dollarAmount / 10);
		dollarAmount %= 10;
		
		System.out.printf("  - %d five(s)\n", (int) dollarAmount / 5);
		dollarAmount %= 5;
		
		System.out.printf("  - %d one(s)\n", (int) dollarAmount / 1);
		dollarAmount %= 1;
		
		dollarAmount *= 100;
		
		System.out.printf("  - %d quarter(s)\n", (int) dollarAmount / 25);
		dollarAmount %= 25;
		
		System.out.printf("  - %d dime(s)\n", (int) dollarAmount / 10);
		dollarAmount %= 10;
		
		System.out.printf("  - %d nickle(s)\n", (int) dollarAmount / 5);
		dollarAmount %= 5;
		
		System.out.printf("  - %d cents(s)\n", (int) dollarAmount / 1);
		dollarAmount %= 1;
		
		System.out.println("\nGoodbye!");
	}

}
