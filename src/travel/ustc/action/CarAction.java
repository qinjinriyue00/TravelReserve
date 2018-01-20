package travel.ustc.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import travel.ustc.bean.Car;
import travel.ustc.dao.CarDao;

public class CarAction {
	
	private String url = "jdbc:mysql://127.0.0.1:3306/TravelReserve?characterEncoding=utf8";
	private String DBUser = "root";
	private String DBPassword = "123";
	
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String execute() throws SQLException, UnsupportedEncodingException{
		CarDao carDao=new CarDao(url,DBUser,DBPassword);
		List<Car> carInfo=null;
		if(location!=null){
			carInfo=carDao.get(new String(location.getBytes("ISO-8859-1"),"utf-8"));
		}else{
			carInfo=carDao.query();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("carInfo", carInfo);
		return "success";
	}

}
