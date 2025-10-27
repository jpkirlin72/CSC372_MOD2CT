package app;


public class BankAccount {

	private double balance;
	
	public BankAccount() {
		this(0.0);
	}
	
	//Starting Balance
	public BankAccount(double startingBalance) {
		if (startingBalance < 0 ) {
			throw new IllegalArgumentException("Starting Balance Cannot Be Zero");
		}
		this.balance = startingBalance;
	}
	
	
	
	
	//Get Balance
	public double getBalance() {
		return balance;
	}
	
	//Deposit
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit Amount Must Be Positive");
		}
		balance += amount;
	}
	
	//Withdraw
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal Amount Must Be Positive");
		}
		if (amount > balance) {
			throw new IllegalArgumentException("Insufficient Funds");
		}
		balance -= amount;
	}
	

}
