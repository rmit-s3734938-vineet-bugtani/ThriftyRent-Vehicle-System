package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTable {
	DeleteTable() throws SQLException {

		final String DB_NAME = "testDB";

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			stmt.executeUpdate("DROP TABLE rentalrecords");
			stmt.executeUpdate("DROP TABLE vehicle");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
