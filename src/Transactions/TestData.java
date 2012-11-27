package Transactions;

public class TestData {

	String callNumber, title, mainAuthor, publisher, subject, status;
	int isbn, year;
	
	private Transactions trans;
	
	public TestData() {
		
	}
	
	public void insertBookData() {
		trans.insertBook("QA76.73.J38 2004", 1590591232, "Advanced Java Game Programming", "David Wallace Croft", "Berkley Press", 2004);
		trans.insertBook("QA39.3.E65 2004", 534490964, "Discrete Mathematics with Applications", "Susanna S. Epp", "Thomson-Brooks/Cole", 2004);
		trans.insertBook("QA76.9.A43 G67 2002", 471383651, "Algorithm Design", "Michael T. Goodrich", "Wiley", 2002);
		trans.insertBook("PR6068.O93 Z68 2003", 403912645, "Re-reading Harry Potter", "Suman Gupta", "Palgrave Macmillan", 2003);
		trans.insertBook("GE160.A35 O35 2010", 781617288, "Africa: the driest continent", "Abdeen Mustafa Omer", "Nova Science", 2010);
		trans.insertBook("QC990.I4 S55 2010", 176580597, "Global warming: India's response to climate change, disasster mitigation and adaptation", "Pawan Sikka", "Uppal Publishing House", 2010);
		trans.insertBook("QA76.73.J38 L25 2000", 534366910, "Java: a framework for program design and data structures", "Martin Osborne", "Brooks/Cole", 2000);
		trans.insertBook("QA76.73.C153 M268 2011", 471383651, "C++ programming: from problem analysis to program design", "D.S. Malik", "Course Technology", 2011);
		trans.insertBook("QA297.A748 2011", 898719976, "A first course in numerical methods", "U.M. Ascher", "Society for Industrial and Applied Mathematics", 2011);
		trans.insertBook("QA76.6.M3585 2009", 0071624724, "Programming: a beginner's guide", "Richard Mansfield", "McGraw-Hill", 2009);
	}
	
	public void insertHasSubjData() {
		trans.insertHasSubject("QA76.73.J38 2004","java");
		trans.insertHasSubject("QA76.73.J38 2004","programming");
		trans.insertHasSubject("QA76.73.J38 2004","computer games");
		trans.insertHasSubject("QA76.73.J38 2004","computer science");
		
		trans.insertHasSubject("QA39.3.E65 2004","discrete mathematics");
		
		trans.insertHasSubject("QA76.9.A43 G67 2002","computer science");
		trans.insertHasSubject("QA76.9.A43 G67 2002","programming");
		trans.insertHasSubject("QA76.9.A43 G67 2002","algorithms");
		
		trans.insertHasSubject("PR6068.O93 Z68 2003","harry potter");
		
		trans.insertHasSubject("GE160.A35 O35 2010","africa");
		trans.insertHasSubject("GE160.A35 O35 2010","continent");
		
		trans.insertHasSubject("QC990.I4 S55 2010","global warming");
		trans.insertHasSubject("QC990.I4 S55 2010","india");
		trans.insertHasSubject("QC990.I4 S55 2010","climate change");
		
		trans.insertHasSubject("QA76.73.J38 L25 2000","java");
		trans.insertHasSubject("QA76.73.J38 L25 2000","computer science");
		trans.insertHasSubject("QA76.73.J38 L25 2000","programming");
		
		trans.insertHasSubject("QA76.73.C153 M268 2011","c++");
		trans.insertHasSubject("QA76.73.C153 M268 2011","computer science");
		trans.insertHasSubject("QA76.73.C153 M268 2011","programming");
		
		trans.insertHasSubject("QA297.A748 2011","numerical methods");
		trans.insertHasSubject("QA297.A748 2011","computer science");
		
		trans.insertHasSubject("QA76.6.M3585 2009","computer science");
		trans.insertHasSubject("QA76.6.M3585 2009","programming");		
	}
	
