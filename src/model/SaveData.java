package model;

import java.sql.Connection;
import java.sql.Statement;

public class SaveData {

	public SaveData() {
		// deleting existing data in database and reinserting data from arrays
		try (Connection con = ConnectionTest.getConnection("testDB"); Statement stmt = con.createStatement();) {
			stmt.executeUpdate("Delete from  rentalrecords");
			stmt.executeUpdate("Delete from vehicle");
			for (int i = 0; i <= ThriftyRentSystem.getInstance().getVehicleList().size() - 1; i++) {
				String query = "insert into vehicle values('"
						+ ThriftyRentSystem.getInstance().getVehicleList().get(i).getVehicleId() + "',"
						+ ThriftyRentSystem.getInstance().getVehicleList().get(i).getYear() + ",'"
						+ ThriftyRentSystem.getInstance().getVehicleList().get(i).getMake() + "','"
						+ ThriftyRentSystem.getInstance().getVehicleList().get(i).getModel() + "',"
						+ ThriftyRentSystem.getInstance().getVehicleList().get(i).getNumberOfPassengers() + ",'"
						+ ThriftyRentSystem.getInstance().getVehicleList().get(i).getClass().getSimpleName() + "', '"
						+ ThriftyRentSystem.getInstance().getVehicleList().get(i).getVehicleStatus() + "', '"
						+ ThriftyRentSystem.getInstance().getVehicleList().get(i).getImageName() + "',NULL)";
				stmt.executeUpdate(query);

				// if vehicle is a van and has a last maintenance date update record with the
				// date
				if (ThriftyRentSystem.getInstance().getVehicleList().get(i) instanceof Van) {
					if (((Van) ThriftyRentSystem.getInstance().getVehicleList().get(i))
							.getLastMaintenanceDate() != null) {
						String query1 = "update vehicle set last_maintainancedate='"
								+ ((Van) ThriftyRentSystem.getInstance().getVehicleList().get(i))
										.getLastMaintenanceDate().toString()
								+ "' where id='"
								+ ThriftyRentSystem.getInstance().getVehicleList().get(i).getVehicleId() + "'";
						stmt.executeUpdate(query1);
					}
				}
				for (int j = 0; j <= ThriftyRentSystem.getInstance().getVehicleList().get(i).getRentalRecordList()
						.size() - 1; j++) {
					Vehicle v = ThriftyRentSystem.getInstance().getVehicleList().get(i);
					if (v.getRentalRecordList().get(j) != null) {
						if (v.getRentalRecordList().get(j).getActualReturnDate() != null) {
							String query1 = "insert into rentalrecords values('"
									+ v.getRentalRecordList().get(j).getRecordId() + "','"
									+ v.getRentalRecordList().get(j).getRentDate().toString() + "','"
									+ v.getRentalRecordList().get(j).getEstimatedReturnDate().toString() + "','"
									+ v.getRentalRecordList().get(j).getActualReturnDate().toString() + "',"
									+ v.getRentalRecordList().get(j).getRentFee() + ","
									+ v.getRentalRecordList().get(j).getLateFee() + ",'" + v.getVehicleId() + "')";
							stmt.executeUpdate(query1);
						} else {
							String query1 = "insert into rentalrecords values('"
									+ v.getRentalRecordList().get(j).getRecordId() + "','"
									+ v.getRentalRecordList().get(j).getRentDate().toString() + "','"
									+ v.getRentalRecordList().get(j).getEstimatedReturnDate().toString()
									+ "',null,null,null,'" + v.getVehicleId() + "')";
							stmt.executeUpdate(query1);

						}
					}
				}
			}
			con.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
