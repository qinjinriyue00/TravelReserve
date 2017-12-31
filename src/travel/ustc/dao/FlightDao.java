package travel.ustc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import travel.ustc.bean.Flight;

public class FlightDao extends BaseDao {
	
	public FlightDao(String url, String dBUser, String dBPassword){
		super();
		this.url = url;
		this.DBUser = dBUser;
		this.DBPassword = dBPassword;
	}

	@Override
	public Flight query(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		Flight flight = (Flight) obj;
		Connection conn = openDBConnection();
		String sql = "select * from Flight where FlightNum=?";
		PreparedStatement pState = conn.prepareStatement(sql);
		pState.setString(1, flight.getFlightNum());
		ResultSet rs = pState.executeQuery();
		if (rs.next()) {
			Flight newFlight = new Flight(rs.getString("FlightNum"),
					rs.getInt("Price"), rs.getInt("NumSeats"),
					rs.getInt("NumAvail"), rs.getString("FromCity"),
					rs.getString("ArivCity"));
			conn.close();
			return newFlight;
		}
		return null;
	}
	
	public List<Flight> query() throws SQLException{
		Connection conn = openDBConnection();
		String sql = "select * from Flight";
		PreparedStatement pState = conn.prepareStatement(sql);
		ResultSet rs = pState.executeQuery();
		List<Flight> list=new ArrayList<Flight>();
		while(rs.next()){
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
