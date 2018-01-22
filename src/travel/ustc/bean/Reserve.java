package travel.ustc.bean;

public class Reserve {

	public Reserve() {

	}

	private int id;
	private String custName;
	private int resvType;
	private String resvKey;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getResvType() {
		return resvType;
	}

	public void setResvType(int resvType) {
		this.resvType = resvType;
	}

	public String getResvKey() {
		return resvKey;
	}

	public void setResvKey(String resvKey) {
		this.resvKey = resvKey;
	}

}
