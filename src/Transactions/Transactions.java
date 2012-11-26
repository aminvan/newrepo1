package Transactions;

//We need to import the java.sql package to use JDBC
import java.sql.*;
import java.util.*;
//for reading from the command line
//import java.io.*;
import java.sql.Connection;

import Objects.Book;
import Objects.BookCopy;
import Objects.Borrower;
import Objects.CheckedOutBook;



public class Transactions {
	
	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	boolean connected = false;
	
	public Transactions(){
		if (connected == false){
			connect();
		}
	}
	
	public boolean connect()
 {
   String connectURL =  "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug"; 

   try 
   {
	// Load the Oracle JDBC driver
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
   }
   catch (SQLException ex)
   {
	System.out.println("Message: " + ex.getMessage());
	System.exit(-1);
   }
   
   try 
   {
	connection = DriverManager.getConnection(connectURL,"ora_e6i7","a22749097");

	System.out.println("\nConnected to Oracle!");
	connected = true;
	return true;
   }
   catch (SQLException ex)
   {
	System.out.println("Message: " + ex.getMessage());
	return false;
   }
 }
	
	public void insertBorrowerType(String type, String time){
		
		
		try
		{
		  ps = connection.prepareStatement("INSERT INTO borrowerType VALUES (?,?)");
		  ps.setString(1, type);
		  ps.setString(2, time);

		  //System.out.println("all added");
		  ps.executeUpdate();
		  //System.out.println("executed");
		  // commit work 
		  connection.commit();
		  //System.out.println("committed");
		  ps.close();
		  System.out.println("Inserted into borrowerType");
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    try 
		    {
			// undo the insert
			connection.rollback();	
		    }
		    catch (SQLException ex2)
		    {
			System.out.println("Message: " + ex2.getMessage());
			System.exit(-1);
		    }
		}
	    }
		
	public void insertBorrower(int bid, String password, String name, String address, String phone, String email, String sin, String exp, String type){
		//TODO make date an int?
		
		try
		{
		  ps = connection.prepareStatement("INSERT INTO borrower VALUES (?,?,?,?,?,?,?,?,?)");
		  ps.setInt(1, bid);
		  ps.setString(2, password);
		  ps.setString(3, name);
		  ps.setString(4, address);
		  ps.setString(5, phone);
		  ps.setString(6, email);
		  ps.setString(7, sin);
		  ps.setString(8, exp);
		  ps.setString(9, type);

		  ps.executeUpdate();
		  // commit work 
		  connection.commit();
		  ps.close();
		  System.out.println("Inserted into borrower");
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    try 
		    {
			// undo the insert
			connection.rollback();	
		    }
		    catch (SQLException ex2)
		    {
			System.out.println("Message: " + ex2.getMessage());
			System.exit(-1);
		    }
		}
	    }
	
	public boolean insertBook(String callnum, int isbn, String title, String mainAuthor, String publisher, int year ){
		
		
		try
		{
		  ps = connection.prepareStatement("INSERT INTO book VALUES (?,?,?,?,?,?)");
		  ps.setString(1, callnum);
		  ps.setInt(2, isbn);
		  ps.setString(3, title);
		  ps.setString(4, mainAuthor);
		  ps.setString(5, publisher);
		  ps.setInt(6, year);

		  //System.out.println("all added");
		  ps.executeUpdate();
		  //System.out.println("executed");
		  // commit work 
		  connection.commit();
		  //System.out.println("committed");
		  ps.close();
		  System.out.println("Inserted into book");
		  return true;
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    try 
		    {
			// undo the insert
			connection.rollback();	
			return false;
		    }
		    catch (SQLException ex2)
		    {
			System.out.println("Message: " + ex2.getMessage());
			System.exit(-1);
			return false;
		    }
		}
	    }
	