	public void insertHasAuthorData() {
		trans.insertHasAuthor("QA76.73.J38 2004", "David Wallace Croft");
		trans.insertHasAuthor("QA39.3.E65 2004", "Susanna S. Epp");
		trans.insertHasAuthor("QA76.9.A43 G67 2002", "Michael T. Goodrich");
		trans.insertHasAuthor("PR6068.O93 Z68 2003", "Suman Gupta");
		trans.insertHasAuthor("GE160.A35 O35 2010", "Abdeen Mustafa Omer");
		trans.insertHasAuthor("QC990.I4 S55 2010", "Pawan Sikka");
		trans.insertHasAuthor("QA76.73.J38 L25 2000", "Martin Osborne");
		trans.insertHasAuthor("QA76.73.C153 M268 2011", "D.S. Malik");
		trans.insertHasAuthor("QA297.A748 2011", "U.M. Ascher");
		trans.insertHasAuthor("QA76.6.M3585 2009", "Richard Mansfield");
	}
	
	
	public void insertBorrowerData() {
		trans.insertBorrowerForTestData("100000", "786362", "John Smith", "200 Beach Ave", "6048233829", "jsmith@mail.com", "12313849", "01-jan-2020", "student");
		trans.insertBorrowerForTestData("100001", "392024", "Jane Clark", "3829 Sunset Dr", "7882340101", "clarkj@abc.com", "48393202", "27-may-2018", "student");
		trans.insertBorrowerForTestData("100002", "289238", "Calvin Klein", "100 Church St", "7889112020", "ck@mail.com", "39201391", "05-jan-2013", "student");
		trans.insertBorrowerForTestData("100003", "992928", "Mickey Mouse", "90 Disney Dr", "2507892343", "mmouse@cartoon.com", "38291111", "25-feb-2020", "student");
		trans.insertBorrowerForTestData("100004", "123123", "Donald Duck", "88 Space Mountain Rd", "5198093456", "quackers@rus.com", "10020030", "10-oct-2019", "student");
		
		trans.insertBorrowerForTestData("100005", "234213", "Prof Plum", "111 Candlestick Dr", "2322344686", "pplum@instudy.com", "281835829", "12-oct-2016", "faculty");
		trans.insertBorrowerForTestData("100006", "d8s3k2", "John Brown", "234 River Rd", "2342432879", "jbrown@instudy.com", "465485447", "21-mar-2013", "faculty");
		trans.insertBorrowerForTestData("100007", "s9sfd8", "Jim Flack", "565 Taylor Way", "8881233829", "flack@up.com", "343532342", "05-nov-2013", "faculty");
		trans.insertBorrowerForTestData("100008", "32kj42", "Barbara Paul", "2342 Marine Dr", "8272347842", "paulb@here.com", "345364534", "10-may-2014", "faculty");
		trans.insertBorrowerForTestData("100009", "l23424", "Ian Fleming", "3732 Granville St", "4839202010", "iflem@jb.com", "123123123", "15-mar-2015", "faculty");
		
		trans.insertBorrowerForTestData("100010", "234213", "Donna Clark", "546 Lakeway Dr", "6048729312", "dclark@univ.edu", "234165874", "02-oct-2014", "staff");
		trans.insertBorrowerForTestData("100011", "d8s3k2", "John Green", "123 Hill Rd", "2342234234", "jgreen@why.com", "978351756", "03-mar-2015", "staff");
		trans.insertBorrowerForTestData("100012", "s9sfd8", "Gail Smith", "435 Lucky Way", "2342235556", "gsmith@sky.com", "393882573", "31-dec-2013", "staff");
		trans.insertBorrowerForTestData("100013", "32kj42", "Bob Park", "5453 Park Dr", "1028282737", "ballpark@mail.com", "112934228", "30-may-2013", "staff");
		trans.insertBorrowerForTestData("100014", "l23424", "Lewis Carrol", "352 Sewer St", "2349172732", "lcarroll@mail.co.uk", "445889282", "01-jan-2015", "staff");
	} 
	
