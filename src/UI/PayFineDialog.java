package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import Transactions.Transactions;

public class PayFineDialog extends JFrame implements ActionListener{

	JTable table;
	
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
        //TODO
        //t.getFines(borrowerID);
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
				return 1;
			}

			@Override
			public Object getValueAt(int y, int x) {
				return "";
				
			}
			
		};
		return tm;
	}
	
}
