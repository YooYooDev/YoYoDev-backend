package com.yooyoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreTest {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://yooyoo.cx79krmljcfu.us-east-2.rds.amazonaws.com:5432/YooYoo", "YooYoo",
					"YOOYOO_123");
			System.out.println(connection);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

	}

}
