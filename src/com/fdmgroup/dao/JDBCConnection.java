package com.fdmgroup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	
	private static Connection connection;
	
	public static Connection getInstance() {
		if(connection == null) {
			initDrivers();
			connection = openConnection();
		} else
			try {
				if(connection.isClosed()) {
					closeConnection();
					connection = openConnection();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return connection;
	}
	
	public Connection getConnection() {
		try {
			if (connection.isValid(0)) {
				connection.close();
				connection = openConnection();
			}
			else {
				connection = openConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Here Error 549");
			e.printStackTrace();
		}
		return connection;
	}

	public static void initDrivers() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println("Here Error 546");
			e.printStackTrace();
		}
	}
	
	private static Connection openConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "TRAINEE1", "!QAZSE4");
			return conn;
		} catch (SQLException e) {
			System.out.println("Here Error 545");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection() {
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
