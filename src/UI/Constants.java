package UI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {


	public static String RETURN_TO_CHOOSE_USER_DIALOG = "Return to Choose User Dialog";
	public static String OK = "Ok";
	public static String SUCCESS = "Success";
	public static String ERROR = "Error";
	public static String AN_ERROR_OCCURRED = "An error occurred.  Please check your inputs and try again.";
	public static String IN = "in";
	public static String OUT = "out";
	public static String ON_HOLD = "on hold";
	public static String STUDENT = "student";
	public static String FACULTY = "faculty";
	public static String STAFF = "staff";
	public static String GEN_PUB = "general public";
	public static String NULL = "null";
	
	public static String getCurrentDateInStringFormat()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
