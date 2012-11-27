package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import Objects.Borrower;
import Objects.Borrowing;
import Transactions.Transactions;

public class CheckOverduesDialog extends JFrame implements ActionListener{

	String [] columnNames = {"Item Call Number", "Borrower"}; 
	JTable table;
	static String EMAILBORROWER = "Email borrower";
	static String EMAIL_ALL_BORROWERS = "Email all borrowers";
	static String returnToClerkDialogString = "Return to Clerk Dialog";
	
	static List<Borrowing> overDues = new ArrayList<Borrowing> ();
	
	public CheckOverduesDialog(String name)
	{
		super(name);
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		
		table = new JTable(this.getTableModel());
		panel.add(new JScrollPane(table));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 2));
		
		
		JButton emailBorrower = new JButton (EMAILBORROWER);
		emailBorrower.setVerticalAlignment(AbstractButton.CENTER);
		emailBorrower.setHorizontalAlignment(AbstractButton.CENTER);
		emailBorrower.setActionCommand(EMAILBORROWER);
		emailBorrower.addActionListener(this);
		
		JButton emailAllBorrowers = new JButton (EMAIL_ALL_BORROWERS);
		emailAllBorrowers.setVerticalAlignment(AbstractButton.CENTER);
		emailAllBorrowers.setHorizontalAlignment(AbstractButton.CENTER);
		emailAllBorrowers.setActionCommand(EMAIL_ALL_BORROWERS);
		emailAllBorrowers.addActionListener(this);
		panel2.add(emailBorrower);
		panel2.add(emailAllBorrowers);
		
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
    public static void createAndShowGUI() {
        //Create and set up the window.
        CheckOverduesDialog frame = new CheckOverduesDialog("Check Overdue Items");
        
        generateListOfOverdues();
        
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private static void generateListOfOverdues()
    {
    	overDues.clear();
    	Transactions t = new Transactions();
    	List<Borrowing> checkedOut = t.showCheckedOutBorrowing();
    	for (Borrowing b : checkedOut)
    	{
    		Borrower borr = t.showBorrowerById(b.bid);
    		String type = borr.getType();
    		Date outDate = Constants.stringToDate(b.getOutDate());
    		if (outDate != null)
    		{
    			if (Constants.getReturnDateGivenOutDate(type, outDate).before(new Date()))
    			{
    				overDues.add(b);
    			}
    		}
    	}
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Ok"))
		{
			this.dispose();
		}else if(arg0.getActionCommand().equals(EMAILBORROWER))
		{
			if (overDues.size() > 0)
			{
				emailSelectedBorrower();
			}
			
		}else if (arg0.getActionCommand().equals(EMAIL_ALL_BORROWERS))
		{
			if (overDues.size() > 0)
			{
				emailAllBorrowers();
			}
			
		}
		
	}
	
	private void emailAllBorrowers()
	{
		GiveMeTitleAndMessageDialog.createAndShowGUI("E-mail sent", "E-mail sent to all borrowers alerting them of their overdue books.");
	}
	
	private void emailSelectedBorrower()
	{
		int[] rows = table.getSelectedRows();
		if (rows.length > 0)
		{
			String bids = "";
			for (int i = 0; i < rows.length; i++)
			{
				bids = bids + " " + overDues.get(rows[i]).getBid();
			}
			GiveMeTitleAndMessageDialog.createAndShowGUI("E-mail sent", "E-mail sent to the following borrowers alerting them of their overdue books. \n" + bids);
		}else
		{
			GiveMeTitleAndMessageDialog.createAndShowGUI("No E-mail sent", "There are no borrowers selected");
		}
		
	}
	private TableModel getTableModel()
	{

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
				return overDues.size();
			}

			@Override
			public Object getValueAt(int y, int x) {
				if (x == 0)
				{
					return overDues.get(y).callNumber;
				}else
				{
					return Integer.toString(overDues.get(y).bid);
				}
				
			}
			
		};
		return tm;
	}
}
