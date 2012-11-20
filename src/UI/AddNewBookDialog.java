package UI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewBookDialog extends JFrame implements ActionListener {

	JTextField callNumber = new JTextField();
	JTextField isbn = new JTextField();
	JTextField title = new JTextField();
	JTextField mainAuthor = new JTextField();
	JTextField year = new JTextField();
	
	static String returnToLibrarianDialogCommand = "Return to Librarian Dialog";
	static String add = "Add";
	
	public AddNewBookDialog(String name)
	{
		super (name);

		
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2));
		
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
		}
		
	}
}
