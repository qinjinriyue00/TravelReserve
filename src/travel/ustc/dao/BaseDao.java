package travel.ustc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {

	//protected DriverManager driver;
	private final String url = "jdbc:mysql://127.0.0.1:3306/TravelReserve?characterEncoding=utf8";
	private final String DBUser = "root";
	private final String DBPassword = "123";

	private static Connection conn = null;
	
	public BaseDao(){
		
	}

	
	public Connection openDBConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, DBUser, DBPassword);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public boolean closeDBConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	abstract Object query() throws SQLException;

	abstract boolean insert(Object obj) throws SQLException;

	abstract boolean update(Object obj) throws SQLException;

	abstract boolean delete(Object obj) throws SQLException;

}
