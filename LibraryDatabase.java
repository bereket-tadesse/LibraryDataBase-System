/**
 * A class representing a database to store objects of type Book in an array.
 * @author Bereket
 * @version 1.0
 */
public class LibraryDatabase {
	/*
	 * Stores the capcity of the database
	 */
	private int capacity;
	/*
	 * Stores the current number of books in the database.
	 */
	private int size = 0;
	/*
	 * Stores array of books.
	 */
	private Book books[];

	/**
	 * Creates a libtraray database with a capacity
	 * 
	 * @param capacity stores the capacity of librarydatabase
	 * @throws IllegalArgumentException if capacity is < 0
	 */

	public static void main(String[] args) {
//		Book b1 = new Book("a", "10", "A");
//		Book b2 = new Book("b", "11", "B");
//		Book b3 = new Book("c", "12", "C");
//		Book b4 = new Book("d", "13", "D");
//		Book b5 = new Book("e", "14", "E");
//		Book b6 = new Book("f", "15", "F");
//		Book b7 = new Book("g", "16", "G");
//		Book b8 = new Book("h", "17", "H");
//		Book b9 = new Book("i", "18", "A");

		Book b1 = new Book("abc" , "1111111111111" , "a");
		Book b2 = new Book("def" , "2222222222222" , "b");
		Book b3 = new Book("ghi" , "3333333333333" , "C");
		Book b4 = new Book("jkl" , "4444444444444" , "d");
		Book b5 = new Book("mno" , "5555555555555" , "e");
		Book b6 = new Book("pqr" , "6666666666666" , "f");
		Book b7 = new Book("stu" , "7777777777777" , "g");
		Book b8 = new Book("vwx" , "8888888888888" , "h");
		Book b9 = new Book("yzz" , "9999999999999" , "i");	
		
		LibraryDatabase mybase = new LibraryDatabase(9);
		
		mybase.Add(b1);
		mybase.Add(b1);
		mybase.Add(b1);
		mybase.Add(b3);
		

//		System.out.println(mybase.books[0].getTitle());
//		System.out.println(mybase.books[1].getTitle());
//		System.out.println(mybase.books[2].getTitle());
//		System.out.println(mybase.books[3].getTitle());
//		System.out.println(mybase.books[4].getTitle());
//		System.out.println(mybase.books[5].getTitle());
//		System.out.println(mybase.books[6].getTitle());
//		System.out.println(mybase.books[7].getTitle());
//		System.out.println(mybase.books[8].getTitle());

//		System.out.println(mybase.getAllByAuthor("abc")[0].getAuthor());
		
		mybase.removeDuplicates();
		
		System.out.println(mybase.books[0].getTitle());
		System.out.println(mybase.books[1].getTitle());
//		System.out.println(mybase.books[2].getTitle());
//		System.out.println(mybase.books[3].getTitle());
		
		
	}
	
	
	
	/**
	 * A constructor to create a ddtabase with a specified capacity.
	 * @param capacity
	 */
	public LibraryDatabase(int capacity) {
		
		// if invalid capacity.
		if (capacity < 0) {
			throw new IllegalArgumentException();
		// valid capacity.
		} else {
			this.capacity = capacity;
			this.books = new Book[capacity];
		}
	}
	
	/**
	 * method to remove duplicated elements from the database in ascending order
	 */
	public void removeDuplicates() {
		
		if (size()!=0) {
			
			// number of books in the database.
			int size = this.size();
	
			// stores single copies of elements
			Book[] finalArray = new Book[size];
			int j = 0;
			
			// aloop which extracts single copies.
			for (int i = 0; i < size - 1; i++) {
				if ( !(books[i].getISBN().equals(books[i + 1].getISBN())) ) {
					finalArray[j++] = books[i];
				}
			}
			
			// avoid outof bound exception
			finalArray[j++] = books[size - 1];
			
			// Updates the array of the database from the final array.
			for (int i = 0; i < j; i++) {
				books = finalArray;
			}
		}
	}

	
	
