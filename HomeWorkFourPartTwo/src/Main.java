import java.util.Random;
import java.util.Scanner;

// COP 3330 - Gradi Tshielekeja Mbuyi
// Assignment Four - Sep 24, 2022

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
	static boolean addBook(Books bookList, Scanner myScan, String scanString, String [] bookInfo) {
		
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
			
			// prints out the book info
			if(bookList.addBookstoreBook(book)) {
				System.out.println("\nHere is your bookstore book information");
				System.out.println(book + "\n");
			} 	return true;
		}  
		
		// adds book to our LibraryBook List
		else if(scanString.equals("LB")) {
			
			// creates an object for our book
			LibraryBook book = new LibraryBook(bookInfo[0],bookInfo[1], bookInfo[2]);
			System.out.println("Got it!");
			
			book.setCaller();
			
			// prints out the book info
			if(bookList.addLibraryBook(book)) {
				System.out.println("\nHere is your bookstore book information");
				System.out.println(book + "\n");
			} 	return true;
		}
		
		else { // returns false when user input an invalid entry
			System.out.print("Oops! That's not a valid entry. Please try again: ");
			return false;
		}
		
	}
	
	// creates a book object
	static void createObject(Books bookList, Scanner myScan, String scanString) {
		
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
		
		// returns the user's input
		System.out.println(""); return scanString;
	}
	
	// reads all the user's inputs
	static void readInput(Books bookList) {
		
		// creates a scanner object, along with an object to store the user's input
		Scanner myScan = new Scanner(System.in);
		String scanString = new String();
		
		// welcomes user 
		boolean create = true;
		System.out.println("Welcome to the book program!");
		
		// while loops runs at least once
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
	static void printOutput(Books bookList) {
		
		// prints output
		System.out.println("Sure! \n");
		System.out.println(bookList);
		System.out.println("Take care now!");
	}

	// beginning of our code
	public static void main(String[] args) {
		
		// creates bookList
		Books bookList = new Books();
		
		// reads user input
		readInput(bookList);
		
		// prints book list
		printOutput(bookList);
	}
	
}

// a class for a list of books
class Books {
	
	// max size of our LibraryBook object
	final int SIZE = 200;
	
	// arrays to store our list of books
	private BookstoreBook [] bookstorebook;
	private LibraryBook [] librarybook;
	
	// constructor for our bookList
	public Books() {
		
		// setting the size of both of our objects
		bookstorebook = new BookstoreBook[SIZE / 2];
		librarybook = new LibraryBook[SIZE];
		
		// setting every book to null
		for(int i = 0; i < SIZE; i++) {
			if(i < SIZE / 2) bookstorebook[i] = null;
			librarybook[i] = null;
		}
	}
	
	// adds a book from the book store
	public boolean addBookstoreBook(BookstoreBook book) {
		
		// our loop looks for available space
		for(int i = 0; i < SIZE / 2; i++) {
			if(bookstorebook[i] == null) {
				
				// stores our book once space is found
				bookstorebook[i] = book;
				
				// increases numBook
				BookstoreBook.setNumBook(BookstoreBook.getNumBook() + 1);
				return true;
			}
		}
		
		// returns false if program fails to add book
		return false;
	}
	
	// adds a book from the library
	public boolean addLibraryBook(LibraryBook book) {
		
		// our loop looks for available space
		for(int i = 0; i < SIZE; i++) {
			if(librarybook[i] == null) {
				
				// stores our book once space is found
				librarybook[i] = book;
				
				// increases numBook
				LibraryBook.setNumBook(LibraryBook.getNumBook() + 1);
				return true;
			}
		}
		
		// returns false if program fails to add book
		return false;
	}
	
	// helper method for printing libaryBook
	private String printLibraryBook(String bookList) {
		
		// title and numBook
		bookList += "Library Books (" + Integer.toString(LibraryBook.getNumBook()) + ")\n";
		
		// stores book info
		for(LibraryBook lb : librarybook) {
			if(lb != null) {
				bookList += "     " + lb;
				bookList += "\n";
			}
		}
		
		// returns book info
		return bookList;
	}
	
	// helper method for printing bookstoreBook
	private String printBookstoreBook(String bookList) {
		
		// title and numBook
		bookList += "Bookstore Books (" + Integer.toString(BookstoreBook.getNumBook()) + ")\n";
		
		// stores book info
		for(BookstoreBook bb : bookstorebook) {
			if(bb != null) {
				bookList += "     " + bb;
				bookList += "\n";
			}
		}
		
		// returns book info
		return bookList;
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

// class of our bookstore books
class BookstoreBook{
	private String author;
	private String title;
	private String isbn;
	
	private float price;
	private boolean forSale;
	private int deduction;
	private float listed;
	
	private static int numBook = 0;
	
	// CONSTRUCTORS

	public BookstoreBook (String author, String title, String isbn){
		this.author = author;
		this.title = title;
		this.isbn = isbn;
	}
	
	public BookstoreBook (float price, boolean forSale) {
		this.price = price;
		this.forSale = forSale;
	}
	
	public BookstoreBook (float price, boolean forSale, int deduction) {
		this.price = price;
		this.forSale = forSale;
		this.deduction = deduction;
	}
	
	// SETTERS AND GETTERS
	
	public int getDeduction() {
		return deduction;
	}

	public void setDeduction(int deduction) {
		this.deduction = deduction;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

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
	
	public static int getNumBook() {
		return numBook;
	}

	public static void setNumBook(int numBook) {
		BookstoreBook.numBook = numBook;
	}
	
	public float getListed() {
		return listed;
	}

	public void setListed(float listed) {
		this.listed = listed;
	}
	
	// OVERRIDING THE toString METHOD

	@Override
	public String toString() {
		return "[" + isbn + "-" + title + " by " + author + ", $" + String.format("%.2f", price) + " listed for $" + String.format("%.2f", listed) + "]";
	}
	
	
}

// class of our library books
class LibraryBook {
	private String author;
	private String title;
	private String isbn;
	
	private String [] caller;
	private static int numBook = 0;
	private Random random;
	
	// CONSTRUCTORS
	
	public LibraryBook(String author, String title, String isbn){
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		
		random = new Random();
	}
	
	public LibraryBook(String author) {
		this.author = author;
	}
	
	public LibraryBook() {
		this.author = null;
		this.title = null;
		this.isbn = null;
	}

	// SETTERS AND GETTERS
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public static int getNumBook() {
		return numBook;
	}

	public static void setNumBook(int numBook) {
		LibraryBook.numBook = numBook;
	}
	
	private String randomFloor() {
		
		// return value
		String randomNumber = "";
		
		// generates a random number between 0  and 99
		int rand = random.nextInt(0, 100);
		if(rand < 10) randomNumber += "0";
		
		// returns the number as a string
		randomNumber += Integer.toString(rand);
		return randomNumber;
	}

	public void setCaller() {
		
		// generates a random number 00 - 99
		caller = new String[3]; 
		caller[0] = randomFloor();
		
		// stores the author's name (first 3 letters)
		if(author.length() > 2) caller[1] = author.substring(0, 3);
		else caller[1] = author.substring(0);
		
		// stores the last value of the book's isbn
		caller[2] = isbn.substring(isbn.length() - 1);
		
	}
	
	// OVERRIDING THE toString METHOD

	@Override
	public String toString() {
		return "[" + isbn + "-" + title + " by " + author + "-" + caller[0] + "." + caller[1] + "." + caller[2] + "]";
	}
	
}