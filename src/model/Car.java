package model;

import java.io.FileNotFoundException;

import view.VehicleDetailsPage;

public class Car extends Vehicle {
	private float fourSeaterrate = 78;
	private float sevenSeaterrate = 113;
	private float lateRate = 1.25f;

	public Car(String vehicleId, int year, String make, String model, int numberOfPassengers, String imageName,
			String vehicleStatus) {
		super(vehicleId, year, make, model, numberOfPassengers, "Car", imageName, vehicleStatus);
	}

	public void completeMaintenance() throws CompleteMaintainanceException, FileNotFoundException {
		// condition to check whether vehicle is under maintenance
		if (this.vehicleStatus.equals("Under Maintainance")) {
			vehicleStatus = "Available";
			Main.getRoot().setCenter(new VehicleDetailsPage(this));
		} else {
			throw new CompleteMaintainanceException();
		}

	}

	// calculate fee by first calling rentDays() to determine number of days
	// returned late or early
	@Override
	public float calculateRentalFee() {
		this.rentDays();
		if (this.getNumberOfPassengers() == 4)
			return this.getNumberOfDaysRentedBeforeEstimate() * fourSeaterrate;
		else
			return this.getNumberOfDaysRentedBeforeEstimate() * sevenSeaterrate;
	}

	// calculate late fee by first calling rentDays() to determine number of days
	// returned late or early
	@Override
	public float calculateLateFee() {
		this.rentDays();
		if (this.getNumberOfPassengers() == 4)
			return (this.getNumberOfDaysRentedAfterEstimate() * fourSeaterrate * lateRate);
		else
			return (this.getNumberOfDaysRentedAfterEstimate() * sevenSeaterrate * lateRate);
	}

	@Override
	public String toString() {
		return getVehicleId() + ":" + getYear() + ":" + getMake() + ":" + getModel() + ":" + getNumberOfPassengers()
				+ ":" + getVehicleStatus() + ":" + getImageName();
	}
}