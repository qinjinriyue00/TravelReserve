package travel.ustc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import travel.ustc.bean.Hotel;

public class HotelDao extends BaseDao {

	public HotelDao(String url, String DBUser, String DBPassword) {
		this.url = url;
		this.DBUser = DBUser;
		this.DBPassword = DBPassword;
	}

	@Override
	public List<Hotel> query() throws SQLException {// query all hotels
													// information
		Connection conn = openDBConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Hotel");
		List<Hotel> hotels = new ArrayList<Hotel>();
		Hotel h = null;
		while (rs.next()) {
			h = new Hotel();
			h.setHotelName(rs.getString("hotelName"));
			h.setLocation(rs.getString("location"));
			h.setPrice(rs.getInt("price"));
			h.setNumRooms(rs.getInt("numRooms"));
			h.setNumAvail(rs.getInt("numAvail"));
			// System.out.println(rs.getString("type")+rs.getString("location")+rs.getInt("price"));
			hotels.add(h);
		}
		conn.close();
		return hotels;
	}

	public List<Hotel> get(String location) throws SQLException {// get hotels
																	// information
																	// by
																	// location
		Connection conn = openDBConnection();
		String sql = "select * from hotel where location = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, location);
		ResultSet rs = stmt.executeQuery();
		List<Hotel> hotels = new ArrayList<Hotel>();
		Hotel h = null;
		while (rs.next()) {
			h = new Hotel();
			h.setHotelName(rs.getString("hotelName"));
			h.setLocation(rs.getString("location"));
			h.setPrice(rs.getInt("price"));
			h.setNumRooms(rs.getInt("numRooms"));
			h.setNumAvail(rs.getInt("numAvail"));
			// System.out.println(rs.getString("type")+rs.getString("location")+rs.getInt("price"));
			hotels.add(h);
		}
		conn.close();
		return hotels;
	}

	@Override
	public boolean insert(Object obj) throws SQLException { // insert hotel
															// information
		Hotel hotel = (Hotel) obj;
		Connection conn = openDBConnection();
		String sql = "insert into hotel (hotelName,location,price,numRooms,numAvail) values (?,?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, hotel.getHotelName());
		ptmt.setString(2, hotel.getLocation());
		ptmt.setInt(3, hotel.getPrice());
		ptmt.setInt(4, hotel.getNumRooms());
		ptmt.setInt(5, hotel.getNumAvail());
		boolean flag = ptmt.execute();
		conn.close();
		return flag;
	}

	@Override
	public boolean update(Object obj) throws SQLException {// update hotel
															// information
		Hotel hotel = (Hotel) obj;
		Connection conn = openDBConnection();
		String sql = "update hotel set hotelName=?,location=?,price=?,numAvail=? where numRooms=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, hotel.getHotelName());
		ptmt.setString(2, hotel.getLocation());
		ptmt.setInt(3, hotel.getPrice());
		ptmt.setInt(4, hotel.getNumAvail());
		ptmt.setInt(5, hotel.getNumRooms());
		boolean flag = ptmt.execute();
		conn.close();
		return flag;
	}

	@Override
	public boolean delete(Object obj) throws SQLException {// delete hotel
															// information
		Hotel hotel = (Hotel) obj;
		Connection conn = openDBConnection();
		String sql = "delete from hotel where numRooms=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, hotel.getNumRooms());
		boolean flag = ptmt.execute();
		conn.close();
		return flag;
	}

}
