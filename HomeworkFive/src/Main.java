import java.util.Random;
import java.util.Scanner;

// COP 3330 - Gradi Tshielekeja Mbuyi
// Assignment Five - Oct 20 2022

public class Main {
	
	// helper function for the addBook method
	static void setPrice(BookstoreBook book, Scanner myScan, String scanString) {
		
		// tells our program that the book is for sale
		book.setForSale(true);
		
		// asks the user for the deduction percentage
		System.out.print("Deduction percentage: ");
		
		// scans and stores user's input AND converts our value to an int
		scanString = myScan.nextLine();
		scanString = scanString.replaceAll("\\D+", "");
		book.setDeduction(Integer.parseInt(scanString));
		System.out.println("Got it!");
	}
	
	// method to add book
	static boolean addBook(BookList bookList, Scanner myScan, String scanString, String [] bookInfo) {
			
		// adds book to our BookstoreBook List
		if(scanString.equals("BB")) {
				
			// var for book's listed price
			float listed;
				
			// creates an object for our book
			BookstoreBook book = new BookstoreBook(bookInfo[0], bookInfo[1], bookInfo[2]);
			System.out.println("Got it!");
				
			// prompts the user to enter the price of the book
			System.out.print("Please enter the list price of " + bookInfo[1] + " by " + bookInfo[0] + ": ");
			book.setPrice(myScan.nextFloat());
				
			// asks the user whether our book is for sale or not
			System.out.print("Is it on sale? (y/n): ");
				
			// scans and stores user's entry
			scanString = myScan.nextLine();
			scanString = myScan.nextLine();
			scanString = scanString.toLowerCase();	
				
			// sets the price and reduction of our book
			if(scanString.equals("y")) setPrice(book, myScan, scanString);
				
			// tells our program that the book isn't for sale
			else if (scanString.equals("n")) {
				book.setForSale(false); 
				book.setDeduction(0); 
				System.out.println("Got it!");
			}
				
			// calculates how much the book is listed for
			if(book.isForSale() == false) listed = book.getPrice();
			else listed = book.getPrice() - ((float) book.getDeduction() / 100 * book.getPrice());
				
			book.setListed(listed);
			book.setType("BookstoreBook");
				
			// prints out the book info
			if(bookList.addBook(book)) {
				System.out.println("\nHere is your bookstore book information");
				System.out.println(book + "\n");
			} 	return true;
		}  
			
		// adds book to our LibraryBook List
		else if(scanString.equals("LB")) {
				
			// creates an object for our book
			LibraryBook book = new LibraryBook(bookInfo[0], bookInfo[1], bookInfo[2]);
			System.out.println("Got it!");
				
			book.setCaller();
			book.setType("LibraryBook");
				
			// prints out the book info
			if(bookList.addBook(book)) {
				System.out.println("\nHere is your library book information");
				System.out.println(book + "\n");
			} 	return true;
		}
			
		else { // returns false when user input an invalid entry
			System.out.print("Oops! That's not a valid entry. Please try again: ");
			return false;
		}
			
	}
	
	// creates a book object
	static void createObject(BookList bookList, Scanner myScan, String scanString) {
		
		// creates an arr of type String to store book info
		String [] bookInfo; boolean error = false;
		
		// prompts the user to enter the book's information
		System.out.print("Please enter the author, title, and the isbn of the book separated by /: ");
		
		// scans user's input
		scanString = myScan.nextLine();
		scanString = scanString.toUpperCase();		
		bookInfo = scanString.split("/");
		System.out.println("Got it!");
		
		// ask user whether the book is a bookstore or library book
		System.out.print("Now, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book): ");
				
		// while loop runs at least once
		while (error == false) {
					
			// scans for user's response (to the previous question)
			scanString = myScan.nextLine();
			scanString = scanString.toUpperCase();
					
			// attempts to add book
			error = addBook(bookList, myScan, scanString, bookInfo); // returns whether the attempt was successful 
		} 
		
	}
	
	// method used to check for error in user's input - ask user whether they would like to add a book object
	static String inputError(Scanner myScan, String scanString) {
		
		// while loop continues until user inputs: yes or no
		while(!(scanString.equals("yes") || scanString.equals("no"))) {
			System.out.print("I'm sorry but " + scanString + " isn't a valid answer. Please enter either yes or no: ");
			scanString = myScan.nextLine();
			scanString = scanString.toLowerCase();
		}
		
		System.out.println(""); return scanString; // returns the user's input
	}

	// reads all the user's input
	static void readInput(BookList bookList) {
		
		// creates a scanner object, along with an object to store the user's input
		Scanner myScan = new Scanner(System.in);
		String scanString = new String();
		
		// welcomes user
		boolean create = true;
		System.out.println("Welcome to the book program!");
		
		// while loops run at least once
		while(create == true) {
			
			// asks user if they would like to create a book object
			System.out.print("Would you like to create a book object? (yes/no): ");
			
			// scans and stores user input
			scanString = myScan.nextLine();
			scanString = scanString.toLowerCase();
			
			// checks for error
			if(!(scanString.equals("yes") || scanString.equals("no"))) scanString = inputError(myScan, scanString);
				
			// creates a book object or ends the loop
			if(scanString.equals("yes")) createObject(bookList, myScan, scanString);
			else if(scanString.equals("no")) create = false;
		}
		
		// closes my scanner
		myScan.close();
	}
	