	/**
	 * A method to add books based on ascending order.
	 * @param book the book to be added to the database
	 */
	public void Add(Book book) {

		// coverting the isbn to double for comaprsion.
		double BookIsbn = Double.parseDouble(book.getISBN());

		// stores the new updated array if reszing needed.
		Book[] newArray;

		// checking if resizing is needed.
		if (size() == capacity) {
			newArray = new Book[this.capacity * 2];
			capacity = capacity * 2;
		} else {
			newArray = new Book[this.capacity];
		}
		
		// update the size
		this.size += 1;

		int length = books.length;
		int i = 0;
		
		// loops through the array to find the excat position of a book by ISBN in ascending order.
		for (; i < length; i++) {
			
			// avoids null pointer exception
			if (books[i] != null) {
				// loops out and save i.
				if (BookIsbn < Double.parseDouble(books[i].getISBN())) break;
				newArray[i] = books[i];
			}
			else break;
		}

		// adding the current book on the required position
		newArray[i] = book;

		// copying the rest of the elements to the new array.s
		for (int j = i + 1; j < length; j++) {
			newArray[j] = books[j - 1];
		}
		// returning new array.
		books = newArray;

	}
	
	/**
	 * method to get all books of the same author
	 * @param Author the author of the book to be searched.
	 * @return returns an array of books with the same given author.
	 */
	public Book[] getAllByAuthor(String Author) {

		// counter
		int count = 0;
		// stores the books found with same author
		Book BooksFound[] = new Book[books.length];

		// loop which checks for similar authors and stores in an array.
		for (int i = 0; i < books.length; i++) {
			if (books[i] != null) {
				if (books[i].getAuthor().equals(Author)) {
					BooksFound[count] = books[i];
					count += 1;
				}
			}
		}
		
		return BooksFound;
	}

	/**
	 * method to find the current number of books in the database
	 * @return the size of the array in the database
	 */
	public int size() {
		return size;
	}

	/**
	 * Selects random book form the database
	 * @return returns the book selected randomly
	 */
	public Book randomSelection() {

		//stores the random generated index
		int number = (int) (Math.random() * this.size());

		// checks if the elemetn is not null
		if (books[number] != null) {
			return books[number]; 
		} else {
			return null;
		}

	}

	/**
	 * A method which removes a book based on given isbn
	 * @param isbn stores the isbn of the book to be searched
	 * @return returns the book with the isbn if found
	 */
	public Book remove(String isbn) {

		// stores the books to be removed.
		Book temp = null;

		// loops through the book to find the book that is going to be removed.
		for (int i = 0; i < this.books.length; i++) {
			
			// avoids null pointer exception
			if (this.books[i] != null) {
				
				//checks for similar ISBN
				if (this.books[i].getISBN().equals(isbn)) {
					temp = this.books[i];
					this.books[i] = null;
				}
			}
		}

		return temp;
	}

	public Book findByTitle(String title) {
		
		//loops through the to find the similar tittles.
		for (int i = 0; i < books.length; i++) {
			if (books[i] != null && books[i].getTitle().equals(title)) {
				return books[i];
			}
		}
		return null;
	}

	public Book findByISBN(String isbn) {

		//Using binary Search algorithm with O(logn)
		 					
		// start index
		int start = 0;
		// last index
		int last = this.size() - 1;
		// middle index
		int mid = 0;

		while (last - start >= 0) {
			
			mid = (start + last) / 2;
			
			if (Double.parseDouble(books[mid].getISBN()) < Double.parseDouble(isbn))
				start = mid + 1;
			else if ((Double.parseDouble(books[mid].getISBN()) == Double.parseDouble(isbn)))
				return books[mid];
			else 
				last = mid;
			
		}

		// returns book if found null if not.
		if (books[mid] != null && books[mid].getISBN().equals(isbn))
			return books[mid];
		else
			return null;

	}



	public Book[] toArray() {
		
		// Since the books are added in accending order no need to sort again.
		return this.books;
	}

}
