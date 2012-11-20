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

public class SearchForBooksDialog extends JFrame implements ActionListener{

	JTextField title = new JTextField();
	JTextField author = new JTextField();
	JTextField subject = new JTextField();
	
	static String returnToUserDialogString = "Return to User Dialog";
	static String search = "Search";
	
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
		// TODO Auto-generated method stub
		
	}
}
