package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import Objects.Book;
import Objects.BookCopy;

public class DisplaySearchedBooksDialog extends JFrame implements ActionListener{
	
	String [] columnNames = {"Call Number", "Title", "ISBN", "Author", "Publisher", "Year", "Subject", "Status"}; 
	public DisplaySearchedBooksDialog(String name)
	{
		super (name);
	}
	
	private void addComponentsToPane(final Container pane, List<BookCopy> books)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		
		JTable table = new JTable(this.bookData(books));
		panel.add(new JScrollPane(table));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 2));
		
		
		
		JButton backButton = new JButton (Constants.OK);
		backButton.setVerticalAlignment(AbstractButton.CENTER);
		backButton.setHorizontalAlignment(AbstractButton.CENTER);
		backButton.setActionCommand(Constants.OK);
		backButton.addActionListener(this);

		panel2.add(new Label(""));
		panel2.add(backButton);
		
		pane.add(panel, BorderLayout.NORTH);
		pane.add(panel2, BorderLayout.SOUTH);
		
	}
	public static void createAndShowGUI(List<BookCopy> books)
	{ 
		  //Create and set up the window.
        DisplaySearchedBooksDialog frame = new DisplaySearchedBooksDialog("Search Results");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        //TODO remove this;
        books.add(new BookCopy("Something","Something", "Something", "Something", "Something", "Something", "Something", "status"));
        books.add(new BookCopy("Something1","Something2", "Something3", "Something4", "Something5", "Something6", "Something7", "status"));
       
        frame.addComponentsToPane(frame.getContentPane(), books);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			this.dispose();
	}
	
	private TableModel bookData(final List<BookCopy> books)
	{
		final int numRows = books.size();

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
						return books.get(y).callNumber;
					case 1:
						return books.get(y).title;
					case 2:
						return books.get(y).isbn;
					case 3:
						return books.get(y).mainAuthor;
					case 4:
						return books.get(y).publisher;
					case 5:
						return books.get(y).year;
					case 6:
						return books.get(y).subject;
					case 7:
						return books.get(y).status;
					default:
						return "";
				}
				
			}
			
		};
		return tm;
	}
} 