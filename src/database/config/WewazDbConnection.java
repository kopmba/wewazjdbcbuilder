package database.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class WewazDbConnection {
	
	private static Connection connection;
	
	public WewazDbConnection createConnection(String url, String username, String password) {
		try {
			//Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return this;
		
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
