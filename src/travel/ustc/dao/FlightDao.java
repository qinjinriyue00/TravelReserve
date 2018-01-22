package travel.ustc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import travel.ustc.bean.Flight;

public class FlightDao extends BaseDao {

	public FlightDao() {
		super();
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
	
	public Flight get(Flight flight) throws SQLException{
		Connection conn = openDBConnection();
		String sql="select * from Flight where flightNum=?";
		PreparedStatement pState = conn.prepareStatement(sql);
		pState.setString(1, flight.getFlightNum());
		ResultSet rs = pState.executeQuery();
		Flight newFlight=null;
		while (rs.next()) {
			newFlight = new Flight(rs.getString("FlightNum"),
					rs.getInt("Price"), rs.getInt("NumSeats"),
					rs.getInt("NumAvail"), rs.getString("FromCity"),
					rs.getString("ArivCity"));
		}
		return newFlight;
	}

	@Override
	public boolean insert(Object obj) throws SQLException { //insert car information
		Flight flight=(Flight)obj;
		Connection conn= openDBConnection();
		String sql="insert into flight (flightNum,price,numSeats,numAvail,fromCity,arivCity)values (?,?,?,?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,flight.getFlightNum());
		ptmt.setInt(2,flight.getPrice());
		ptmt.setInt(3,flight.getNumSeats());
		ptmt.setInt(4,flight.getNumAvail());
		ptmt.setString(5,flight.getFromCity());
		ptmt.setString(6,flight.getArivCity());
		boolean flag=ptmt.execute();
		return flag;
	}

	@Override
	public boolean update(Object obj) throws SQLException {//update car information
		Flight flight=(Flight)obj;
		Connection conn= openDBConnection();
		String sql="update flight set price=?,numSeats=?,numAvail=?,fromCity=?,arivCity=? where flightNum=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,flight.getPrice());
		ptmt.setInt(2,flight.getNumSeats());
		ptmt.setInt(3,flight.getNumAvail());
		ptmt.setString(4,flight.getFromCity());
		ptmt.setString(5,flight.getArivCity());
		ptmt.setString(6,flight.getFlightNum());
		boolean flag=ptmt.execute();
		return flag;
	}
	
	public boolean updateNumAvail(String key,int numAvail) throws SQLException{
		Connection conn=openDBConnection();
		String sql="update flight set numAvail=? where FlightNum=?";
		PreparedStatement pState=conn.prepareStatement(sql);
		pState.setInt(1,numAvail);
		pState.setString(2, key);
		int flag=pState.executeUpdate();
		if(flag>0) return true;
		else return false;
	}

	@Override
	public boolean delete(Object obj) throws SQLException {//delete car information
		Flight flight=(Flight)obj;
		Connection conn= openDBConnection();
		String sql="delete from flight where flightNum=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,flight.getFlightNum());
		boolean flag=ptmt.execute();
		return flag;
	}

}
