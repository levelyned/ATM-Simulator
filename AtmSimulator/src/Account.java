import java.util.ArrayList;
import java.util.Date;

public class Account {
	String accountNumber;
	int balance;
	String password;
	String accountType;
	String username;
	ArrayList<History> history = new ArrayList<History>();
	
	public Account(String accountNumber, int balance, String password, String accountType, String username) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.password = password;
		this.accountType = accountType;
		this.username = username;
	}
	
	public Account(String accountNumber, int balance, String password, String accountType, String username,
			ArrayList<History> history) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.password = password;
		this.accountType = accountType;
		this.username = username;
		this.history = history;
	}

	public ArrayList<History> getHistory() {
		return history;
	}
	public void setHistory(ArrayList<History> history) {
		this.history = history;
	}
	public void setHistory(Date date, int money, String status) {
		History newHistory = new History(date, money, status);
		this.history.add(newHistory);
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
