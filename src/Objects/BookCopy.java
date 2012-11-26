package Objects;

public class BookCopy extends Book {

	
	public String copyNum, status;
	public BookCopy()
	{
		
	}
	
	public BookCopy (String callNumber, String isbn, String title, String mainAuthor, String publisher, String year, String subject, String status)
	{
		super(callNumber, isbn, title, mainAuthor, publisher, year, subject);
		this.status = status;
	}
}
