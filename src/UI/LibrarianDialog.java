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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class LibrarianDialog extends JFrame implements ActionListener {
	static JFrame mainFrame;
	static String addBookCommand = "Add a Book";
	static String checkOutBooksCommand = "Generate Report of Checked Out Books";
	static String popularItemsCommand = "Generate Report";
	static String addCopyCommand = "Add Copy of Pre-existing Book";
	JTextField nItems = new JTextField();
	JTextField year = new JTextField();
	
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
		
		panel2.add(new Label(""));
		panel2.add(new Label(""));
		panel2.add(popularItemsButton);
		
		
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
		}else if (LibrarianDialog.addCopyCommand.equals(arg0.getActionCommand()))
		{
			AddCopyDialog.createAndShowGUI();
		}
		
	}
}
