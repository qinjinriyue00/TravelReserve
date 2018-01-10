package travel.ustc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class ReserveAction {
	private int resvType;
	private String resvKey;

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

	public String execute() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		request.setAttribute("resvType", resvType);
		request.setAttribute("resvKey", resvKey);
		return "success";
	}
}
