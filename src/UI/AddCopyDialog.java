package UI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Transactions.Transactions;

public class AddCopyDialog extends JFrame implements ActionListener {


	JTextField callNumber = new JTextField();
	JTextField copyNo = new JTextField();
	JTextField status = new JTextField();

	static String returnToLibrarianDialogCommand = "Return to Librarian Dialog";
	static String add = "Add";
	
	public AddCopyDialog(String name)
	{
		super (name);

		
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		
		panel.add(new Label("Add a copy of a pre-existing book"));
		panel.add(new Label(""));
		
		panel.add(new Label("Call Number"));
		panel.add(callNumber);
		
		panel.add(new Label("Copy Number"));
		panel.add(copyNo);
		
		panel.add(new Label("Status"));
		panel.add(status);
	
		
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
        AddCopyDialog frame = new AddCopyDialog("Add Copy Dialog");
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
			
			
			// need to check that status is "on-hold", "in" or "out"
			
			addCopy();
		}
		
	}
	
	public void addCopy() {
		
		String callNo = callNumber.getText();
		int copynum = Integer.parseInt(copyNo.getText());
		String stat = status.getText();
		
		Transactions newBook = new Transactions();
		
		newBook.insertBookCopy(callNo, copynum, stat);
	}

}
