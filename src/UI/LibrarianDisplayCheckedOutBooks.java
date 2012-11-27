package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import Objects.BookCopy;
import Objects.Borrower;
import Objects.Borrowing;
import Transactions.Transactions;

public class LibrarianDisplayCheckedOutBooks extends JFrame implements ActionListener{
	
	String [] columnNames = {"Call Number", "Out Date", "Due Date", "Overdue"}; 
	
	public LibrarianDisplayCheckedOutBooks(String name)
	{
		super (name);
	}
	
	private void addComponentsToPane(final Container pane, List<Borrowing> borrowing)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		
		JTable table = new JTable(this.getData(borrowing));
		panel.add(new JScrollPane(table));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 2));
		
		
		//changed OK from constants
		JButton backButton = new JButton ("Ok");
		backButton.setVerticalAlignment(AbstractButton.CENTER);
		backButton.setHorizontalAlignment(AbstractButton.CENTER);
		//changed OK from constants
		backButton.setActionCommand("Ok");
		backButton.addActionListener(this);

		panel2.add(new Label(""));
		panel2.add(backButton);
		
		pane.add(panel, BorderLayout.NORTH);
		pane.add(panel2, BorderLayout.SOUTH);
		
	}
	public static void createAndShowGUI(List<Borrowing> borrowing)
	{ 
		  //Create and set up the window.
        LibrarianDisplayCheckedOutBooks frame = new LibrarianDisplayCheckedOutBooks("Checked Out Books");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
              
        frame.addComponentsToPane(frame.getContentPane(), borrowing);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			this.dispose();
	}
	
	private TableModel getData(final List<Borrowing> borrowing)
	{
		final int numRows = borrowing.size();

		@SuppressWarnings("serial")
		TableModel tm = new AbstractTableModel(){

			@Override
			public String getColumnName(int col) {
		        return columnNames[col];
		    }
			@Override
			public int getColumnCount() {
				return columnNames.length;
			}

			@Override
			public int getRowCount() {
				return numRows;
			}

			@Override
			public Object getValueAt(int y, int x) {
				Transactions t = new Transactions();
				Borrower b = t.showBorrowerById(borrowing.get(y).bid);
				Date outDate = Constants.stringToDate(borrowing.get(y).outDate);
				Date expDate;
				if (outDate != null)
				{
					expDate = Constants.getReturnDateGivenOutDate(b.getType(), outDate);
				}else{
					expDate = new Date();
				}
				
				switch(x){
					case 0:
						return borrowing.get(y).callNumber;
					case 1:{
						return borrowing.get(y).outDate;
					}
					case 2:{
						return Constants.DateToString(expDate);
					}
					case 3:
						if (expDate.before(new Date()))
						{
							return "YES";
						}
						return "";
				
					default:
						return "";
				}
				
			}
			
		};
		return tm;
	}
}
