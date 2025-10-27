package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.JOptionPane;

public class BankBalanceGUI extends JFrame{
    
	private final JLabel balanceLabel;
	private final JTextField amountField;
	private final JButton depositButton;
	private final JButton withdrawButton;
	private final JButton exitButton;
	
	private JPanel middlePanel;
	private JLabel amountLabel;
	private String pendingAction = "";
	
	private final BankAccount account = new BankAccount(0.0);
	private final NumberFormat money = NumberFormat.getCurrencyInstance();
    
    public BankBalanceGUI() {
    	setTitle("Bank Balance");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(420,220);
    	setLocationRelativeTo(null);
    	
    	//Balance Display
    	JPanel topPanel = new JPanel();
    	balanceLabel = new JLabel("Balance: $0.00");
    	topPanel.add(balanceLabel);
    	
    	
    	//Amount input
    	middlePanel = new JPanel();
    	amountLabel = new JLabel("Amount:");
    	amountField = new JTextField(5);
    	middlePanel.add(amountLabel);
    	middlePanel.add(amountField);
    	middlePanel.setVisible(false);
    	
    	//Buttons
    	JPanel bottomPanel = new JPanel();
    	depositButton = new JButton("Deposit");
    	withdrawButton = new JButton("Withdraw");
    	exitButton = new JButton("Exit");
    	
    	bottomPanel.add(depositButton);
    	bottomPanel.add(withdrawButton);
    	bottomPanel.add(exitButton);
    	
    	//action Listeners
    	depositButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if (!middlePanel.isVisible() || !"DEPOSIT".equals(pendingAction)) {
    				pendingAction = "DEPOSIT";
    				amountLabel.setText("Amount To Deposit:");
    				amountField.setText("");
    				middlePanel.setVisible(true);
    				amountField.requestFocusInWindow();
    				middlePanel.revalidate();
    				middlePanel.repaint();
    				return;
    		}
    			Double amt = parseAmountOrWarn();
    			if (amt == null) return;
    			
    			try {
    				account.deposit(amt);
    				updateBalanceLabel();
    				middlePanel.setVisible(false);
    				pendingAction = "";
    			} catch (IllegalArgumentException ex) {
    				JOptionPane.showMessageDialog(BankBalanceGUI.this, ex.getMessage(), "Deposit Error", JOptionPane.ERROR_MESSAGE);
    			}
    	}});
    	
    	withdrawButton.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        if (!middlePanel.isVisible() || !"WITHDRAW".equals(pendingAction)) {
    	            pendingAction = "WITHDRAW";
    	            amountLabel.setText("Amount to withdraw:");
    	            amountField.setText("");
    	            middlePanel.setVisible(true);
    	            amountField.requestFocusInWindow();
    	            middlePanel.revalidate();
    	            middlePanel.repaint();
    	            return;
    	        }

    	        Double amt = parseAmountOrWarn();
    	        if (amt == null) return;

    	        try {
    	            account.withdraw(amt);
    	            updateBalanceLabel();
    	            middlePanel.setVisible(false);
    	            pendingAction = "";
    	        } catch (IllegalArgumentException ex) {
    	            JOptionPane.showMessageDialog(
    	                BankBalanceGUI.this,
    	                ex.getMessage(),
    	                "Withdrawal error",
    	                JOptionPane.ERROR_MESSAGE
    	            );
    	        }
    	    }
    	});
    	
    	exitButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			JOptionPane.showMessageDialog(BankBalanceGUI.this, "Final Balance: " + balanceLabel.getText().replace("Balance: ", ""), "Exit", JOptionPane.INFORMATION_MESSAGE);
    			System.exit(0);
    		}
    	});
    	
    	
    	//layout
    	JPanel mainPanel = new JPanel(new BorderLayout());
    	mainPanel.add(topPanel, BorderLayout.NORTH);
    	mainPanel.add(middlePanel, BorderLayout.CENTER);
    	mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    	
    	setContentPane(mainPanel);
    	
    	updateBalanceLabel();
    }
    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: " + money.format(account.getBalance()));
    }
    
    private Double parseAmountOrWarn() {
    	String input = amountField.getText().trim();
    	try {
    		double amt = Double.parseDouble(input);
    		if (amt <= 0) throw new NumberFormatException();
    		return amt;
    	} catch (NumberFormatException ex) {
    		JOptionPane.showMessageDialog(this, "Please Enter A Valid Number.", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
    	}
		return null;
    }
    
}
