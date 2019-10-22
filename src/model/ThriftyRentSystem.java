package model;

import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ThriftyRentSystem {

	private static ThriftyRentSystem single_instance = null;

	private ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();
	Scanner in;

	public ObservableList<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(ObservableList<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	// logic for making the class a singleton
	public static ThriftyRentSystem getInstance() {
		if (single_instance == null) {
			single_instance = new ThriftyRentSystem();
		}
		return single_instance;
	}

	private ThriftyRentSystem() {

	}

	public void addVehicle(String vehicleId, int year, String make, String model, int numberOfSeats)
			throws InValidNumberOfSeatsException, InvalidIdException {

		// not allowing vehicle id as just C_ or V_ .Should be more than 2 characters
		// but should contain either C_ or V_
		if (!((vehicleId.contains("V")) || (vehicleId.contains("C"))) || (vehicleId.length() < 1)
				|| (vehicleId.length() == 1)) {
			throw new InvalidIdException("Vehicle id should be of format Cnumber for car and Vnumber for Van");
		}

		for (int i = 0; i <= vehicleList.size() - 1; i++) {
			if (vehicleList.get(i) != null)
				if (vehicleList.get(i).getVehicleId().equals(vehicleId))
					throw new InvalidIdException("Vehicle id already exists");
		}

		// checking number of seats condition for a car
		if (!(numberOfSeats == 4 || numberOfSeats == 7) && vehicleId.contains("C")) {
			throw new InValidNumberOfSeatsException("A car can have either 4 or 7 passenger seats.");

			// checking number of seats condition for a van
		}

		if (!(numberOfSeats == 15) && vehicleId.contains("V")) {
			throw new InValidNumberOfSeatsException("A van always has 15 passenger seats.");

		}
		if (vehicleId.contains("V")) {

			vehicleList.add(new Van(vehicleId, year, make, model, numberOfSeats, "no image", "Available"));
		} else
			vehicleList.add(new Car(vehicleId, year, make, model, numberOfSeats, "no image", "Available"));

	}
}