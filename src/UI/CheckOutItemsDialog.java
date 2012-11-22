package UI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CheckOutItemsDialog extends JFrame implements ActionListener{

	JTextField borrowerID = new JTextField();
	JTextField bookCallNumber = new JTextField();
	DefaultListModel listModel = new DefaultListModel();
	
	static String CHECKOUT = "Check Out Books";
	static String returnToClerkDialogString = "Return to Clerk Dialog";
	
    private Connection con;
	
	public CheckOutItemsDialog(String name)
	{
		super(name);
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 3));
		
		panel.add(new Label("Check Out Books"));
		panel.add(new Label(""));
		panel.add(new Label(""));
		
		panel.add(new Label("Borrower ID"));
		panel.add(borrowerID);
		panel.add(new Label(""));
		
		panel.add(new Label("Enter one call number and press add"));
		panel.add(bookCallNumber);
		JButton addCallNumberButton = new JButton("Add");
		addCallNumberButton.setActionCommand("Add");
		addCallNumberButton.addActionListener(this);
		panel.add(addCallNumberButton);
		
		panel.add(new Label(""));
		
		JList list = new JList(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		panel.add(scrollPane);
		panel.add(new Label(""));
		
		
		panel.add(new Label(""));
		JButton returnToUserDialog = new JButton(returnToClerkDialogString);
		returnToUserDialog.setActionCommand(returnToClerkDialogString);
		returnToUserDialog.addActionListener(this);
		
		JButton checkOutButton = new JButton(CHECKOUT);
		checkOutButton.setActionCommand(CHECKOUT);
		checkOutButton.addActionListener(this);
		
		panel.add(returnToUserDialog);
		panel.add(checkOutButton);
		
		pane.add(panel);
	}
    public static void createAndShowGUI() {
        //Create and set up the window.
        CheckOutItemsDialog frame = new CheckOutItemsDialog("Check Out Items");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (returnToClerkDialogString.equals(arg0.getActionCommand()))
		{
			this.dispose();
		}else if(arg0.getActionCommand().equals("Add"))
		{
			listModel.addElement(bookCallNumber.getText());
			bookCallNumber.setText("");
		}else if(arg0.getActionCommand().equals(CHECKOUT))
		{
			checkout();
		}

	}
	
	public void checkout() {
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("INSERT INTO Borrowing VALUES (?,?,?,?,?, ?)");
//			ps.setInt(1, Integer.parseInt(borid.getText()));
//			ps.setInt(2, Integer.parseInt(bid.getText()));
//			ps.setInt(3, Integer.parseInt(callNumber.getText()));
//			ps.setInt(4, Integer.parseInt(copyNo.getText()));
//			ps.setString(5, outDate.getText());
//			ps.setString(6, inDate.getText());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
