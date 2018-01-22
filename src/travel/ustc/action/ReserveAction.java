package travel.ustc.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import travel.ustc.bean.Car;
import travel.ustc.bean.Consumer;
import travel.ustc.bean.Coupon;
import travel.ustc.bean.Flight;
import travel.ustc.bean.Hotel;
import travel.ustc.bean.Reserve;
import travel.ustc.dao.*;

public class ReserveAction {

	private int id;
	private int resvType;
	private int numAvail;
	private int price;
	private String resvKey;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumAvail() {
		return numAvail;
	}

	public void setNumAvail(int numAvail) {
		this.numAvail = numAvail;
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

	public String execute() throws UnsupportedEncodingException, SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		// insert reserve
		ReserveDao reserveDao = new ReserveDao();
		Reserve reserve = new Reserve();
		reserve.setCustName((String) session.getAttribute("custName"));
		reserve.setResvType(resvType);
		String newResvKey = new String(resvKey.getBytes("ISO-8859-1"), "utf-8");
		reserve.setResvKey(newResvKey);
		boolean flag = reserveDao.insert(reserve);

		// update numAvail
		switch (resvType) {
		case 1:
			FlightDao flightDao = new FlightDao();
			flightDao.updateNumAvail(newResvKey, numAvail - 1);
			break;
		case 2:
			HotelDao hotelDao = new HotelDao();
			hotelDao.updateNumAvail(newResvKey, numAvail - 1);
			break;
		case 3:
			CarDao carDao = new CarDao();
			carDao.updateNumAvail(new Integer(resvKey), numAvail - 1);
			break;
		}

		// update balance
		// get consumer balance
		Consumer consumer = new Consumer();
		consumer.setCustName((String) session.getAttribute("custName"));
		ConsumerDao consumerDao = new ConsumerDao();
		Consumer newConsumer=consumerDao.get(consumer);
		int balance = newConsumer.getBalance();
		// get consumer coupon
		Coupon coupon = new Coupon();
		CouponDao couponDao = new CouponDao();
		coupon.setCustName((String) session.getAttribute("custName"));
		coupon.setType(resvType);
		Coupon cp = (Coupon) couponDao.get(coupon, price);
		int discount = 0;
		if (cp != null) {
			discount = cp.getDiscount();
			couponDao.delete(cp);
		} else {
			System.out.println("没有使用优惠券");
		}
		// update
		consumer.setBalance(balance - price + discount);
		consumerDao.update(consumer);

		request.setAttribute("resvType", resvType);
		request.setAttribute("resvKey",
				new String(resvKey.getBytes("ISO-8859-1"), "utf-8"));

		if (flag) {
			return "success";
		} else {
			return "false";
		}
	}

	public String search() throws SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		ReserveDao reserveDao = new ReserveDao();
		List<Reserve> reserveInfo = (List<Reserve>) reserveDao
				.query((String) session.getAttribute("custName"));
		request.setAttribute("reserveInfo", reserveInfo);

		Consumer consumer = new Consumer();
		consumer.setCustName((String) session.getAttribute("custName"));
		ConsumerDao consumerDao = new ConsumerDao();
		Consumer con = consumerDao.get(consumer);
		request.setAttribute("balance", con.getBalance());

		Coupon coupon = new Coupon();
		coupon.setCustName((String) session.getAttribute("custName"));
		CouponDao couponDao = new CouponDao();
		List<Coupon> coupons = (List<Coupon>) couponDao.query(coupon);
		request.setAttribute("couponInfo", coupons);
		return "success";
	}

	public String cancel() throws SQLException {
		// delete reserve record
		ReserveDao reserveDao = new ReserveDao();
		Reserve reserve = new Reserve();
		reserve.setId(id);
		reserveDao.delete(reserve);
		
		// add numAvail
		switch(resvType){
		case 1:
			FlightDao flightDao=new FlightDao();
			Flight flight=new Flight();
			flight.setFlightNum(resvKey);
			Flight newFlight=flightDao.get(flight);
			numAvail=newFlight.getNumAvail();
			flightDao.updateNumAvail(resvKey, numAvail+1);
			break;
		case 2:
			HotelDao hotelDao=new HotelDao();
			Hotel hotel=new Hotel();
			hotel.setHotelName(resvKey);
			Hotel newHotel=hotelDao.getHotel(hotel);
			numAvail=newHotel.getNumAvail();
			hotelDao.updateNumAvail(resvKey, numAvail+1);
			break;
		case 3:
			CarDao carDao=new CarDao();
			Car car=new Car();
			car.setKey1(new Integer(resvKey));
			Car newCar=carDao.getCar(car);
			numAvail=newCar.getNumAvail();
			carDao.updateNumAvail(new Integer(resvKey), numAvail+1);
			break;
		}
		return "success";
	}
}
