package app;

import javax.swing.SwingUtilities;

public class BankApp {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BankBalanceGUI gui = new BankBalanceGUI();
                gui.setVisible(true);
            }
        });
    }
}
