import java.util.Scanner;
import java.lang.NumberFormatException;
public class UI {
	
	
	public static void initialMessage() {
		System.out.println("What would you like to do?");
		System.out.println("Purchase a book? (Type 'B')");
		System.out.println("Donate a book? (Type 'D')");
		System.out.println("Check the availability of a book? (Type 'C')");
		System.out.println("Are you stealing our books? (Type 'S')");
		System.out.println("Do you need more detailed assistance? (Type 'H')");
		System.out.println("Want to leave the store at anytime? (Type 'Quit')");
		
	}
	
	private static boolean quittingTime(String userInput) { // only use this method when getting input from user
		if (userInput.trim().toLowerCase().equals("quit")) {
			return true;
		}
		return false;
	}
	private static Book makeBook(int isbn, String title, String genre) {
		Book book = new Book(isbn, title, genre, 1);
		return book;
	}
	
	private static boolean isbnCheck(String userInput) {
		if(quittingTime(userInput)) return false;
		if(userInput.isEmpty()) {
			System.out.println("Please enter the 13 digit ISBN number. This field must not be empty");
			return false;
		}
		try {
			Integer isbn = Integer.parseInt(userInput.substring(3)); //check if integers are input and if 4th digit is less than 2
			}catch(NumberFormatException e) {
				System.out.println("ERROR! Please enter the 13 digit ISBN number in solely "
						+ "counting numbers."); // error message	
				return false;
			}
		
		if(userInput.length() != 13) {
			System.out.println("Please enter your book's 13 digit ISBN code.");
			return false;
		}
		
		
		return true;
		
	}
	
