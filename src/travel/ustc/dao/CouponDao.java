package travel.ustc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import travel.ustc.bean.Coupon;

public class CouponDao extends BaseDao {

	@Override
	public Object query() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = openDBConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from Coupon");
		List<Coupon> list = new ArrayList<Coupon>();
		while (rs.next()) {
			Coupon newCoupon = new Coupon(rs.getString("custName"),rs.getString("coupNum"),
					rs.getInt("type"), rs.getInt("discount"));
			list.add(newCoupon);
		}
		conn.close();
		return list;
	}
	
	public Object query(Coupon coupon) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = openDBConnection();
		String sql="select * from coupon where custName=?";
		PreparedStatement pState=conn.prepareStatement(sql);
		pState.setString(1, coupon.getCustName());
		ResultSet rs=pState.executeQuery();
		List<Coupon> list = new ArrayList<Coupon>();
		while (rs.next()) {
			Coupon newCoupon = new Coupon(rs.getString("custName"),rs.getString("coupNum"),
					rs.getInt("type"), rs.getInt("discount"));
			list.add(newCoupon);
		}
		conn.close();
		return list;
	}
	
	public Object get(Coupon coupon,int price) throws SQLException{
		Connection conn= openDBConnection();
		String sql="select * from coupon where custname=? and type=? and discount<? ";
		PreparedStatement pState = conn.prepareStatement(sql);
		pState.setString(1, coupon.getCustName());
		pState.setInt(2, coupon.getType());
		pState.setInt(3, price);
		ResultSet rs=pState.executeQuery();  
		List<Coupon> coupons = new ArrayList<Coupon>();
		Coupon c = null;
		while(rs.next()){
			c=new Coupon();
			c.setCoupNum(rs.getString("coupNum"));
			c.setType(rs.getInt("type"));
			c.setDiscount(rs.getInt("discount"));
			//	System.out.println(rs.getString("type")+rs.getString("location")+rs.getInt("price"));
			coupons.add(c);
		}
		conn.close();
		int maxDiscount=0;
		Coupon maxCoup=null;
		for(Coupon coup:coupons){	
			if(coup.getDiscount()>maxDiscount){
				maxCoup=coup;
				maxDiscount=coup.getDiscount();
			}
		}
		return maxCoup;
	}

	@Override
	public boolean insert(Object obj) throws SQLException {
		Coupon coupon=(Coupon)obj;
		Connection conn= openDBConnection();
		String sql="insert into Coupon (custName,coupNum,type,discount) values (?,?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,coupon.getCustName());
		ptmt.setString(2,coupon.getCoupNum());
		ptmt.setInt(3,coupon.getType());
		ptmt.setInt(4,coupon.getDiscount());
		boolean flag=ptmt.execute();
		conn.close();
		return flag;
	}

	@Override
	public boolean update(Object obj) throws SQLException {
		Coupon coupon=(Coupon)obj;
		Connection conn= openDBConnection();
		String sql="update coupon set custName=?,type=?,price=? where coupNum=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,coupon.getCustName());
		ptmt.setInt(2,coupon.getType());
		ptmt.setInt(3,coupon.getDiscount());
		ptmt.setString(4,coupon.getCoupNum());
		boolean flag=ptmt.execute();
		conn.close();
		return flag;
	}

	@Override
	public boolean delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		Coupon coupon=(Coupon)obj;
		Connection conn= openDBConnection();
		String sql="delete from coupon where coupNum=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,coupon.getCoupNum());
		boolean flag=ptmt.execute();
		conn.close();
		return flag;
	}

}
