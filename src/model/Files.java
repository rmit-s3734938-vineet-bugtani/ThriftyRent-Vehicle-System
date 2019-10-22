package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;

public class Files {

	public static void readFromFile(String fileName) {
		ThriftyRentSystem.getInstance().getVehicleList().clear();
		String dataArray[] = new String[10];
		Vehicle vehicle = null;
		File file = new File(fileName);

		try (Scanner input = new Scanner(file)) {
			while (input.hasNextLine()) {
				String data = input.nextLine();
				dataArray = data.split(":");
				// rental records line in text file consists of 6 strings separated by
				// delimiter. vehicle
				// record consists of 7 or 8 strings separated by delimiter. if length is not 6
				// make a vehicle object
				if (dataArray.length != 6) {
					if (vehicle != null)
						Collections.reverse(vehicle.getRentalRecordList());
					if (dataArray[0].contains("V")) {
						vehicle = new Van(dataArray[0], Integer.parseInt(dataArray[1]), dataArray[2], dataArray[3],
								Integer.parseInt(dataArray[4]), dataArray[7], dataArray[5]);
						if (!dataArray[6].equals("null")) {
							((Van) vehicle).setLastMaintenanceDate(DateTime.stringToDateTime(dataArray[6]));
						}
					} else {
						vehicle = new Car(dataArray[0], Integer.parseInt(dataArray[1]), dataArray[2], dataArray[3],
								Integer.parseInt(dataArray[4]), dataArray[6], dataArray[5]);

					}
					ThriftyRentSystem.getInstance().getVehicleList().add(vehicle);
				} else {

                    RentalRecord rentalRecord = null;
                    if (!dataArray[3].equals("none")) {
                        rentalRecord = new RentalRecord(dataArray[0], DateTime.stringToDateTime(dataArray[1]),
                                                        DateTime.stringToDateTime(dataArray[2]), DateTime.stringToDateTime(dataArray[3]),
                                                        Float.parseFloat(dataArray[4]), Float.parseFloat(dataArray[5]));
                    } else {
                        rentalRecord = new RentalRecord(dataArray[0], DateTime.stringToDateTime(dataArray[1]),
                                                        DateTime.stringToDateTime(dataArray[2]));
                        
                    }
                    
                    vehicle.getRentalRecordList().add(rentalRecord);
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void writeToFile(String directoryName) {
		File file = new File(directoryName + "/export_data.txt");
		try {
			PrintWriter output = new PrintWriter(file);
			for (int i = 0; i <= ThriftyRentSystem.getInstance().getVehicleList().size() - 1; i++) {
				output.write(ThriftyRentSystem.getInstance().getVehicleList().get(i).toString() + "\n");
				for (int j = ThriftyRentSystem.getInstance().getVehicleList().get(i).getRentalRecordList().size()
						- 1; j >= 0; j--) {
					if (ThriftyRentSystem.getInstance().getVehicleList().get(i).getRentalRecordList().get(j) != null)
						output.write(ThriftyRentSystem.getInstance().getVehicleList().get(i).getRentalRecordList()
								.get(j).toString() + "\n");
				}
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}
}
