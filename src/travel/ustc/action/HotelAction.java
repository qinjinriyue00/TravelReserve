package travel.ustc.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import travel.ustc.bean.Hotel;
import travel.ustc.dao.HotelDao;

import com.opensymphony.xwork2.ActionSupport;

public class HotelAction extends ActionSupport {
	
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
		HotelDao hotelDao=new HotelDao(url,DBUser,DBPassword);
		List<Hotel> hotelInfo=null;
		if(location!=null){
			System.out.println("location:"+new String(location.getBytes("ISO-8859-1"),"utf-8"));
			hotelInfo=hotelDao.get(new String(location.getBytes("ISO-8859-1"),"utf-8"));
		}else{
			hotelInfo=hotelDao.query();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("hotelInfo", hotelInfo);
		return "success";		
	}
}