	public void insertBookCopy(String callnum, int copynum, String status){
		
		
		try
		{
		  ps = connection.prepareStatement("INSERT INTO bookCopy VALUES (?,?,?)");
		  ps.setString(1, callnum);
		  ps.setInt(2, copynum);
		  ps.setString(3, status);


		  //System.out.println("all added");
		  ps.executeUpdate();
		  //System.out.println("executed");
		  // commit work 

		  connection.commit();

		  //System.out.println("committed");
		  ps.close();
		  System.out.println("Inserted into bookCopy");
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    try 
		    {
			// undo the insert
			connection.rollback();	
		    }
		    catch (SQLException ex2)
		    {
			System.out.println("Message: " + ex2.getMessage());
			System.exit(-1);
		    }
		}
	    }
	
	public ArrayList<Borrower> getAllBorrowers()
 {
		
		//TODO
		//loop through columns and add variables to a Srting[]
		//take out the prints
		//add functionality to select a certain attribute
		//return one long String[] for now
		//divide up the string by the number of parameters and make a matrix?
	ArrayList<Borrower> returnQuery = new ArrayList<Borrower>();
	Statement  stmt;
	ResultSet  rs;
	
	   
	try
	{
	  stmt = connection.createStatement();
	  rs = stmt.executeQuery("SELECT * FROM borrower");
	  // get info on ResultSet
	  ResultSetMetaData rsmd = rs.getMetaData();
	  // get number of columns
	  int numCols = rsmd.getColumnCount();
	  //System.out.println(" ");
	  // display column names;
	  for (int i = 0; i < numCols; i++)
	  {
	      // get column name and print it
	     // System.out.printf("%-15s", rsmd.getColumnName(i+1));    
	  }
	  //System.out.println(" ");
	  while(rs.next())
	  {
		    Borrower b = new Borrower ();
	      	b.setBid(rs.getString("BID"));
	  		b.setPassword(rs.getString("PASSWORD"));
	  		b.setName(rs.getString("NAME"));
	  		b.setAddress(rs.getString("ADDRESS"));
	  		b.setPhone(rs.getString("PHONE"));
	  		b.setEmailAddress(rs.getString("EMAILADDRESS"));
	  		b.setSinOrStNo(rs.getString("SINORSTNO"));
	  		b.setExpiryDate(rs.getString("EXPIRYDATE"));
	  		b.setType(rs.getString("TYPE"));
	  		
	  		
	  		returnQuery.add(b);
	  }

	  // close the statement; 
	  // the ResultSet will also be closed
	  stmt.close();
	  return returnQuery;
	}
	catch (SQLException ex)
	{
	    System.out.println("Message: " + ex.getMessage());
	    return null;
	}	
 }
 
	//set default input to null	
	//returns callnumber, title, status, outdate, indate
	public ArrayList<CheckedOutBook> getCheckedOutBooks(String subject)
 {

	ArrayList<CheckedOutBook> returnQuery = new ArrayList<CheckedOutBook>();
	Statement  stmt;
	ResultSet  rs;
	
	   
	try
	{
		
	  stmt = connection.createStatement();
	  
	  if (subject != null){
		  String query = String.format("SELECT * FROM bookCopy, borrowing, book, hasSubject WHERE status = 'out' and hasSubject.callnumber = book.callnumber and bookcopy.callnumber = borrowing.callnumber and bookcopy.copyno = borrowing.copyno and bookcopy.callnumber = book.callnumber and subject = '%s'",subject);
		  rs = stmt.executeQuery(query);
	  }
	  else{
		  rs = stmt.executeQuery("SELECT * FROM bookCopy, borrowing, book WHERE status = 'out' and bookcopy.callnumber = borrowing.callnumber and bookcopy.copyno = borrowing.copyno and bookcopy.callnumber = book.callnumber");
	  }
	  
	  // get info on ResultSet
	  ResultSetMetaData rsmd = rs.getMetaData();
	  // get number of columns
	  int numCols = rsmd.getColumnCount();
	  //System.out.println(" ");
	  // display column names;
	  for (int i = 0; i < numCols; i++)
	  {
	      // get column name and print it
	     // System.out.printf("%-15s", rsmd.getColumnName(i+1));    
	  }
	  //System.out.println(" ");
	  while(rs.next())
	  {
	        CheckedOutBook c = new CheckedOutBook();
	      	c.callNumber = rs.getString("CALLNUMBER");
	      	c.title = rs.getString("TITLE");
	      	c.status = rs.getString("STATUS");
	  		c.outDate = rs.getString("OUTDATE");
	  		c.inDate = rs.getString("INDATE");

	  		returnQuery.add(c);
	  }

	  // close the statement; 
	  // the ResultSet will also be closed
	  stmt.close();
	  return returnQuery;
	}
	catch (SQLException ex)
	{
	    System.out.println("Message: " + ex.getMessage());
	    return null;
	}	
 }
 
