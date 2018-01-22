package travel.ustc.bean;

public class Coupon {

	private String custName;
	private String coupNum;
	private int type;
	private int discount;

	public Coupon(String custName, String coupNum, int type, int discount) {
		super();
		this.custName = custName;
		this.coupNum = coupNum;
		this.type = type;
		this.discount = discount;
	}

	public Coupon() {
	}

	public String getCoupNum() {
		return coupNum;
	}

	public void setCoupNum(String coupNum) {
		this.coupNum = coupNum;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}
