package kr.co.ican.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetDBConn {
	

	private GetDBConn(){}
	
	static{
		
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("Driver Loading Sucoess!!!");
			}catch(ClassNotFoundException e){
				System.out.println("Driver Loading fail");
				System.out.println(e.getMessage());
			} 
	 }
	
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
					
		String user = "ican";
		String passwd = "ican";

		conn = DriverManager.getConnection(url, user, passwd);
		System.out.println("getConnection");
		return conn;
	}

	public static void close(Connection conn, Statement psmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.getStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.getStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.getStackTrace();
			}
		}
	}
}
