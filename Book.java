/**
 * A class which represents a book to be stores in a database.
 * @author Bereket
 * @version 1.0
 */
public class Book {
	/**
	 * stores the title of the book
	 */
	private String title;
	/**
	 * stores the isbn of the book
	 */
	private String ISBN;
	/**
	 * stores the author of the book.
	 */
	private String author;

	/**
	 * Creates a book with the specified parameters.
	 * @param title the title of the book
	 * @param ISBN the isbn of the book
	 * @param author the author of the book
	 */
	public Book(String title, String ISBN, String author) {
		
		// isbn should be 13 numeric length
		if (ISBN.length() != 13){
			throw new IllegalArgumentException();
		} else {
			this.ISBN = ISBN;
			this.title = title;
			this.author = author;
		}
		
	}

	/**
	 *  a gets the title of a book
	 * @return returns the title of the book
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * getter for the isbn of a book
	 * @return returns the isbn of the book
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * a getter for the author of a book
	 * @return
	 */
	public String getAuthor() {
		return author;
	}	
}
