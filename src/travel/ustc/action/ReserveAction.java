package travel.ustc.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import travel.ustc.bean.Reserve;
import travel.ustc.dao.*;

public class ReserveAction {

	private int resvType;
	private int numAvail;

	public int getNumAvail() {
		return numAvail;
	}

	public void setNumAvail(int numAvail) {
		this.numAvail = numAvail;
	}

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

	public String execute() throws UnsupportedEncodingException, SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		ReserveDao reserveDao = new ReserveDao();
		Reserve reserve = new Reserve();
		reserve.setCustName((String) session.getAttribute("custName"));
		reserve.setResvType(resvType);
		String newResvKey=new String(resvKey.getBytes("ISO-8859-1"), "utf-8");
		reserve.setResvKey(newResvKey);
		boolean flag= reserveDao.insert(reserve);
		
		//uodate numAvail
		switch(resvType){
		case 1:
			FlightDao flightDao=new FlightDao();
			flightDao.updateNumAvail(newResvKey,numAvail-1);
			break;
		case 2:
			HotelDao hotelDao =new HotelDao();
			hotelDao.updateNumAvail(newResvKey,numAvail-1);
			break;
		case 3:
			CarDao carDao=new CarDao();
			carDao.updateNumAvail(newResvKey,numAvail-1);
			break;
		}

		request.setAttribute("resvType", resvType);
		request.setAttribute("resvKey",
				new String(resvKey.getBytes("ISO-8859-1"), "utf-8"));

		if (flag) {
			return "success";
		} else {
			return "false";
		}
	}

	public String search() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		ReserveDao reserveDao = new ReserveDao();
		try {
			List<Reserve> reserveInfo = (List<Reserve>) reserveDao
					.query((String) session.getAttribute("custName"));
			request.setAttribute("reserveInfo", reserveInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
}
