package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBKudeatzaile {

	Connection conn;

	public void conOpen() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}

			String userName = "twitterUser";
			String password = "twitter";
			String url = "jdbc:mysql://localhost:3306/twittermysql";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = (Connection) DriverManager.getConnection(url, userName, password);
			System.out.println("Database connection established");
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
			e.printStackTrace();
		}
	}

	private ResultSet query(Statement s, String query) {

		ResultSet rs = null;

		try {
			s.executeQuery(query);
			rs = s.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	// singleton patroia
	private static DBKudeatzaile instantzia = new DBKudeatzaile();

	private DBKudeatzaile() {
		this.conOpen();
	}

	public static DBKudeatzaile getInstantzia() {
		return instantzia;
	}

	public ResultSet execSQL(String query) {
		int count = 0;
		Statement s = null;
		ResultSet rs = null;
		try {
			s = (Statement) conn.createStatement();
			if (query.toLowerCase().indexOf("select") == 0) {
				// select agindu bat
				rs = this.query(s, query);
			} else {
				// update, delete, create agindu bat
				count = s.executeUpdate(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}