package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQuery {
	
	private final String DB_NAME = "testDB";

	SelectQuery() {

		int i = 0;

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {

			String query = "SELECT * FROM Vehicle";

			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					if (resultSet.getString("type").equals("Van")) {
						Van van = new Van(resultSet.getString("id"), resultSet.getInt("year"),
								resultSet.getString("make"), resultSet.getString("model"),
								resultSet.getInt("number_of_passengers"), resultSet.getString("image_name"),
								resultSet.getString("status"));
						if (resultSet.getString("last_maintainancedate") != null)
							van.setLastMaintenanceDate(
									DateTime.stringToDateTime(resultSet.getString("last_maintainancedate")));
						ThriftyRentSystem.getInstance().getVehicleList().add(van);
					} else {
						Car car = new Car(resultSet.getString("id"), resultSet.getInt("year"),
								resultSet.getString("make"), resultSet.getString("model"),
								resultSet.getInt("number_of_passengers"), resultSet.getString("image_name"),
								resultSet.getString("status"));
						ThriftyRentSystem.getInstance().getVehicleList().add(car);

					}

					String rentalQuery = "SELECT * FROM rentalrecords where vehicle_id='" + resultSet.getString("id") + "'";
					try (ResultSet resultSet1 = stmt.executeQuery(rentalQuery)) {
						while (resultSet1.next()) {
							if (resultSet1.getString("actual_returndate") == null) {

								RentalRecord rentalRecord = new RentalRecord(resultSet1.getString("id"),
										DateTime.stringToDateTime(resultSet1.getString("rent_date")),
										DateTime.stringToDateTime(resultSet1.getString("estimated_returndate")));
								ThriftyRentSystem.getInstance().getVehicleList().get(i).getRentalRecordList()
										.add(rentalRecord);

							} else {

								RentalRecord rentalRecord = new RentalRecord(resultSet1.getString("id"),
										DateTime.stringToDateTime(resultSet1.getString("rent_date")),
										DateTime.stringToDateTime(resultSet1.getString("estimated_returndate")),
										DateTime.stringToDateTime(resultSet1.getString("actual_returndate")),
										resultSet1.getFloat("rent_fee"), resultSet1.getFloat("late_fee"));
								ThriftyRentSystem.getInstance().getVehicleList().get(i).getRentalRecordList()
										.add(rentalRecord);

							}
						}

						i++;
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}

				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
