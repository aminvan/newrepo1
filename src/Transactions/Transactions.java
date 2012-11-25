package Transactions;

//We need to import the java.sql package to use JDBC
import java.sql.*;
import java.util.*;
//for reading from the command line
//import java.io.*;
import java.sql.Connection;



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
	
	public void insertBook(String callnum, int isbn, String title, String mainAuthor, String publisher, int year ){
		
		
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
	
	
	public ArrayList<String> showBorrower()
 {
		
		//TODO
		//loop through columns and add variables to a Srting[]
		//take out the prints
		//add functionality to select a certain attribute
		//return one long String[] for now
		//divide up the string by the number of parameters and make a matrix?
	String     bid;
	String     name;
	String     addr;
	String     password;
	String     phone;
	String     email;
	String     sin;
	String     exp;
	String     type;
	ArrayList<String> returnQuery = new ArrayList<String>();
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
	      
	      	bid = rs.getString("BID");
	  		password = rs.getString("PASSWORD");
	  		name = rs.getString("NAME");
	  		addr = rs.getString("ADDRESS");
	  		phone = rs.getString("PHONE");
	  		email = rs.getString("EMAILADDRESS");
	  		sin = rs.getString("SINORSTNO");
	  		exp = rs.getString("EXPIRYDATE");
	  		type = rs.getString("TYPE");
	  		
	  		returnQuery.add(bid);
	  		returnQuery.add(password);
	  		returnQuery.add(name);
	  		returnQuery.add(addr);
	  		returnQuery.add(phone);
	  		returnQuery.add(email);
	  		returnQuery.add(sin);
	  		returnQuery.add(exp);
	  		returnQuery.add(type);
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
	public ArrayList<String> showCheckedOutBooks(String subject)
 {
		
		//TODO
		
		String     callnum;
		String     status;
		String     title;
		String     outdate;
		String     indate;

	ArrayList<String> returnQuery = new ArrayList<String>();
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
	      
	      	callnum = rs.getString("CALLNUMBER");
	      	title = rs.getString("TITLE");
	      	status = rs.getString("STATUS");
	  		outdate = rs.getString("OUTDATE");
	  		indate = rs.getString("INDATE");

	  		returnQuery.add(callnum);
	  		returnQuery.add(title);
	  		returnQuery.add(status);
	  		returnQuery.add(outdate);
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
 
	
	//input null by default eg. showBookSearch(null,null,"adventure") to search for adventure books
	//returns: callnumber, title, author, subject status
	public ArrayList<String> showBookSearch(String titlein, String authorin, String subjectin)
	 {
			//TODO
			//loop through columns and add variables to a String[]
			//take out the prints
			//add functionality to select a certain attribute
			//return one long String[] for now
			//divide up the string by the number of parameters and make a matrix?
		String     callnum;
		String     title;
		String     mainauthor;
		String     subject;
		String     status;

		ArrayList<String> returnQuery = new ArrayList<String>();
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
		      
		      	callnum = rs.getString("CALLNUMBER");
		  		title = rs.getString("TITLE");
		  		mainauthor = rs.getString("MAINAUTHOR");
		  		subject = rs.getString("SUBJECT");
		  		status = rs.getString("STATUS");
		  		
		  		returnQuery.add(callnum);
		  		returnQuery.add(title);
		  		returnQuery.add(mainauthor);
		  		returnQuery.add(subject);
		  		returnQuery.add(status);
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
	 
	
	
	public ArrayList<String> showBookCopy()
	 {
			
			//TODO
			//loop through columns and add variables to a Srting[]
			//take out the prints
			//add functionality to select a certain attribute
			//return one long String[] for now
			//divide up the string by the number of parameters and make a matrix?
		String     callnum;
		String     copynum;
		String     status;


		ArrayList<String> returnQuery = new ArrayList<String>();
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
		      
		      	callnum = rs.getString("CALLNUMBER");
		  		copynum = rs.getString("COPYNO");
		  		status = rs.getString("STATUS");

		  		
		  		returnQuery.add(callnum);
		  		returnQuery.add(copynum);
		  		returnQuery.add(status);


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
	 
	
	
	
}


	

