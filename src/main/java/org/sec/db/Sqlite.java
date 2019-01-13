package org.sec.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlite {
	
	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:sec.db");
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("select * from test");
			if (r != null) {
				while (r.next()) {
					System.out.println(r.getInt(1) + "\t" + r.getString(2));
				}
				r.close();
			}
			s.close();
			c.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}

}
