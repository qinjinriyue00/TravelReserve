package travel.ustc.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import travel.ustc.bean.Flight;
import travel.ustc.dao.FlightDao;

public class FlightAction {

	private String fromCity;
	private String arivCity;
	private List<Flight> list;

	public FlightAction() {

	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getArivCity() {
		return arivCity;
	}

	public void setArivCity(String arivCity) {
		this.arivCity = arivCity;
	}

	public List<Flight> getList() {
		return list;
	}

	public void setList(List<Flight> list) {
		this.list = list;
	}

	public String execute() throws SQLException {
		FlightDao flightDao = new FlightDao();
		if (fromCity != null && arivCity != null) {
			list = flightDao.query(fromCity, arivCity);
		} else {
			list = flightDao.query();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "success";
	}
}
