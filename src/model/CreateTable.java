package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	CreateTable() throws SQLException {

		final String DB_NAME = "testDB";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			stmt.executeUpdate("CREATE TABLE vehicle (" + "id VARCHAR(8) NOT NULL," + "year INT NOT NULL,"
					+ "make VARCHAR(20) NOT NULL," + "model VARCHAR(20) NOT NULL,"
					+ "number_of_passengers INT NOT NULL," + "type VARCHAR(8) NOT NULL,"
					+ "status VARCHAR(20) NOT NULL," + "image_name VARCHAR(20)  NULL,"
					+ "last_maintainancedate VARCHAR(10) NULL," + " PRIMARY KEY (id))");

			stmt.executeUpdate("CREATE TABLE rentalrecords (" + "id VARCHAR(30) NOT NULL,"
					+ "rent_date VARCHAR(10) NOT NULL," + "estimated_returndate VARCHAR(10) NOT NULL,"
					+ "actual_returndate VARCHAR(10) NULL," + "rent_fee float(24) NULL," + "late_fee float(24) NULL,"

					+ "vehicle_id VARCHAR(8) NOT NULL FOREIGN KEY REFERENCES vehicle(id)," + "  PRIMARY KEY (id))");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
