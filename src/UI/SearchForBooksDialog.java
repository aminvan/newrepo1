package UI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Objects.Book;
import Transactions.Transactions;

public class SearchForBooksDialog extends JFrame implements ActionListener{

	JTextField title = new JTextField();
	JTextField author = new JTextField();
	JTextField subject = new JTextField();
	
	static String returnToUserDialogString = "Return to User Dialog";
	static String search = "Search";
	
	public static final int VALIDATIONERROR = 2;
	
	public SearchForBooksDialog(String name)
	{
		super (name);

		
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		
		panel.add(new Label("Search"));
		panel.add(new Label(""));
		
		panel.add(new Label("titles"));
		panel.add(title);
		
		panel.add(new Label("authors"));
		panel.add(author);
		
		panel.add(new Label("subjects"));
		panel.add(subject);
		
		JButton returnToUserDialog = new JButton(returnToUserDialogString);
		returnToUserDialog.setActionCommand(returnToUserDialogString);
		returnToUserDialog.addActionListener(this);
		
		JButton searchButton = new JButton(search);
		searchButton.setActionCommand(search);
		searchButton.addActionListener(this);
		
		panel.add(returnToUserDialog);
		panel.add(searchButton);
		
		pane.add(panel);
	}
    public static void createAndShowGUI() {
        //Create and set up the window.
        SearchForBooksDialog frame = new SearchForBooksDialog("Search Dialog");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (returnToUserDialogString.equals(arg0.getActionCommand()))
		{
			this.dispose();
			
		}
		else if (search.equals(arg0.getActionCommand()))
		{ 
			//TODO remove comments and remove this line
			DisplaySearchedBooksDialog.createAndShowGUI(new ArrayList<Book>());
			/*List<Book> books = searchBooks();
			if (books != null) {
				title.setText("");
				author.setText("");
				subject.setText("");
				DisplaySearchedBooksDialog.createAndShowGUI(books);
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
			}*/
			
		}
		
	}
	
	
	private List<Book> searchBooks() {
		
		String btitle = title.getText().trim();
		String bauthor = author.getText().trim();
		String bsubject = subject.getText().trim();
		
		if (btitle.length() != 0 | bauthor.length() != 0 | bsubject.length() != 0) {

			Transactions trans = new Transactions();
			ArrayList<Book> bookList = trans.showBookSearch(btitle, bauthor, bsubject);
			return bookList;
			// displayBooks(bookList);
			
		}
		
		return null;
	}
}
