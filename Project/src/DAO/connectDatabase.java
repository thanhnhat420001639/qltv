package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDatabase {
	public static Connection getMySQLConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/qltv";
		String user= "root";
		String pw = "nhat123";
		try {
			Connection conn = DriverManager.getConnection(url,user,pw);
			return conn;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
