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

public class CheckOverduesDialog extends JFrame implements ActionListener{

	JTextField borrowerID = new JTextField();
	JTextField bookCallNumber = new JTextField();
	DefaultListModel listModel = new DefaultListModel();
	
	static String EMAILBORROWER = "Email borrower";
	static String returnToClerkDialogString = "Return to Clerk Dialog";
	
	public CheckOverduesDialog(String name)
	{
		super(name);
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 3));
		
		panel.add(new Label("Check Overdues"));
		panel.add(new Label(""));
		panel.add(new Label(""));
		
		panel.add(new Label("Item"));
		panel.add(new Label("Borrower"));
		panel.add(new Label(""));
		
		panel.add(bookCallNumber);
		panel.add(borrowerID);
		
		JList list = new JList(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		panel.add(scrollPane);
		panel.add(new Label(""));
		
		JButton returnToUserDialog = new JButton(returnToClerkDialogString);
		returnToUserDialog.setActionCommand(returnToClerkDialogString);
		returnToUserDialog.addActionListener(this);
		
		JButton emailBorrowerButton = new JButton(EMAILBORROWER);
		emailBorrowerButton.setActionCommand(EMAILBORROWER);
		emailBorrowerButton.addActionListener(this);
		
		
		panel.add(returnToUserDialog);
		panel.add(emailBorrowerButton);
		
		pane.add(panel);
	}
    public static void createAndShowGUI() {
        //Create and set up the window.
        CheckOverduesDialog frame = new CheckOverduesDialog("Check Overdue Items");
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
		}else if(arg0.getActionCommand().equals(EMAILBORROWER))
		{
			//listModel.addElement(bookCallNumber.getText());
			//bookCallNumber.setText("");
		}
		
	}
}
