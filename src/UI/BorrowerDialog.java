package UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BorrowerDialog extends JPanel implements ActionListener{

	static JFrame mainFrame;
	static String searchBooks = "Search for Books";
	static String checkAccount = "Check Account";
	static String placeHoldRequest = "Place Hold Request";
	static String payFine = "Pay Fine";
	static String bid;
	public BorrowerDialog()
	{
		JButton searchForBooks = new JButton(BorrowerDialog.searchBooks);
		searchForBooks.setVerticalTextPosition(AbstractButton.CENTER);
		searchForBooks.setHorizontalTextPosition(AbstractButton.CENTER);
		searchForBooks.setActionCommand(BorrowerDialog.searchBooks);
		searchForBooks.addActionListener(this);
		
		JButton checkAccount = new JButton(BorrowerDialog.checkAccount);
		checkAccount.setVerticalTextPosition(AbstractButton.CENTER);
		checkAccount.setHorizontalTextPosition(AbstractButton.CENTER);
		checkAccount.setActionCommand(BorrowerDialog.checkAccount);
		checkAccount.addActionListener(this);
		
		JButton placeHoldRequest = new JButton (BorrowerDialog.placeHoldRequest);
		placeHoldRequest.setVerticalAlignment(AbstractButton.CENTER);
		placeHoldRequest.setHorizontalAlignment(AbstractButton.CENTER);
		placeHoldRequest.setActionCommand(BorrowerDialog.placeHoldRequest);
		placeHoldRequest.addActionListener(this);
		
		JButton payFine = new JButton (BorrowerDialog.payFine);
		payFine.setVerticalAlignment(AbstractButton.CENTER);
		payFine.setHorizontalAlignment(AbstractButton.CENTER);
		payFine.setActionCommand(BorrowerDialog.payFine);
		payFine.addActionListener(this);
		
		JButton returnToChooseUserDialogBtn = new JButton (Constants.RETURN_TO_CHOOSE_USER_DIALOG);
		returnToChooseUserDialogBtn.setVerticalAlignment(AbstractButton.CENTER);
		returnToChooseUserDialogBtn.setHorizontalAlignment(AbstractButton.CENTER);
		returnToChooseUserDialogBtn.setActionCommand(Constants.RETURN_TO_CHOOSE_USER_DIALOG);
		returnToChooseUserDialogBtn.addActionListener(this);
		
		this.add(searchForBooks, BorderLayout.LINE_START);
		this.add(checkAccount, BorderLayout.CENTER);
		this.add(placeHoldRequest, BorderLayout.AFTER_LAST_LINE);
		this.add(payFine, BorderLayout.AFTER_LAST_LINE);
		this.add(returnToChooseUserDialogBtn, BorderLayout.AFTER_LAST_LINE);
	}
	
	public static void createAndShowGUI(String borrowerID)
	{
		//TODO user borrowerID to took up borrower
		bid = borrowerID;
		mainFrame = new JFrame("Borrower Interface");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComponent newContentPane = new BorrowerDialog();
		newContentPane.setOpaque(true);
		mainFrame.setContentPane(newContentPane);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (BorrowerDialog.searchBooks.equals(arg0.getActionCommand()))
		{
			SearchForBooksDialog.createAndShowGUI();
		} else if (BorrowerDialog.checkAccount.equals(arg0.getActionCommand()))
		{
			CheckBorrowerAcctDialog.createAndShowGUI();
		} else if (BorrowerDialog.placeHoldRequest.equals(arg0.getActionCommand()))
		{
			PlaceHoldDialog.createAndShowGUI(bid);
		} else if (BorrowerDialog.payFine.equals(arg0.getActionCommand()))
		{
			PayFineDialog.createAndShowGUI();
		}else if (Constants.RETURN_TO_CHOOSE_USER_DIALOG.equals(arg0.getActionCommand()))
		{
			mainFrame.dispose();
			ChooseUserDialog.createAndShowGUI();
		}
		
	}

}
