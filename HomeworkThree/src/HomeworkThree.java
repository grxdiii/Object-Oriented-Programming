// COP 3330 - Gradi Tshielekeja Mbuyi

import java.util.Scanner;

public class HomeworkThree {
	
	static void printReport(int a, int b, int c, int d, int f, int gradeSum) {
		
		// stores number of scores processed
		int numScores = a + b + c + d + f;
		
		// stores number of high score processed
		int highScore = a + b + c;
		
		// prints report
		System.out.printf("\nHere is your report: \n");
		System.out.printf("   - A total of %d scores entered. %d of them are 70 or higher. \n\n", numScores, highScore);
		System.out.printf("   - Letter Grade distribution of the scores: \n");
		System.out.printf("        - %d Students earned the grade of A (90-100) \n", a);
		System.out.printf("        - %d Students earned the grade of B (80-89) \n", b);
		System.out.printf("        - %d Students earned the grade of C (70-79) \n", c);
		System.out.printf("        - %d Students earned the grade of D (60-69) \n", d);
		System.out.printf("        - %d Students earned the grade of F (59 or bellow) \n", f);
		
		// print average score of the class processed
		System.out.printf("   - The average score is: %.2f \n", (double) gradeSum / numScores);
	}

	public static void main(String[] args) {
		// scanner declaration
		Scanner myScan = new Scanner(System.in);
		
		// variable for user input
		int grade = 0; char process; boolean start = true;
		
		// welcomes user
		System.out.printf("Welcome to my program. You will be asked to enter the scores of a test one by one, and to enter -1 to stop.\n");
		
		// while loop runs until user is done processing each class
		while(start != false) {
			
			// frequency variable
			int a = 0, b = 0, c = 0, d = 0, f = 0, gradeSum = 1;
			System.out.printf("\n");
			
			// while loop runs until user is done processing one class
			do {
				
				// scans and stores user's grade entry
				System.out.printf("Enter Score (Enter -1 to Stop): ");
				grade = myScan.nextInt();
			
				// deals with invalid entry
				if (grade > 100) System.out.printf("Score %d Rejected \n", grade);
				
				// deals with valid entry
				else {
					
					// counts grade letter
					if(grade > 89) a++;
					else if(grade > 79) b++;
					else if(grade > 69) c++;
					else if(grade > 59) d++;
					else if(grade > -1) f++;
					
					// stores the sum score of each student
					gradeSum += grade;
				}
				
			} while (grade != -1); // ends while loop 
			
			printReport(a, b, c, d, f, gradeSum); // prints report
			
			// ask user whether they would like to process another class
			System.out.printf("\nWould you like to process another class? (Y or N): ");
			process = myScan.next().charAt(0);
			
			// ends program if user request it
			if(process == 'N' || process == 'n') start = false;
		}
		
		// end of program
		System.out.printf("Goodbye!");
	
	}

}