	// method to print output
	static void printOutput(BookList bookList) {
		
		// prints output
		System.out.println("Sure! \n");
		System.out.println(bookList);
		System.out.println("Take care now!");
	}
	
	
	// beginning of our code
	public static void main(String[] args) {
		
		// creates bookList
		BookList bookList = new BookList();
		
		// reads user input
		readInput(bookList);
		
		// prints bookList
		printOutput(bookList);
		
	}

}

// abstract class for books
abstract class Book {
	private String author;
	private String title;
	private String isbn;
	private String type;
	
	// CONSTRUCTOR
	public Book(String author, String title, String isbn) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
	}

	// SETTERS AND GETTERS
	public String getAuthor() {
		return author;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
	
	@Override
	public String toString() {
		return "" + isbn + "-" + title + " by " + author;
	}
}

// a class for bookstore book
class BookstoreBook extends Book {
	private float price;
	private boolean forSale;
	private int deduction;
	private float listed;
	private static int numBook = 0;
	
	// CONSTRUCTOR
	public BookstoreBook(String author, String title, String isbn) {
		super(author, title, isbn);
	}

	//SETTERS AND GETTERS
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isForSale() {
		return forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	public int getDeduction() {
		return deduction;
	}

	public void setDeduction(int deduction) {
		this.deduction = deduction;
	}

	public float getListed() {
		return listed;
	}

	public void setListed(float listed) {
		this.listed = listed;
	}

	public static int getNumBook() {
		return numBook;
	}

	public static void setNumBook(int numBook) {
		BookstoreBook.numBook = numBook;
	}
	
	@Override
	public String toString() {
		return "[" + super.toString() + ", $" + String.format("%.2f", price) + " listed for $" + String.format("%.2f", listed) + "]";
	}
}

// a class for library book
class LibraryBook extends Book {
	private String [] caller;
	private Random random;
	private static int numBook = 0;
	
	// CONSTRUCTOR
	public LibraryBook(String author, String title, String isbn) {
		super(author, title, isbn);
		random = new Random();
	}
	
	// SETTERS AND GETTERS
	public static int getNumBook() {
		return numBook;
	}

	public static void setNumBook(int numBook) {
		LibraryBook.numBook = numBook;
	}
	
	// finds random floor for book
	private String randomFloor() {
		
		// return value 
		String randomNumber = "";
		
		// generates a random number between 0 and 99
		int rand = random.nextInt(0, 100);
		if(rand < 10) randomNumber += "0";
		
		// returns the number as a string
		randomNumber += Integer.toString(rand);
		return randomNumber;
		
	}

	// sets information of book
	public void setCaller() {
		
		// generates a random number 00 - 99
		caller = new String[3];
		caller[0] = randomFloor();
		
		// stores the author's name (first 3 letters)
		if(super.getAuthor().length() > 2) caller[1] = super.getAuthor().substring(0, 3);
		else caller[1] = super.getAuthor().substring(0);
		
		// stores the last value of the book's isbn
		caller[2] = super.getIsbn().substring(super.getIsbn().length() - 1);
	}
		
	@Override
	public String toString() {
		return "[" + super.toString() + "-" + caller[0] + "." + caller[1] + "." + caller[2] + "]";
	}
	
}

// a class for a list of books
class BookList {
	
	// array to store our list of books
	private Book[] list;
	
	// CONSTRUCTOR
	public BookList() {
		list = new Book[100];
		
		for(int i = 0; i < 100; i++) {
			list[i] = null; 
		}	
	}
	
	// adds a book to our list
	public boolean addBook(Book book) {
		
		// our loop looks for available space
		for(int i = 0; i < 100; i++) {
			
			if(list[i] == null) {
				
				// stores our book once space is found
				list[i] = book;
				
				// increases numBook
				if(list[i].getType().equals("LibraryBook")) LibraryBook.setNumBook(LibraryBook.getNumBook() + 1);
				else if(list[i].getType().equals("BookstoreBook")) BookstoreBook.setNumBook(BookstoreBook.getNumBook() + 1);
				return true;
			}
			
		}   return false; // returns false if program fails to add book
	}
	
	// helper method for printing libraryBook
	private String printLibraryBook(String bookList) {
		
		// title and numBook
		bookList += "Library Books (" + Integer.toString(LibraryBook.getNumBook()) + ")\n";
		
		for(Book lb : list) { // store book info
			if(lb != null && lb.getType().equals("LibraryBook")) {
				bookList += "     " + lb;
				bookList += "\n";
			}
		}
		
		return bookList; // returns book info
	}
	
	// helper method for printing bookstoreBook
	private String printBookstoreBook(String bookList) {
		
		// title and numBook
		bookList += "Bookstore Books (" + Integer.toString(BookstoreBook.getNumBook()) + ")\n";
		
		for(Book bb : list) { // stores book info
			if(bb != null && bb.getType().equals("BookstoreBook")) {
				bookList += "     " + bb;
				bookList += "\n";
			}
		}
		
		return bookList; // returns book info
	}
	
	@Override
	public String toString() {
		
		// title
		String bookList = "Here are all your books... \n\n";
		
		// prints bookstore book
		if(LibraryBook.getNumBook() > 0) bookList = printLibraryBook(bookList);
		bookList += "\n";
		
		// prints library book
		if(BookstoreBook.getNumBook() > 0) bookList = printBookstoreBook(bookList);
		return bookList;
	}
	
	
}
