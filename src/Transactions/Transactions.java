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
 
}

	

