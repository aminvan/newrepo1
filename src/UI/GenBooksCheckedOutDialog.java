package UI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Transactions.Transactions;

public class GenBooksCheckedOutDialog extends JFrame implements ActionListener{

	JTextField callNum = new JTextField();
	JTextField title = new JTextField();
	JTextField checkOutDate = new JTextField();
	JTextField dueDate = new JTextField();
	
	static String returnToUserDialogString = "Return to User Dialog";
	static String genReport = "Generate Report";
	
	public static final int VALIDATIONERROR = 2;
	
	public GenBooksCheckedOutDialog(String name)
	{
		super (name);		
	}
	
	private void addComponentsToPane(final Container pane)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		
		panel.add(new Label("Books Currently Checked Out"));
		panel.add(new Label(""));
		
//		panel.add(new Label("Call"));
//		panel.add(year);
//		
//		panel.add(new Label("Number"));
//		panel.add(number);
//			
		JButton returnToUserDialog = new JButton(returnToUserDialogString);
		returnToUserDialog.setActionCommand(returnToUserDialogString);
		returnToUserDialog.addActionListener(this);
		
//		JButton genReportButton = new JButton(genReport);
//		genReportButton.setActionCommand(genReport);
//		genReportButton.addActionListener(this);
//		
		panel.add(returnToUserDialog);
//		panel.add(genReportButton);
		
		pane.add(panel);
	}
    public static void createAndShowGUI() {
        //Create and set up the window.
        GenBooksCheckedOutDialog frame = new GenBooksCheckedOutDialog("Search Dialog");
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (returnToUserDialogString.equals(arg0.getActionCommand()))
		{
			this.dispose();
			
		}
		else if (genReport.equals(arg0.getActionCommand()))
		{ 
			genBooksOutReport();
			
		}
		
	}
	
	
	private int genBooksOutReport() {
		
		
		
		return VALIDATIONERROR;
	}
}
