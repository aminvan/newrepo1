package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GiveMeTitleAndMessageDialog extends JFrame implements ActionListener{
	
	
	public GiveMeTitleAndMessageDialog(String name)
	{
		super (name);
	}
	
	private void addComponentsToPane(final Container pane, String message)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		
		panel.add(new Label(message));
		
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 2));
		
		
		
		JButton backButton = new JButton (Constants.OK);
		backButton.setVerticalAlignment(AbstractButton.CENTER);
		backButton.setHorizontalAlignment(AbstractButton.CENTER);
		backButton.setActionCommand(Constants.OK);
		backButton.addActionListener(this);

		panel2.add(new Label(""));
		panel2.add(backButton);
		
		pane.add(panel, BorderLayout.NORTH);
		pane.add(panel2, BorderLayout.SOUTH);
		
	}
	public static void createAndShowGUI(String title, String message)
	{
		  //Create and set up the window.
        GiveMeTitleAndMessageDialog frame = new GiveMeTitleAndMessageDialog(title);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane(), message);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			this.dispose();
	}
	
}