	//input null by default eg. showBookSearch(null,null,"adventure") to search for adventure books
	//returns: callnumber, title, author, subject status
	public ArrayList<Book> showBookSearch(String titlein, String authorin, String subjectin)
	 {
			//TODO
		

		ArrayList<Book> returnQuery = new ArrayList<Book>();
		Statement  stmt;
		ResultSet  rs;
		
		String titleString = "";
		String authorString = "";
		String subjectString = "";
		
		if (titlein != null){
			titleString = String.format("and title = '%s'", titlein);
		}
		if(authorin != null){
			authorString = String.format("and mainauthor = '%s'", authorin);
		}
		if(subjectin != null){
			subjectString = String.format("and subject = '%s'", subjectin);			
		}
		
		
		String inputString = titleString + authorString + subjectString;
		String queryString = String.format("Select *  FROM book, bookCopy, hasSubject WHERE book.callnumber = bookcopy.callnumber and book.callnumber = hasSubject.callnumber %s", inputString);
		System.out.println(queryString);

		try
		{
		  stmt = connection.createStatement();
		  rs = stmt.executeQuery(queryString);
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();
		  // get number of columns
		  int numCols = rsmd.getColumnCount();
		  //System.out.println(" ");
		  // display column names;
		  for (int i = 0; i < numCols; i++)
		  {
		      // get column name and print it
		     // System.out.printf("%-15s", rsmd.getColumnName(i+1));    
		  }
		  //System.out.println(" ");
		  while(rs.next())
		  {
		        BookCopy b = new BookCopy();
		      	b.callNumber = rs.getString("CALLNUMBER");
		  		b.title = rs.getString("TITLE");
		  		b.mainAuthor = rs.getString("MAINAUTHOR");
		  		b.subject = rs.getString("SUBJECT");
		  		b.status = rs.getString("STATUS");
		  		b.isbn= rs.getString("ISBN");
		  		b.year = rs.getString("YEAR");
		  		b.publisher = rs.getString("PUBLISHER");
		  		returnQuery.add(b);
		  }
		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		  return returnQuery;
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    return null;
		}	
	 }
	 
	public ArrayList<BookCopy> getAllBookCopies()
	 {
		ArrayList<BookCopy> returnQuery = new ArrayList<BookCopy>();
		Statement  stmt;
		ResultSet  rs;
		
		   
		try
		{
		  stmt = connection.createStatement();
		  rs = stmt.executeQuery("SELECT * FROM bookcopy");
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();
		  // get number of columns
		  int numCols = rsmd.getColumnCount();
		  //System.out.println(" ");
		  // display column names;
		  for (int i = 0; i < numCols; i++)
		  {
		      // get column name and print it
		     // System.out.printf("%-15s", rsmd.getColumnName(i+1));    
		  }
		  //System.out.println(" ");
		  while(rs.next())
		  {
		        BookCopy bc = new BookCopy();
		      	bc.callNumber = rs.getString("CALLNUMBER");
		  		bc.copyNum = rs.getString("COPYNO");
		  		bc.status = rs.getString("STATUS");

		  		
		  		returnQuery.add(bc);


		  }

		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		  return returnQuery;
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    return null;
		}	
	 }

