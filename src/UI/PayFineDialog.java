package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
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
import Objects.Fine;
import Transactions.Transactions;

public class PayFineDialog extends JFrame implements ActionListener{

	JTable table;
	static int bid;
	static List<Fine> fines;
	String [] columnNames = {"Fid", "Amount", "IssuedDate", "BorID"};
	static String payFine = "Pay Selected Fine";
	static String returnToBorrowerDialog = "Return to Borrower Dialog";
	
	public static final int VALIDATIONERROR = 2;
	
	public PayFineDialog(String name) 
	{
		super (name);
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		
		table = new JTable(this.getTableModel());
		panel.add(new JScrollPane(table));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 2));
		
		
		JButton backButton = new JButton (returnToBorrowerDialog);
		backButton.setVerticalAlignment(AbstractButton.CENTER);
		backButton.setHorizontalAlignment(AbstractButton.CENTER);
		backButton.setActionCommand(returnToBorrowerDialog);
		backButton.addActionListener(this);
		
		JButton payFineBtn = new JButton (payFine);
		payFineBtn.setVerticalAlignment(AbstractButton.CENTER);
		payFineBtn.setHorizontalAlignment(AbstractButton.CENTER);
		payFineBtn.setActionCommand(payFine);
		payFineBtn.addActionListener(this);

		panel2.add(backButton);
		panel2.add(payFineBtn);
		
		pane.add(panel, BorderLayout.NORTH);
		pane.add(panel2, BorderLayout.SOUTH);
	}
    public static void createAndShowGUI(int borrowerID) {
        //Create and set up the window.
        PayFineDialog frame = new PayFineDialog("Search Dialog");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Transactions t = new Transactions();
        fines = new ArrayList<Fine>();
        List<Borrowing> borrowing = t.showBorrowingById(borrowerID);
        for (Borrowing b : borrowing)
        {
        	Fine f = t.showFineById(b.borid);
        	if (f.borid == 0)
        	{
        		
        	}else if (f.paidDate == null || f.paidDate.length() < 9)
    		{
    			fines.add(f);
    		}
        }
        bid = borrowerID;
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (returnToBorrowerDialog.equals(arg0.getActionCommand()))
		{
			this.dispose();
			
		}
		else if (payFine.equals(arg0.getActionCommand()))
		{ 
			int selectedRow = table.getSelectedRow();
			Fine toPay = fines.get(selectedRow);
			Transactions t = new Transactions();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			if (t.payFine(toPay.fid, toPay.amount, dateFormat.format(new Date())))
			{
				GiveMeTitleAndMessageDialog.createAndShowGUI("Success", "Fine paid successfully");
				fines.remove(selectedRow);
				this.dispose();
				PayFineDialog.createAndShowGUI(bid);
			}else
			{
				GiveMeTitleAndMessageDialog.createAndShowGUI("Error", "Fine could not be paid, please try again");
			}
			
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
				return fines.size();
			}

			@Override
			public Object getValueAt(int y, int x) {
				switch(x){
				
				case 0:
					return Integer.toString(fines.get(y).fid);
				case 1:
					return Integer.toString(fines.get(y).amount);
				case 2:
					return fines.get(y).issuedDate;
				default:
					return fines.get(y).borid;
				}
				
			}
			
		};
		return tm;
	}
	
}
