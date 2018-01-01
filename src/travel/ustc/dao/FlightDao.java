package travel.ustc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import travel.ustc.bean.Flight;

public class FlightDao extends BaseDao {

	public FlightDao(String url, String dBUser, String dBPassword) {
		super();
		this.url = url;
		this.DBUser = dBUser;
		this.DBPassword = dBPassword;
	}

	@Override
	public List<Flight> query() throws SQLException {
		Connection conn = openDBConnection();
		String sql = "select * from Flight";
		PreparedStatement pState = conn.prepareStatement(sql);
		ResultSet rs = pState.executeQuery();
		List<Flight> list = new ArrayList<Flight>();
		while (rs.next()) {
			Flight newFlight = new Flight(rs.getString("FlightNum"),
					rs.getInt("Price"), rs.getInt("NumSeats"),
					rs.getInt("NumAvail"), rs.getString("FromCity"),
					rs.getString("ArivCity"));
			list.add(newFlight);
		}
		conn.close();
		return list;
	}

	public List<Flight> query(String fromCity, String arivCity)
			throws SQLException {
		Connection conn = openDBConnection();
		String sql = "select * from Flight where FromCity=? and ArivCity=?";
		PreparedStatement pState = conn.prepareStatement(sql);
		pState.setString(1, fromCity);
		pState.setString(2, arivCity);
		ResultSet rs = pState.executeQuery();
		List<Flight> list = new ArrayList<Flight>();
		while (rs.next()) {
			Flight newFlight = new Flight(rs.getString("FlightNum"),
					rs.getInt("Price"), rs.getInt("NumSeats"),
					rs.getInt("NumAvail"), rs.getString("FromCity"),
					rs.getString("ArivCity"));
			list.add(newFlight);
		}
		conn.close();
		return list;
	}

	@Override
	public boolean insert(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
