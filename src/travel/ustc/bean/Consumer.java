package travel.ustc.bean;

public class Consumer {
	
	private String custName;
	private String password;
	private double balance;
	
	public Consumer(){
		
	}
	
	public Consumer(String custName, String password, double balance) {
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

}
