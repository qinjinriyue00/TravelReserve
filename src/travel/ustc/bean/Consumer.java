package travel.ustc.bean;

public class Consumer {
	
	private String custName;
	private String password;
	private int balance;
	
	public Consumer(){
		
	}
	
	public Consumer(String custName, String password, int balance) {
		super();
		this.custName = custName;
		this.password = password;
		this.balance = balance;
	}
	
	public Consumer(String custName, String password) {
		super();
		this.custName = custName;
		this.password = password;
	}

	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

}
