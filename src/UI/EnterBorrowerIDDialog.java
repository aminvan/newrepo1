package UI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class EnterBorrowerIDDialog extends JFrame implements ActionListener {

	static String DONE = "Done";
	JTextField idField = new JTextField();
	JTextField passwordField = new JTextField();
	public EnterBorrowerIDDialog(String name)
	{
		super(name);
	}
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		
		panel.add(new Label("Enter Borrower ID"));
		panel.add(idField);
		
		panel.add(new Label("Enter Password"));
		panel.add(passwordField);
			
		panel.add(new Label(""));

		
		JButton doneButton = new JButton(DONE);
		doneButton.setActionCommand(DONE);
		doneButton.addActionListener(this);
		
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
		// TODO check that id exists
		int bid = Integer.parseInt(idField.getText().trim());
		BorrowerDialog.createAndShowGUI(bid);
		this.dispose();
		
	}
}
