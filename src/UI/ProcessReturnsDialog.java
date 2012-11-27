package UI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Objects.BookCopy;
import Objects.Borrower;
import Objects.Borrowing;
import Transactions.Transactions;

public class ProcessReturnsDialog extends JFrame implements ActionListener{

	JTextField borrowerID = new JTextField();
	JTextField bookCallNumber = new JTextField();
	JTextField bookCopyNo = new JTextField();
	DefaultListModel listModel = new DefaultListModel();
	
	static String CHECKIN = "Check In Books";
	static String returnToClerkDialogString = "Return to Clerk Dialog";
	
	public ProcessReturnsDialog(String name)
	{
		super(name);
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));
		
		panel.add(new Label("Check In Books"));
		panel.add(new Label(""));
		
		panel.add(new Label("Call number"));
		panel.add(bookCallNumber);
		
		panel.add(new Label("Copy No"));
		panel.add(bookCopyNo);
		
		JButton returnToUserDialog = new JButton(returnToClerkDialogString);
		returnToUserDialog.setActionCommand(returnToClerkDialogString);
		returnToUserDialog.addActionListener(this);
		
		JButton checkInButton = new JButton(CHECKIN);
		checkInButton.setActionCommand(CHECKIN);
		checkInButton.addActionListener(this);
		
		panel.add(returnToUserDialog);
		panel.add(checkInButton);
		
		pane.add(panel);
	}
    public static void createAndShowGUI() {
        //Create and set up the window.
        ProcessReturnsDialog frame = new ProcessReturnsDialog("Check In Items");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (returnToClerkDialogString.equals(arg0.getActionCommand()))
		{
			this.dispose();
		}else if(arg0.getActionCommand().equals(CHECKIN))
		{
			BookCopy bc = getBookCopy();
			if (bc != null && bc.status != null)
			{
				Transactions t = new Transactions();
				t.updateBookCopyStatus(bc.callNumber, bc.copyNum, Constants.IN);
				Borrowing b = this.getBorrowing(bc);
				if (b != null && b.outDate != null)
				{
					Borrower ber = this.getBorrower(b);
					if (ber != null && ber.getType() != null)
					{
						insertFineIfNeeded(b, ber);
					}
				}
				
				
				
				
			}else
			{
				GiveMeTitleAndMessageDialog.createAndShowGUI("Error", "Book Copy with given call number and copy number not found.");
			}
			String callNumber = bookCallNumber.getText().trim();
			listModel.addElement(callNumber);
			bookCallNumber.setText("");
			bookCopyNo.setText("");
		}
		
		
	}
	
	private void insertFineIfNeeded(Borrowing bor, Borrower b)
	{
		String type = b.getType();
		String outDate = bor.getOutDate();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try{
			Date outD = dateFormat.parse(outDate);
			Date expDate = this.getReturnDate(type, outD);
			if (expDate.before(this.getCurrentDate()))
			{
				Transactions t = new Transactions();
				t.insertFine(1, 2, dateFormat.format(this.getCurrentDate()), null, bor.borid);
			}
		}catch (Exception e)
		{
			
		}
		
		
	}
	private Borrower getBorrower(Borrowing b)
	{
		Transactions t = new Transactions();
		return (t.showBorrowerById(b.getBid()));
	}
	
	private Borrowing getBorrowing (BookCopy bc)
	{
		Transactions t = new Transactions();
		List<Borrowing> borrowing = t.showCheckedOutBorrowing();
		List<Borrowing> all = t.showAllBorrowing();
		
		for (Borrowing b : t.showCheckedOutBorrowing())
		{
			if (b.callNumber == bc.callNumber && b.copyNo == bc.copyNum && b.inDate.equals("null"))
			{
				return b;
			
			}
		}
		return null;
	}
	
	private BookCopy getBookCopy()
	{
		try
		{
			int callNumber = Integer.parseInt(bookCallNumber.getText().trim());
			int copyNo = Integer.parseInt(bookCopyNo.getText().trim());
			Transactions t = new Transactions();
			BookCopy bc = t.showCopyOfGivenBook(callNumber, copyNo);
			return bc;
		}catch(Exception e)
		{
			GiveMeTitleAndMessageDialog.createAndShowGUI("Error", "Please input integers and try again");
			return null;
		}
	}
	
	private Date getCurrentDate()
	{
		Date date = new Date();
		return date;
	}
	
	private Date getReturnDate(String borrowerType, Date outDate)
	{
		Calendar c = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		c.setTime(outDate);
		if (borrowerType.equals(Constants.STAFF))
		{
			c.add(Calendar.DATE, 42);
		}else if (borrowerType.equals(Constants.STUDENT))
		{
			c.add(Calendar.DATE, 14);
		}else if (borrowerType.equals(Constants.FACULTY))
		{
			c.add(Calendar.DATE, 84);
		}else
		{
			c.add(Calendar.DATE, 14);
		}

		return c.getTime();
		 
	}
}
