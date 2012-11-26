package UI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Transactions.Transactions;

public class AddNewBookDialog extends JFrame implements ActionListener {

	JTextField callNumber = new JTextField();
	JTextField isbn = new JTextField();
	JTextField title = new JTextField();
	JTextField mainAuthor = new JTextField();
	JTextField publisher = new JTextField();
	JTextField year = new JTextField();
	
	static String returnToLibrarianDialogCommand = "Return to Librarian Dialog";
	static String add = "Add";
	
	private Transactions con;
	
	public static final int VALIDATIONERROR = 2;
	
	public AddNewBookDialog(String name)
	{
		super (name);
				
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2));
		
		panel.add(new Label("Add a new book"));
		panel.add(new Label(""));
		
		panel.add(new Label("Call Number"));
		panel.add(callNumber);
		
		panel.add(new Label("ISBN"));
		panel.add(isbn);
		
		panel.add(new Label("Title"));
		panel.add(title);
		
		panel.add(new Label("Main Author"));
		panel.add(mainAuthor);
		
		panel.add(new Label("Publisher"));
		panel.add(publisher);
		
		panel.add(new Label("Year"));
		panel.add(year);
		
		JButton returnToUserDialog = new JButton(returnToLibrarianDialogCommand);
		returnToUserDialog.setActionCommand(returnToLibrarianDialogCommand);
		returnToUserDialog.addActionListener(this);
		
		JButton addButton = new JButton(add);
		addButton.setActionCommand(add);
		addButton.addActionListener(this);
		
		panel.add(returnToUserDialog);
		panel.add(addButton);
		
		pane.add(panel);
	}
    public static void createAndShowGUI() {
        //Create and set up the window.
        AddNewBookDialog frame = new AddNewBookDialog("Add New Book Dialog");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (returnToLibrarianDialogCommand.equals(arg0.getActionCommand()))
		{
			this.dispose();
		}else if(arg0.getActionCommand().equals("Add"))
		{
			addBook();
		}
		
	}
	
	public int addBook() {
		
		String callNo;
		int iIsbn;
		String sTitle;
		String mAuthor;
		String sPublisher;
		int yr;
		
		if (callNumber.getText().trim().length() != 0) {
			callNo = callNumber.getText();
		}
		else {
			showErrorDialog();
			return VALIDATIONERROR;
		}
		
		if (isbn.getText().trim().length() != 0) {
			iIsbn = Integer.parseInt(isbn.getText());
		}
		else {
			showErrorDialog();
			return VALIDATIONERROR;
		}
		
		if (title.getText().trim().length() != 0) {
			sTitle = title.getText();
		}
		else {
			showErrorDialog();
			return VALIDATIONERROR;
		}
		
		if (mainAuthor.getText().trim().length() != 0) {
			mAuthor = mainAuthor.getText();
		}
		else {
			showErrorDialog();
			return VALIDATIONERROR;
		}
		
		if (publisher.getText().trim().length() != 0) {
			sPublisher = publisher.getText();
		}
		else {
			showErrorDialog();
			return VALIDATIONERROR;
		}
		
		if (year.getText().trim().length() != 0) {
			yr = Integer.parseInt(year.getText());
		}
		else {
			showErrorDialog();
			return VALIDATIONERROR;
		}
		
		Transactions trans = new Transactions();
		
		if (trans.insertBook(callNo, iIsbn, sTitle, mAuthor, sPublisher,yr))
		{
			GiveMeTitleAndMessageDialog.createAndShowGUI(Constants.SUCCESS, "Book added successfully");
			trans.insertBookCopy(callNo + " C1", 1, "in");
			clearTextFields();
		}else
		{
			showErrorDialog();
		}
		
		return 0;
		
	}
	
	private void showErrorDialog()
	{
		GiveMeTitleAndMessageDialog.createAndShowGUI(Constants.ERROR, Constants.AN_ERROR_OCCURRED);
	}
	private void clearTextFields(){
		callNumber.setText("");
		isbn.setText("");
		title.setText("");
		mainAuthor.setText("");
		publisher.setText("");
		year.setText("");
	}
}
