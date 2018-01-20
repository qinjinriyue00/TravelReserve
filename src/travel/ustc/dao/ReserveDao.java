package travel.ustc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import travel.ustc.bean.Reserve;

public class ReserveDao extends BaseDao {

	public ReserveDao(String url, String dBUser, String dBPassword) {
		super();
		this.url = url;
		this.DBUser = dBUser;
		this.DBPassword = dBPassword;
	}
	
	@Override
	public Object query() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=openDBConnection();
		String sql="select * from Reserve";
		PreparedStatement pState=conn.prepareStatement(sql);
		ResultSet rs=pState.executeQuery();
		List<Reserve> list=new ArrayList<Reserve>();
		while(rs.next()){
			Reserve reserve=new Reserve();
			reserve.setCustName(rs.getString("custName"));
			reserve.setResvType(rs.getShort("resvType"));
			reserve.setResvKey(rs.getString("resvKey"));
			list.add(reserve);
		}
		conn.close();
		return list;
	}
	
	public Object query(String custName) throws SQLException{
		Connection conn=openDBConnection();
		String sql="select * from Reserve where custName=?";
		PreparedStatement pState=conn.prepareStatement(sql);
		pState.setString(1, custName);
		ResultSet rs=pState.executeQuery();
		List<Reserve> list=new ArrayList<Reserve>();
		while(rs.next()){
			Reserve reserve=new Reserve();
			reserve.setCustName(rs.getString("custName"));
			reserve.setResvType(rs.getShort("resvType"));
			reserve.setResvKey(rs.getString("resvKey"));
			list.add(reserve);
		}
		conn.close();
		return list;
	}

	@Override
	public boolean insert(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		Reserve reserve=(Reserve)obj;
		Connection conn=openDBConnection();
		String sql="insert into Reserve values(?,?,?)";
		PreparedStatement pState=conn.prepareStatement(sql);
		pState.setString(1, reserve.getCustName());
		pState.setInt(2, reserve.getResvType());
		System.out.println("ReserveDao.insert:"+reserve.getResvKey());
		pState.setString(3, reserve.getResvKey());
		int flag=pState.executeUpdate();
		System.out.println("ReserveDao.insert:"+flag);
		if(flag>0)
			return true;
		else return false;
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
