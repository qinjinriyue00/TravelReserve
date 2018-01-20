package travel.ustc.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import travel.ustc.bean.Reserve;
import travel.ustc.dao.ReserveDao;

public class ReserveAction {

	private String url = "jdbc:mysql://127.0.0.1:3306/TravelReserve?characterEncoding=utf8";
	private String DBUser = "root";
	private String DBPassword = "123";

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

	public String execute() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		ReserveDao reserveDao = new ReserveDao(url, DBUser, DBPassword);
		Reserve reserve = new Reserve();
		reserve.setCustName((String) session.getAttribute("custName"));
		reserve.setResvType(resvType);
		reserve.setResvKey(new String(resvKey.getBytes("ISO-8859-1"),"utf-8"));
		System.out.println(new String(resvKey.getBytes("ISO-8859-1"),"utf-8"));
		boolean flag = false;
		try {
			flag = reserveDao.insert(reserve);
			System.out.println(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("resvType", resvType);
		request.setAttribute("resvKey", new String(resvKey.getBytes("ISO-8859-1"),"utf-8"));
		
		if (flag) {
			return "success";
		} else {
			return "false";
		}

	}
	
	public String search(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		ReserveDao reserveDao = new ReserveDao(url, DBUser, DBPassword);
		try {
			List<Reserve> reserveInfo=(List<Reserve>) reserveDao.query((String) session.getAttribute("custName"));
			request.setAttribute("reserveInfo", reserveInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
}
