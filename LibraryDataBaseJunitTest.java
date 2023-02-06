import static org.junit.Assert.*;
import org.junit.Test;

/**
 * A Junit test file for the Book and Data base classes which tests all the methods.
 * @author Bereket
 * @version 1.0
 */
public class LibraryDataBaseJunitTest {

	/**
	 * These book type objects and LibraryDatabase type objects will be used for the different methods being tested for both classes.
	 */
	Book b1 = new Book("abc" , "1111111111111" , "a");
	Book b2 = new Book("def" , "2222222222222" , "b");
	Book b3 = new Book("ghi" , "3333333333333" , "C");
	Book b4 = new Book("jkl" , "4444444444444" , "d");
	Book b5 = new Book("mno" , "5555555555555" , "e");
	Book b6 = new Book("pqr" , "6666666666666" , "f");
	Book b7 = new Book("stu" , "7777777777777" , "g");
	Book b8 = new Book("vwx" , "8888888888888" , "h");
	Book b9 = new Book("yzz" , "9999999999999" , "i");
	
	
	
	
	//////////////////////////////////Test method for Book class  /////////////////////////////////////////////////////
	
	@Test
	/**
	 * Tests the Book class using the constructor and using the: 
	 * getTitle
	 * getISBN and 
	 * getAuthor methods at the same time.
	 */
	public void bookClassTester() {
	
		assertEquals("abc", b1.getTitle());
		assertEquals("1111111111111", b1.getISBN());
		assertEquals("a", b1.getAuthor());
		
	}
	
	
	//////////////////////////////////  Test methods for LibraryDatabase class  /////////////////////////////////////////////////////
	

	/**
	 * Tests the library database constructor
	 */
	@Test
	public void LibraryDatabaseConstrucotrTester() {
		
		//Testing for invalid number
		try {
			LibraryDatabase myDatabase = new LibraryDatabase(-1);
			 fail("creating database with invalid parameter did not throw an exception.");
		} catch (IllegalArgumentException e) {
			//everything is good.
		}
		
		//Testing valid number 
		
		LibraryDatabase mybase = new LibraryDatabase(9);
		assertEquals(9, mybase.toArray().length);
		

	}
	
	/**
	 * Tests the add method
	 */
	@Test
	public void addTester() {
		
		LibraryDatabase mybase = new LibraryDatabase(9);
		
		
		
		// Test 0 : test empty database
		assertEquals(null, mybase.toArray()[0]);
		
		
		mybase.Add(b1);
		
		// Test 1: test ddatabase with one book
		assertEquals("abc", mybase.toArray()[0].getTitle());
		
		// Test many: tests database with many books added in ascending order.
		mybase.Add(b2);
		mybase.Add(b4);
		mybase.Add(b5);
		mybase.Add(b3);
		mybase.Add(b7);
		mybase.Add(b6);
		mybase.Add(b9);
		mybase.Add(b8); 
		
		
		// testing if all books are added in asceding order.
		assertEquals("abc", mybase.toArray()[0].getTitle());
		assertEquals("def", mybase.toArray()[1].getTitle());
		assertEquals("ghi", mybase.toArray()[2].getTitle());
		assertEquals("jkl", mybase.toArray()[3].getTitle());
		assertEquals("mno", mybase.toArray()[4].getTitle());
		assertEquals("pqr", mybase.toArray()[5].getTitle());
		assertEquals("stu", mybase.toArray()[6].getTitle());
		assertEquals("vwx", mybase.toArray()[7].getTitle());
		assertEquals("yzz", mybase.toArray()[8].getTitle());
		
		
		// Test for resize: (of the database when database exceedes the capacity by adding 10th book)
		LibraryDatabase mybase2 = new LibraryDatabase(3);
		mybase2.Add(b1);
		mybase2.Add(b2);
		mybase2.Add(b3);
		mybase2.Add(b4);
		
		assertEquals("jkl", mybase.toArray()[3].getTitle());
		
	}
	
