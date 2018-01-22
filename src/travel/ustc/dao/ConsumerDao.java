package travel.ustc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import travel.ustc.bean.Consumer;

public class ConsumerDao extends BaseDao{

	@Override
	public List<Consumer> query() throws SQLException {//´òÓ¡ËùÓÐ¹Ë¿ÍÁÐ±í
		Connection conn= openDBConnection();
		PreparedStatement stmt = (PreparedStatement) conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from customer");
		List<Consumer> consumers = new ArrayList<Consumer>();
		Consumer c = null;
		while(rs.next()){
			c=new Consumer();
			c.setCustName(rs.getString("custName"));
			c.setPassword(rs.getString("password"));
			c.setBalance(rs.getInt("balance"));
			//System.out.println(rs.getString("custName")+rs.getString("password")+rs.getInt("balance"));
			consumers.add(c);
		}
		return consumers;
	}
	
	public Consumer get(Consumer consumer) throws SQLException {//¸ù¾ÝÐÕÃû²éÑ¯µ¥¸ö¹Ë¿Í
		Connection conn= openDBConnection();
		String sql="select * from customer where custName = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,consumer.getCustName());
		ResultSet rs=stmt.executeQuery();		     
		Consumer c = null;
		if(rs.next()){
			c=new Consumer();
			c.setCustName(rs.getString("custName"));
			c.setPassword(rs.getString("password"));
			c.setBalance(rs.getInt("balance"));
			//System.out.println(rs.getString("custName")+rs.getString("password")+rs.getInt("balance"));
		}
		return c;
	}
	
	@Override
	public boolean insert(Object obj) throws SQLException { //²åÈë¹Ë¿ÍÐÅÏ¢
		Consumer consumer=(Consumer)obj;
		Connection conn= openDBConnection();
		String sql="insert into customer (custName,password,balance)values (?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,consumer.getCustName());
		ptmt.setString(2,consumer.getPassword());
		ptmt.setDouble(3,consumer.getBalance());
		boolean flag=ptmt.execute();
		return flag;
	}
	
	@Override
	public boolean update(Object obj) throws SQLException { //¸üÐÂ¹Ë¿ÍÐÅÏ¢
		Consumer consumer=(Consumer)obj;
		Connection conn= openDBConnection();
		String sql="update customer set balance=? where custName=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setDouble(1,consumer.getBalance());
		ptmt.setString(2, consumer.getCustName());
		boolean flag=ptmt.execute();
		return flag;
	}
	
	@Override//¸ù¾Ý¹Ë¿ÍÐÕÃûÉ¾³ýÊý¾Ý¿â
	public boolean delete(Object obj) throws SQLException {
		Consumer consumer=(Consumer)obj;
		Connection conn= openDBConnection();
		String sql="delete from customer where custName=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,consumer.getCustName());
		boolean flag=ptmt.execute();
		return flag;
	}
}