	public int showBorrowersTimeLimit(int bidin)
	 {
			
			//TODO
			
			String     timeLimit="";

		Statement  stmt;
		ResultSet  rs;
		
		   
		try
		{
			
		  stmt = connection.createStatement();
		  

			  String query = String.format("SELECT booktimelimit FROM borrower, borrowerType WHERE borrower.type = borrowerType.type and borrower.bid = %d",bidin);
			  System.out.println(query);
			  rs = stmt.executeQuery(query);

		  
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();
		  // get number of columns
		  int numCols = rsmd.getColumnCount();
		  //System.out.println(" ");
		  // display column names;
		  for (int i = 0; i < numCols; i++)
		  {
		      // get column name and print it
		     // System.out.printf("%-15s", rsmd.getColumnName(i+1));    
		  }
		  //System.out.println(" ");
		  while(rs.next())
		  {
		      	timeLimit = rs.getString("BOOKTIMELIMIT");
		  }

		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		  return Integer.parseInt( timeLimit );
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    return 0;
		}	
	 }
	
	public ArrayList<Book> getAllBorrowedBooks()
	 {
			
			//TODO
			
		String     callnum;
		String     title;

		ArrayList<Book> returnQuery = new ArrayList<Book>();
		Statement  stmt;
		ResultSet  rs;
		
		   
		try
		{
			
		  stmt = connection.createStatement();
		  

			  String query = String.format("SELECT book.callnumber, book.title FROM book, borrowing WHERE book.callnumber = borrowing.callnumber");
			  System.out.println(query);
			  rs = stmt.executeQuery(query);

		  
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();
		  // get number of columns
		  int numCols = rsmd.getColumnCount();
		  //System.out.println(" ");
		  // display column names;
		  for (int i = 0; i < numCols; i++)
		  {
		      // get column name and print it
		     // System.out.printf("%-15s", rsmd.getColumnName(i+1));    
		  }
		  //System.out.println(" ");
		  while(rs.next())
		  {
			  Book b = new Book();
		      	b.callNumber = rs.getString("CALLNUMBER");
		      	b.title = rs.getString("TITLE");
		  		returnQuery.add(b);
		  }

		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		  return returnQuery;
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    return null;
		}	
	 }
	
	public ArrayList<String> processReturn(int callnum, int copynum)
	 {
			
			//TODO
			
		String     status;
		String     bid;
		String     indate;

		ArrayList<String> returnQuery = new ArrayList<String>();
		Statement  stmt;
		ResultSet  rs;
		
		   
		try
		{
			
		  stmt = connection.createStatement();
		  

			  String query = String.format("SELECT bookCopy.status, borrowing.bid, borrowing.indate FROM bookCopy, borrowing WHERE bookCopy.callNumber = borrowing.callNumber and bookCopy.callNumber = %d and bookCopy.copyNo = %d ", callnum, copynum);
			  System.out.println(query);
			  rs = stmt.executeQuery(query);

		  
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();
		  // get number of columns
		  int numCols = rsmd.getColumnCount();
		  //System.out.println(" ");
		  // display column names;
		  for (int i = 0; i < numCols; i++)
		  {
		      // get column name and print it
		     // System.out.printf("%-15s", rsmd.getColumnName(i+1));    
		  }
		  //System.out.println(" ");
		  while(rs.next())
		  {
		      	status = rs.getString("STATUS");
		      	bid = rs.getString("BID");
		      	indate = rs.getString("INDATE");
		  		returnQuery.add(status);
		  		returnQuery.add(bid);
		  		returnQuery.add(indate);
		  }

		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		  return returnQuery;
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    return null;
		}	
	 }

	
	
	
public boolean updateBookCopyStatus(int callnum, int copynum, String status){
		
		
		try
		{
			String query = String.format("UPDATE bookCopy SET status = '%s' WHERE callnumber = %d and copyno = %d",status, callnum, copynum);
			System.out.println(query);
			ps = connection.prepareStatement(query);
		  ps.executeUpdate();
		  connection.commit();
		  ps.close();
		  System.out.println("Updated bookCopy");
		  return true;
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		    try 
		    {
			// undo the insert
			connection.rollback();	
		    }
		    catch (SQLException ex2)
		    {
			System.out.println("Message: " + ex2.getMessage());
			System.exit(-1);
		    }
		    return false;
		}
	    }




}


	