	public static void engine() {
		BookStore bookStore = new BookStore(20);
		Boolean KeepRunning = true;
		Scanner myScanner = new Scanner(System.in);
		do {
		initialMessage();

		
		String userInput = myScanner.nextLine().trim();
		if(quittingTime(userInput)) break;
		System.out.println("\n");
		System.out.println(userInput); // prints out the letter they enter. will delete after rigorous checking.
		if(userInput.toLowerCase().startsWith("b")) { // loop if donating a book.
			System.out.println("Please list the 13 digit ISBN number of the Book, excluding the '#'."
					+ " EX: 8791001212222: "); 
			int isbnAttempts = 0; // if a user commits 3 errors, they will be booted out of the Bookstore.
			Boolean isbnTruthValue = false;
			String isbnString = "";
			
			do {
				isbnString = myScanner.nextLine().trim(); // gets user input
				//System.out.println(isbnString.length()); // print length in order to see if Correct.
				isbnTruthValue = isbnCheck(isbnString);
				if(isbnTruthValue == false) isbnAttempts ++;
				//System.out.println(isbnAttempts); was printing attempts in error check.
			}while(!(isbnTruthValue) && isbnAttempts < 3 && !(quittingTime(isbnString)));  //checks for input errors or if quit
				if (isbnAttempts == 3) {
					System.out.println("You are being kicked out of the store. You may re-enter"
							+ ", but please correctly enter the book's ISBN number.");
					KeepRunning = false; // end do while loop for three errors
					break;
				}
				if(quittingTime(isbnString)) {
					System.out.println("Thanks for stopping by!");
					break;
		}
		
		Integer isbn = Integer.parseInt(isbnString.substring(3));
			bookStore.remove(isbn);
				
			
			}// end of startsWithB
		
		if(userInput.toLowerCase().startsWith("d")) { // conditional if buying a book
			System.out.println("Please list the 13 digit ISBN number of the Book, excluding the '#'."
					+ " EX: 8791001212222: "); 
			int isbnAttempts = 0; // if a user commits 3 errors, they will be booted out of the Bookstore.
			Boolean isbnTruthValue = false;
			String isbnString = "";
			do {
				isbnString = myScanner.nextLine().trim(); // gets user input
				//System.out.println(isbnString.length());
				isbnTruthValue = isbnCheck(isbnString);
				if(isbnTruthValue == false) isbnAttempts ++; // if there isn't a proper isbn returns false
				//System.out.println(isbnAttempts);
			}while(!(isbnTruthValue) && isbnAttempts < 3 && !(quittingTime(isbnString)));  //checks for input errors
				if (isbnAttempts == 3) {
					System.out.println("You are being kicked out of the store. You may re-enter"
							+ ", but please correctly enter the book's ISBN number.");
					KeepRunning = false; // end do while loop for three errors
					break;
				}
				if (quittingTime(isbnString)) {
					System.out.println("Thanks for stopping by!");
					break;
					
				}
		Integer isbn = Integer.parseInt(isbnString.substring(3));
			
			System.out.println("Please enter the book title.");
			String title = "";
			int titleEmpty = 0;
			do {
			title = myScanner.nextLine().trim();
			if(title.isEmpty()) {
				System.out.println("Please enter the book title. This field cannot be empty");
				titleEmpty ++;
				
			}
			}while(title.isEmpty() && titleEmpty < 3 && !(quittingTime(title)));
			if(titleEmpty == 3) {
				System.out.println("You Are being kicked out of the store. You may re-enter,"
						+ " but please correctly enter the title.");
				break;
			}
			if(quittingTime(title)) {
				System.out.println("Thanks for stopping by!");
				break;
			}
			String genre = "";
			int genreEmpty = 0;
			System.out.println("Please enter the genre.");
			do {
				genre = myScanner.nextLine().trim();
				if(genre.isEmpty()) {
					System.out.println("Please input the genre. This field cannot be empty.");
					genreEmpty ++;
				}
				
				
			}while(genre.isEmpty() && genreEmpty < 3 && !(quittingTime(genre)));
			if(genreEmpty == 3) {
				System.out.println("You are being kicked out of the store. You may re-enter, "
						+ "but please correctly enter the title");
			}
			if(quittingTime(genre)) {
				System.out.println("Thanks for stoppping by!");
				break;
			}
			Book newBook = new Book(isbn, title, genre, 1);
			bookStore.donate(isbn, newBook);
		}
		if(userInput.toLowerCase().startsWith("c")) { // conditional if checking availability of book
			System.out.println("Please list the 13 digit ISBN number of the Book, excluding the '#'."
					+ " EX: 8791001212222: "); 
			int isbnAttempts = 0; // if a user commits 3 errors, they will be booted out of the Bookstore.
			Boolean isbnTruthValue = false;
			String isbnString = "";
			do {
				isbnString = myScanner.nextLine().trim(); // gets user input
				//System.out.println(isbnString.length());
				isbnTruthValue = isbnCheck(isbnString);
				if(isbnTruthValue == false) isbnAttempts ++;
				//System.out.println(isbnAttempts);
			}while(!(isbnTruthValue) && isbnAttempts < 3 && !(quittingTime(isbnString)));  //checks for input errors
				if (isbnAttempts == 3) {
					System.out.println("You are being kicked out of the store. You may re-enter"
							+ ", but please correctly enter the book's ISBN number.");
					KeepRunning = false; // end do while loop for three errors
					break;
					
		}
				if(quittingTime(isbnString)) {
					System.out.println("Thanks for stopping by!");
					break;
				}
		Integer isbn = Integer.parseInt(isbnString.substring(3));
		if(bookStore.containsBook(isbn)) {
			System.out.println("Looks like we have that book.");
		}else {
			System.out.println("Sorry we do not have that book.");
		}
		
		}
		if(userInput.toLowerCase().startsWith("s")) { // conditional if checking availability of book
			System.out.println("Please don't do this, we work very hard!");
			System.out.println("...");
			System.out.println("NO! please stop!");
			System.out.println("What am I to tell my boss! We've been completely cleared out!");
			bookStore.steal();
			break;
		}
		if(userInput.toLowerCase().startsWith("h")) {
			System.out.println("To purchase a Book, type 'B'");
			System.out.println("To check if a book is available, type 'C");
			System.out.println("To donate a book, type 'D'");
			System.out.println("If you are about to steal all of our books, type 'S");
			System.out.println("If you would like to leave the store, type 'Quit");
		}
		
		}while(KeepRunning);
		
		myScanner.close();
	}
	
	public static void main(String[] args) {
		System.out.println("Hi, welcome to the bookstore." + "\n");
		engine();
	}
	

}