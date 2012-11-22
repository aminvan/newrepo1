package UI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ProcessReturnsDialog extends JFrame implements ActionListener{

	JTextField borrowerID = new JTextField();
	JTextField bookCallNumber = new JTextField();
	DefaultListModel listModel = new DefaultListModel();
	
	static String CHECKIN = "Check In Books";
	static String returnToClerkDialogString = "Return to Clerk Dialog";
	
	public ProcessReturnsDialog(String name)
	{
		super(name);
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 3));
		
		panel.add(new Label("Check In Books"));
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
		
		JButton checkInButton = new JButton(CHECKIN);
		checkInButton.setActionCommand(CHECKIN);
		checkInButton.addActionListener(this);
		
		panel.add(returnToUserDialog);
		panel.add(checkInButton);
		
		pane.add(panel);
	}
    public static void createAndShowGUI() {
        //Create and set up the window.
        ProcessReturnsDialog frame = new ProcessReturnsDialog("Check In Items");
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
		}
		
	}
}