	public void insertBookCopyData() {
		trans.insertBookCopy("QA297.A748 2011", 1, "out");
		trans.insertBookCopy("QA297.A748 2011", 2, "out");
		trans.insertBookCopy("QA297.A748 2011", 3, "on-hold");
		trans.insertBookCopy("QA39.3.E65 2004", 1, "on-hold");
		trans.insertBookCopy("QA76.9.A43 G67 2002", 1, "in");
		trans.insertBookCopy("QA76.9.A43 G67 2002", 2, "in");
		trans.insertBookCopy("PR6068.O93 Z68 2003", 1, "in");
		trans.insertBookCopy("GE160.A35 O35 2010", 1, "on-hold");
		trans.insertBookCopy("GE160.A35 O35 2010", 2, "on-hold");
		trans.insertBookCopy("QC990.I4 S55 2010", 1, "out");
		trans.insertBookCopy("QA76.73.J38 L25 2000", 1, "out");
		trans.insertBookCopy("QA76.73.C153 M268 2011", 1, "on-hold");
		trans.insertBookCopy("QA76.73.C153 M268 2011", 2, "out");
		trans.insertBookCopy("QA76.73.C153 M268 2011", 3, "out");
		trans.insertBookCopy("QA76.73.C153 M268 2011", 4, "out");
		trans.insertBookCopy("QA76.73.C153 M268 2011", 5, "out");
		trans.insertBookCopy("QA297.A748 2011", 1, "in");
		trans.insertBookCopy("QA76.6.M3585 2009", 1, "in");
		trans.insertBookCopy("QA76.6.M3585 2009", 2, "out");
	}
	
	public void insertBorrowingData() {
		trans.insertBorrowingForTestData(1000,100000,"QA297.A748 2011", 1, "03-SEP-2012",null);
		trans.insertBorrowingForTestData(1001,100001,"QA297.A748 2011", 2, "08-oct-2012",null);
		trans.insertBorrowingForTestData(1002,100000,"QC990.I4 S55 2010", 1, "01-oct-2012","12-nov-2012");
		trans.insertBorrowingForTestData(1003,100000,"QA76.73.J38 L25 2000", 1, "01-oct-2012","12-nov-2012");
		trans.insertBorrowingForTestData(1004,100007,"QA76.73.C153 M268 2011", 2, "28-oct-2012",null);
		trans.insertBorrowingForTestData(1005,100005,"QA76.73.C153 M268 2011", 3, "16-jul-2012","06-aug-2012");
		trans.insertBorrowingForTestData(1006,100009,"QA76.73.C153 M268 2011", 4, "17-sep-2012","08-oct-2012");
		trans.insertBorrowingForTestData(1007,100013,"QA76.73.C153 M268 2011", 5, "15-oct-2012","05-nov-2012");
		trans.insertBorrowingForTestData(1008,100000,"QA76.6.M3585 2009", 2, "03-SEP-2012",null);
	}
	
	public void insertHoldReqData() {
		trans.insertHoldReq(1000,100002,"QA297.A748 2011", "20-NOV-2012");
		trans.insertHoldReq(1001,100004,"QA39.3.E65 2004", "14-NOV-2012");
		trans.insertHoldReq(1002,100003,"GE160.A35 O35 2010", "10-Nov-2012");
		trans.insertHoldReq(1003,100008,"GE160.A35 O35 2010", "12-Nov-2012");
		trans.insertHoldReq(1004,100002,"QA76.73.C153 M268 2011", "31-oct-2012");
	}
	
	public void insertFineData() {
		trans.insertFine(1000,"3.00","03-Sep-2011", "10-Sep-2012", 1000);
		trans.insertFine(1001,"0.50","06-Oct-2012", "07-Oct-2012", 1000);
		
		trans.insertFine(1002,"11.25","01-oct-2012", null, 1008);
		
		trans.insertFine(1003,"4.50","01-nov-2012", null, 1012);
		
		trans.insertFine(1004,"1.25","21-aug-2012", null, 1004);
		trans.insertFine(1005,"0.25","17-nov-2012", null, 1004);	
	}
	
}