	/**
	 * tests the remove method
	 */
	@Test
	public void removeTester() {
		
		LibraryDatabase mybase = new LibraryDatabase(9);
		
		// Test 0: removing from empty
		assertEquals(null, mybase.remove("22222222"));
			
		mybase.Add(b1);
		
		// Test 1 : removing book from databse with 1 capacity
		// also applies to test first
		assertEquals("1111111111111", mybase.remove("1111111111111").getISBN());
		
		
		mybase.Add(b2);
		mybase.Add(b3);
		mybase.Add(b4);
		mybase.Add(b5);
		mybase.Add(b6);
		mybase.Add(b7);
		mybase.Add(b8);
		mybase.Add(b9);
		
		
		// Test many: removing books from many book database
		// Also applies to test middle
		assertEquals("2222222222222", mybase.remove("2222222222222").getISBN());
		assertEquals("3333333333333", mybase.remove("3333333333333").getISBN());
		
		// Test last: removing the last element.
		assertEquals("9999999999999", mybase.remove("9999999999999").getISBN());
		
		
	}
	
	/**
	 * tests the size method
	 */
	@Test
	public void sizeTester() {
		LibraryDatabase mybase = new LibraryDatabase(9);

		// Test 0: Testing emepty database
		assertEquals(0, mybase.size());
		
		
		
		//Test 1: testing database with one element
		mybase.Add(b1);
		assertEquals(1, mybase.size());
		
		//Test many
		mybase.Add(b2);
		mybase.Add(b3);
		mybase.Add(b4);
		mybase.Add(b5);
		
		assertEquals(5, mybase.size());
		
	}
	
	/**
	 * tests the random selection methodd
	 */
	@Test
	public void randomSelectionTester() {
		
		LibraryDatabase mybase = new LibraryDatabase(9);

		// Test 0: Testing emepty database
		assertEquals(null, mybase.randomSelection());
		
		 
		
		//Test 1: testing database with one element
		mybase.Add(b6);
		assertEquals("6666666666666", mybase.randomSelection().getISBN());
				
				
		//Test many: testing database with many elements.
		mybase.Add(b2);
		mybase.Add(b3);
		mybase.Add(b4);
		mybase.Add(b5);	
		mybase.Add(b1);	
		
		boolean b = false;
		
		//checks if the random selection was type of Book. (no way to know the random Book)
		if ( mybase.randomSelection() instanceof Book) {
			b = true;
		}
		
		assertEquals(true, b);

		
	}
	
	/**
	 * tests the find by title method
	 */
	@Test
	public void findByTitleTester() {
		
		LibraryDatabase mybase = new LibraryDatabase(9);
	
		// Test 0: Testing emepty database
		assertEquals(null, mybase.findByTitle("abc"));
	
		// Test 1: testing database with one element
		mybase.Add(b6);
		assertEquals("6666666666666", mybase.findByTitle("pqr").getISBN());
				
				
		// Test many: testing database with many elements.
		mybase.Add(b2);
		mybase.Add(b3);
		mybase.Add(b4);
		mybase.Add(b5);	
		mybase.Add(b1);	
		
		assertEquals("5555555555555", mybase.findByTitle("mno").getISBN());
		assertEquals("6666666666666", mybase.findByTitle("pqr").getISBN());
		assertEquals("3333333333333", mybase.findByTitle("ghi").getISBN());
				
		
		// Test first : Tests first book of the database.
		assertEquals("1111111111111", mybase.findByTitle("abc").getISBN());
		
		// Test middle : Test middle element of the database.
		assertEquals("3333333333333", mybase.findByTitle("ghi").getISBN());
		
		// Test last :  Test last elemet of the database.
		assertEquals("6666666666666", mybase.findByTitle("pqr").getISBN());
	}
	
	/**
	 * tests the findbyISBN title 
	 */
	@Test
	public void findByISBNtester() {
		
		LibraryDatabase mybase = new LibraryDatabase(9);
		
		// Test 0: Testing emepty database
		assertEquals(null, mybase.findByISBN("abc"));
	
		// Test 1: testing database with one element
		mybase.Add(b1);
		assertEquals("1111111111111", mybase.findByISBN("1111111111111").getISBN());
				
				
		// Test many: testing database with many elements.
		mybase.Add(b2);
		mybase.Add(b3);
		mybase.Add(b4);
		mybase.Add(b5);	
		mybase.Add(b6);	
		
		
		
		assertEquals("5555555555555", mybase.findByISBN("5555555555555").getISBN());
		assertEquals("6666666666666", mybase.findByISBN("6666666666666").getISBN());
		assertEquals("3333333333333", mybase.findByISBN("3333333333333").getISBN());
				
		
		// Test first : Tests first book of the database.
		assertEquals("1111111111111", mybase.findByISBN("1111111111111").getISBN());
		
		// Test middle : Test middle element of the database.
		assertEquals("3333333333333", mybase.findByISBN("3333333333333").getISBN());
		
		// Test last :  Test last elemet of the database.
		assertEquals("6666666666666", mybase.findByISBN("6666666666666").getISBN());
		
	}
	
