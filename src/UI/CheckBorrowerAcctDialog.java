package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import Objects.Borrowing;
import Transactions.Transactions;

public class CheckBorrowerAcctDialog extends JFrame implements ActionListener{

	JTextField bid = new JTextField();
	JTextField pwd = new JTextField();
	JTable borrowedItems, fines, holdRequests;
	
	static List<Borrowing> borrowing;
	//List<HoldRequest>
	//List<Fine>
	static String done = "Done";
	String returnToBorrowerDialog = "Return to Borrower Dialog";
	String[] borrowColumns = {"Call Number", "Copy Number", "OutDate", "InDate"};
	String[] holdColumns = {"Call Number", "Issued Date"};
	String[] fineColumns = {"Amount", "Issued Date"};
	
	public static final int VALIDATIONERROR = 2;
	
	public CheckBorrowerAcctDialog(String name)
	{
		super (name);
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1));
		
		panel1.add(new Label("Borrowed Items"));
		this.borrowedItems = new JTable(this.getBorrowedItemsTableModel());
		panel1.add(new JScrollPane(borrowedItems));
		panel1.setPreferredSize(new Dimension(100, 200));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 1));
		
		panel2.add(new Label("Hold Requests"));
		this.holdRequests = new JTable(this.getHoldRequestsTableModel());
		panel2.add(new JScrollPane(holdRequests));
		panel2.setPreferredSize(new Dimension(100, 200));
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(3, 1));
		
		panel3.add(new Label("Fines"));
		this.fines = new JTable(this.getFinesTableModel());
		panel3.add(new JScrollPane(fines));
		panel3.setPreferredSize(new Dimension(100, 250));
		
		JButton backButton = new JButton(returnToBorrowerDialog);
		backButton.setActionCommand(returnToBorrowerDialog);
		backButton.addActionListener(this);
		panel3.add(backButton);
//		JButton searchButton = new JButton(search);
//		searchButton.setActionCommand(search);
//		searchButton.addActionListener(this);
		
//		panel.add(returnToUserDialog);
//		panel.add(searchButton);
//		
//		pane.add(panel);
		pane.add(panel1, BorderLayout.NORTH);
		pane.add(panel2, BorderLayout.CENTER);
		pane.add(panel3, BorderLayout.SOUTH);
	}
    public static void createAndShowGUI(int borrowerID) {
        //Create and set up the window.
        CheckBorrowerAcctDialog frame = new CheckBorrowerAcctDialog("Account Overview");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        Transactions t = new Transactions();
        borrowing = t.showBorrowingById(borrowerID);
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private TableModel getBorrowedItemsTableModel()
    {
		@SuppressWarnings("serial")
		TableModel tm = new AbstractTableModel(){

			@Override
			public String getColumnName(int col) {
		        return borrowColumns[col];
		    }
			@Override
			public int getColumnCount() {
				return borrowColumns.length;
			}

			@Override
			public int getRowCount() {
				return borrowing.size();
			}

			@Override
			public Object getValueAt(int y, int x) {
				switch(x)
				{
					case 0:
						return Integer.toString(borrowing.get(y).getCallNumber());
					case 1:
						return Integer.toString(borrowing.get(y).getCopyNo());
					case 2:
						return borrowing.get(y).getOutDate();
					default:
						return borrowing.get(y).getInDate();
				}
				
			}
			
		};
		return tm;
    }
    
    private TableModel getFinesTableModel()
    {
		@SuppressWarnings("serial")
		TableModel tm = new AbstractTableModel(){

			@Override
			public String getColumnName(int col) {
		        return fineColumns[col];
		    }
			@Override
			public int getColumnCount() {
				return fineColumns.length;
			}

			@Override
			public int getRowCount() {
				return 1;
			}

			@Override
			public String getValueAt(int y, int x) {
				return "";
				
			}
			
		};
		return tm;
    }
    
    private TableModel getHoldRequestsTableModel()
    {
		@SuppressWarnings("serial")
		TableModel tm = new AbstractTableModel(){

			@Override
			public String getColumnName(int col) {
		        return holdColumns[col];
		    }
			@Override
			public int getColumnCount() {
				return holdColumns.length;
			}

			@Override
			public int getRowCount() {
				return 1;
			}

			@Override
			public Object getValueAt(int y, int x) {
				return "";
				
			}
			
		};
		return tm;
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
			this.dispose();
	}

}
