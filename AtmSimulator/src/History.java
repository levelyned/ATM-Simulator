import java.util.Date;

public class History{
	Date transactionDate = new Date();
	int money;
	String status;
	public History(Date transactionDate, int money, String status) {
		super();
		this.transactionDate = transactionDate;
		this.money = money;
		this.status = status;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
