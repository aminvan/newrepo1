package Objects;

public class BookCopy extends Book {

	public int copyNum;
	public String status;
	public BookCopy()
	{
		
	}
	
	public BookCopy (String callNumber, String isbn, String title, String mainAuthor, String publisher, String year, String subject, String status)
	{
		super(callNumber, isbn, title, mainAuthor, publisher, year, subject);
		this.status = status;
	}
	
	public BookCopy (int copyNum, String callNumber, String isbn, String title, String mainAuthor, String publisher, String year, String subject, String status)
	{
		super(callNumber, isbn, title, mainAuthor, publisher, year, subject);
		this.status = status;
		this.copyNum = copyNum;
	}
}
