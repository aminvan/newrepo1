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

import Objects.Borrower;
import Objects.Borrowing;
import Transactions.Transactions;

public class PopularItemsDialog extends JFrame implements ActionListener {
	String [] columnNames = {"Call Number", "Number of times"}; 
	
	public PopularItemsDialog(String name)
	{
		super (name);
	}
	
	private void addComponentsToPane(final Container pane, List<List <Integer>> borrowing)
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
	public static void createAndShowGUI(List<List<Integer>> list)
	{ 
		  //Create and set up the window.
        PopularItemsDialog frame = new PopularItemsDialog("Popular Items");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
              
        frame.addComponentsToPane(frame.getContentPane(), list);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			this.dispose();
	}
	
	private TableModel getData(final List<List<Integer>> borrowing)
	{
		final int numRows = borrowing.get(0).size();

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
				
				switch(x){
					case 0:
						return borrowing.get(0).get(y);
					case 1:{
						return borrowing.get(1).get(y);
					}
				
					default:
						return "";
				}
				
			}
			
		};
		return tm;
	}
}
