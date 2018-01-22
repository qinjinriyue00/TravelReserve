package travel.ustc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import travel.ustc.bean.Car;

public class CarDao extends BaseDao{
	
	public CarDao(){
		super();
	}

	@Override
	public List<Car> query() throws SQLException {//query all cars information
		Connection conn= openDBConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from car;");
		List<Car> cars = new ArrayList<Car>();
		Car c = null;
		while(rs.next()){
			c=new Car();
			c.setKey1(rs.getInt("key1"));
			c.setType(rs.getString("type"));
			c.setLocation(rs.getString("location"));
			c.setNumAvail(rs.getInt("numAvail"));
			c.setPrice(rs.getInt("price"));
			c.setNumCars(rs.getInt("numCars"));
			//	System.out.println(rs.getString("type")+rs.getString("location")+rs.getInt("price"));
			cars.add(c);
		}
		conn.close();
		return cars;
	}

	public List<Car> get(String location) throws SQLException {//get cars information by location
		Connection conn= openDBConnection();
		String sql="select * from car where location = ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		stmt.setString(1,location);  
		List<Car> cars = new ArrayList<Car>();
		Car c = null;
		while(rs.next()){
			c=new Car();
			c.setKey1(rs.getInt("key1"));
			c.setType(rs.getString("type"));
			c.setLocation(rs.getString("location"));
			c.setNumAvail(rs.getInt("numAvail"));
			c.setPrice(rs.getInt("price"));
			c.setNumCars(rs.getInt("numCars"));
			//	System.out.println(rs.getString("type")+rs.getString("location")+rs.getInt("price"));
			cars.add(c);
		}
		conn.close();
		return cars;
	}
	
	public Car getCar(Car car) throws SQLException{
		Connection conn= openDBConnection();
		String sql="select * from car where key1=?";
		PreparedStatement pState=conn.prepareStatement(sql);
		pState.setInt(1, car.getKey1());
		ResultSet rs=pState.executeQuery();
		Car newCar=new Car();
		while(rs.next()){
			newCar.setKey1(rs.getInt("key1"));
			newCar.setNumAvail(rs.getInt("numAvail"));
		}
		return newCar;
	}
	
	@Override
	public boolean insert(Object obj) throws SQLException { //insert car information
		Car car=(Car)obj;
		Connection conn= openDBConnection();
		String sql="insert into car (type,location,price,numCars,numAvail) values (?,?,?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,car.getType());
		ptmt.setString(2,car.getLocation());
		ptmt.setInt(3,car.getPrice());
		ptmt.setInt(4,car.getNumCars());
		ptmt.setInt(5,car.getNumAvail());
		boolean flag=ptmt.execute();
		conn.close();
		return flag;
	}

	@Override
	public boolean update(Object obj) throws SQLException {//update car information
		Car car=(Car)obj;
		Connection conn= openDBConnection();
		String sql="update car set type=?,location=?,price=?,numAvail=? where numCars=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,car.getType());
		ptmt.setString(2,car.getLocation());
		ptmt.setInt(3,car.getPrice());
		ptmt.setInt(4,car.getNumAvail());
		ptmt.setInt(5,car.getNumCars());
		boolean flag=ptmt.execute();
		conn.close();
		return flag;
	}
	
	public boolean updateNumAvail(int key,int numAvail) throws SQLException{
		Connection conn=openDBConnection();
		String sql="update car set numAvail=? where key1=?";
		PreparedStatement pState=conn.prepareStatement(sql);
		pState.setInt(1,numAvail);
		pState.setInt(2, key);
		int flag=pState.executeUpdate();
		if(flag>0) return true;
		else return false;
	}


	@Override
	public boolean delete(Object obj) throws SQLException {//delete car information
		Car car=(Car)obj;
		Connection conn= openDBConnection();
		String sql="delete from car where numCars=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,car.getNumCars());
		boolean flag=ptmt.execute();
		conn.close();
		return flag;
	}

}