	/**
	 * tets the getALLByAUthor method
	 */
	@Test
	public void getAllByAuthorTester() {
		
		LibraryDatabase mybase = new LibraryDatabase(9);
		
		// Test 0: Testing emepty database
		assertEquals(null, mybase.getAllByAuthor("a")[0]);
	
		// Test 1 and Test firsr: testing database with one element
		mybase.Add(b1);
		assertEquals("a", mybase.getAllByAuthor("a")[0].getAuthor());
				
				
		// Test many: testing database with many elements.
		mybase.Add(b2);
		mybase.Add(b3);
		mybase.Add(b4);
		mybase.Add(b5);	
		
		Book sameAuthorB1 = new Book("rrr" , "2346834628348" , "a");
		Book sameAuthorB11 = new Book("zzz" , "4545645665466" , "a");
		Book sameAuthorB111 = new Book("eee" , "7545645645645" , "a");
		
		mybase.Add(sameAuthorB1);
		mybase.Add(sameAuthorB11);
		mybase.Add(sameAuthorB111);	
		
		// (Tests last as well)
		// same author 1
		assertEquals("a", mybase.getAllByAuthor("a")[0].getAuthor()); 
		// same autjor 2
		assertEquals("a", mybase.getAllByAuthor("a")[1].getAuthor()); 
		// same author 3
		assertEquals("a", mybase.getAllByAuthor("a")[2].getAuthor()); 
		
		
		
	}
	
	/**
	 * tests the the remove duplicates method
	 */
	@Test
	public void removeDuplicatesTester() {
		LibraryDatabase mybase = new LibraryDatabase(4);
		
		// Test 0: Testing empty database
		mybase.removeDuplicates();
		assertEquals(null, mybase.toArray()[0]);
	
		// Test 1: testing database with one element
		mybase.Add(b1);
		mybase.removeDuplicates();
		assertEquals("abc", mybase.toArray()[0].getTitle());
				
				
		// Test many: testing database with many elements.
		// Also tests first and last
		mybase.Add(b1);
		mybase.Add(b1);
		mybase.Add(b2);
		mybase.Add(b2);	
		mybase.Add(b3);	
		mybase.Add(b3);
		
		mybase.removeDuplicates();
		assertEquals("abc", mybase.toArray()[0].getTitle());
		assertEquals("def", mybase.toArray()[1].getTitle());
		assertEquals("ghi", mybase.toArray()[2].getTitle());
		
	}
	
	/**
	 * tests the toArray method
	 */
	@Test
	public void toArrayTester() {
		
		LibraryDatabase mybase = new LibraryDatabase(9);
		
		// Test 0: Testing empty database
		assertEquals(null, mybase.toArray()[0]);
	
		// Test 1: testing database with one element
		mybase.Add(b1);
		assertEquals("abc", mybase.toArray()[0].getTitle());
				
				
		// Test many: testing database with many elements.
		mybase.Add(b2);
		mybase.Add(b3);
		mybase.Add(b4);
		mybase.Add(b5);	
		mybase.Add(b6);	
		
		
		
		assertEquals("5555555555555", mybase.toArray()[4].getISBN());
		assertEquals("6666666666666", mybase.toArray()[5].getISBN());
		assertEquals("3333333333333", mybase.toArray()[2].getISBN());
				
		
		// Test first : Tests first book of the database.
		assertEquals("1111111111111", mybase.toArray()[0].getISBN());
		
		// Test middle : Test middle element of the database.
		assertEquals("3333333333333", mybase.toArray()[2].getISBN());
		
		// Test last :  Test last elemet of the database.
		assertEquals("6666666666666", mybase.toArray()[5].getISBN());
		
	}
	
	
	
}
