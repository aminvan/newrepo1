package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import Objects.Borrowing;
import Transactions.Transactions;

public class LibrarianDialog extends JFrame implements ActionListener {
	static JFrame mainFrame;
	static String addBookCommand = "Add a Book";
	static String checkOutBooksCommand = "Generate Report of Checked Out Books";
	static String popularItemsCommand = "Generate Report of Popular Items";
	static String addCopyCommand = "Add Copy of Pre-existing Book";
	JTextField nItems = new JTextField();
	JTextField year = new JTextField();
	
	public static final int VALIDATIONERROR = 2;
	
	public LibrarianDialog(String name)
	{
		super (name);
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		
		JButton addBookCommandBtn = new JButton(addBookCommand);
		addBookCommandBtn.setVerticalTextPosition(AbstractButton.CENTER);
		addBookCommandBtn.setHorizontalTextPosition(AbstractButton.CENTER);
		addBookCommandBtn.setActionCommand(addBookCommand);
		addBookCommandBtn.addActionListener(this);
		
		JButton addCopyCommandBtn = new JButton(addCopyCommand);
		addCopyCommandBtn.setVerticalTextPosition(AbstractButton.CENTER);
		addCopyCommandBtn.setHorizontalTextPosition(AbstractButton.CENTER);
		addCopyCommandBtn.setActionCommand(addCopyCommand);
		addCopyCommandBtn.addActionListener(this);
		
		JButton checkedOutItems = new JButton(checkOutBooksCommand);
		checkedOutItems.setVerticalTextPosition(AbstractButton.CENTER);
		checkedOutItems.setHorizontalTextPosition(AbstractButton.CENTER);
		checkedOutItems.setActionCommand(checkOutBooksCommand);
		checkedOutItems.addActionListener(this);
		
		JButton popularItemsButton = new JButton (popularItemsCommand);
		popularItemsButton.setVerticalAlignment(AbstractButton.CENTER);
		popularItemsButton.setHorizontalAlignment(AbstractButton.CENTER);
		popularItemsButton.setActionCommand(popularItemsCommand);
		popularItemsButton.addActionListener(this);
		
		panel.add(addBookCommandBtn);
		panel.add(addCopyCommandBtn);
		panel.add(checkedOutItems);
		
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 4));
		//panel.add(new Label("Generate report of popular items by filling out the text fields and pressing Generate Report"));
		//panel.add(new Label(""));
		//panel.add(new Label(""));
		//panel.add(new Label(""));
		
		panel2.add(new Label("n items "));
		panel2.add(nItems);
		panel2.add(new Label("in year"));
		panel2.add(year);
		
		JButton backButton = new JButton (Constants.RETURN_TO_CHOOSE_USER_DIALOG);
		backButton.setVerticalAlignment(AbstractButton.CENTER);
		backButton.setHorizontalAlignment(AbstractButton.CENTER);
		backButton.setActionCommand(Constants.RETURN_TO_CHOOSE_USER_DIALOG);
		backButton.addActionListener(this);
		
		panel2.add(new Label(""));
		panel2.add(new Label(""));
		panel2.add(popularItemsButton);
		panel2.add(backButton);
		
		pane.add(panel, BorderLayout.NORTH);
		pane.add(new Label("Generate report of popular items by filling out the text fields and pressing Generate Report"),
				BorderLayout.CENTER);
		pane.add(panel2, BorderLayout.SOUTH);
		
	}
	public static void createAndShowGUI()
	{
		  //Create and set up the window.
        LibrarianDialog frame = new LibrarianDialog("Librarian Dialog");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (LibrarianDialog.addBookCommand.equals(arg0.getActionCommand()))
		{
			AddNewBookDialog.createAndShowGUI();
		} else if (LibrarianDialog.addCopyCommand.equals(arg0.getActionCommand()))
		{
			AddCopyDialog.createAndShowGUI();
		} else if (LibrarianDialog.popularItemsCommand.equals(arg0.getActionCommand()))
		{
			PopularItemsDialog.createAndShowGUI(genPopItems());			
		} else if (LibrarianDialog.checkOutBooksCommand.equals(arg0.getActionCommand()))
		{
			GenBooksCheckedOutDialog.createAndShowGUI();
		}else if (Constants.RETURN_TO_CHOOSE_USER_DIALOG.equals(arg0.getActionCommand()))
		{
			this.dispose();
			ChooseUserDialog.createAndShowGUI();
		}
	}
	
	public List<List<Integer>> genPopItems() {
		// something year and number
		int yr, numItems;
		
		if (nItems.getText().trim().length() != 0) {
			numItems = Integer.parseInt(nItems.getText());
		}
		else {
			return null;
		}
		if (year.getText().trim().length() != 0) {
			yr = Integer.parseInt(year.getText());
		}
		else {
			return null;
		}
		yr = yr - 1900;
		
		Transactions trans = new Transactions();
		List<Borrowing> borrowing = trans.showAllBorrowing();
		List<Borrowing> tempBorrowing = borrowing;
		for (int i = 0; i < tempBorrowing.size(); i++){
			if (Constants.stringToDate(tempBorrowing.get(i).outDate) == null)
			{
				borrowing.remove(i);
			}else
			{
				int year = Constants.stringToDate(tempBorrowing.get(i).outDate).getYear();
				if (yr == year)
				{
				
				}else
				{
					borrowing.remove(i);
				}
			}
		}
		HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
		for (Borrowing b : borrowing)
		{
			if (counts.containsKey(b.callNumber))
			{
				counts.put(b.callNumber, counts.get(b.callNumber) + 1);
			}else
			{
				counts.put(b.callNumber, 1);
			}
		}
		Collection<Integer> values = counts.values();
		List<Integer> temp = new ArrayList(values);
		Collections.sort(temp);
		
		List<List<Integer>> returnList= new ArrayList<List<Integer>>(); 
		List<Integer> callNumbers = new ArrayList<Integer>();
		
		List<Integer> quantity = new ArrayList<Integer>();
	
		if (numItems > temp.size())
		{
			numItems = temp.size();
		}
		for (int i = 1; i <= numItems; i++)
		{
			Integer q = temp.get(temp.size() - i);
			Set<Integer> keys = getKeysByValue(counts, q);
			callNumbers.add((Integer) keys.toArray()[0]);
			quantity.add(q);
			counts.remove((Integer) keys.toArray()[0]);
		}
		
				//		trans.popItems(yr, numItems);
		returnList.add(callNumbers);
		returnList.add(quantity);
		return returnList;
	}

	public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
	     Set<T> keys = new HashSet<T>();
	     for (Entry<T, E> entry : map.entrySet()) {
	         if (value.equals(entry.getValue())) {
	             keys.add(entry.getKey());
	         }
	     }
	     return keys;
	}
}
