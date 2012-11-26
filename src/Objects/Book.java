package Objects;

public class Book {

	public int callNumber;
	public String isbn, title, mainAuthor, publisher, year, subject;
	
	public Book()
	{
		
	}
	public Book (int callNumber, String isbn, String title, String mainAuthor, String publisher, String year, String subject)
	{
		this.callNumber = callNumber;
		this.isbn = isbn;
		this.mainAuthor = mainAuthor;
		this.publisher = publisher;
		this.title = title;
		this.year = year;
		this.subject = subject;
	}

	public int getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(int callNumber) {
		this.callNumber = callNumber;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMainAuthor() {
		return mainAuthor;
	}

	public void setMainAuthor(String mainAuthor) {
		this.mainAuthor = mainAuthor;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
