package app;

import javax.swing.SwingUtilities;

public class BankApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			BankBalanceGUI gui = new BankBalanceGUI();
			gui.setVisible(true);
		});

	}

}
