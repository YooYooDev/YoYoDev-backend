package com.yooyoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreTest {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = null;
		
		float f = 3.43f;
		Long l = (long) f;
		System.out.println(l);

		try {

			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yooyoo", "root",
					"tiger");
			System.out.println(connection);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

	}

}
