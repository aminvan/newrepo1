package UI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Objects.Borrower;
import Transactions.Transactions;

public class EnterBorrowerIDDialog extends JFrame implements ActionListener {

	static String DONE = "Done";
	JTextField idField = new JTextField();
	public EnterBorrowerIDDialog(String name)
	{
		super(name);
	}
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		
		panel.add(new Label("Enter Borrower ID"));
		panel.add(idField);
			
		

		
		JButton doneButton = new JButton(DONE);
		doneButton.setActionCommand(DONE);
		doneButton.addActionListener(this);
		
		JButton returnToChooseUserDialogBtn = new JButton (Constants.RETURN_TO_CHOOSE_USER_DIALOG);
		returnToChooseUserDialogBtn.setVerticalAlignment(AbstractButton.CENTER);
		returnToChooseUserDialogBtn.setHorizontalAlignment(AbstractButton.CENTER);
		returnToChooseUserDialogBtn.setActionCommand(Constants.RETURN_TO_CHOOSE_USER_DIALOG);
		returnToChooseUserDialogBtn.addActionListener(this);
		panel.add(returnToChooseUserDialogBtn);
		panel.add(doneButton);
		
		pane.add(panel);
	}
    public static void createAndShowGUI() {
        //Create and set up the window.
        EnterBorrowerIDDialog frame = new EnterBorrowerIDDialog("Borrower");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Constants.RETURN_TO_CHOOSE_USER_DIALOG.equals(arg0.getActionCommand()))
		{
			ChooseUserDialog.createAndShowGUI();
			this.dispose();
		}else
		{
			int bid;
			try{
				 bid = Integer.parseInt(idField.getText().trim());
				 Transactions t = new Transactions();
				 Borrower b = t.showBorrowerById(bid);
				 if (b.getName() != null)
				 {
					 BorrowerDialog.createAndShowGUI(bid);
					 this.dispose();
				 }else
				 {
					 GiveMeTitleAndMessageDialog.createAndShowGUI("Error", "A Borrower with that ID was not found");
				 }
				 
			}catch(Exception e)
			{
				GiveMeTitleAndMessageDialog.createAndShowGUI("Error", "Please enter an integer in Borrower ID");
				
			}
		}
		
		
		
	}
}
