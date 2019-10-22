package model;

import java.io.FileNotFoundException;

import view.VehicleDetailsPage;

public class Van extends Vehicle {
	private DateTime lastMaintenanceDate;
	private float rentalRate = 235;
	private float lateRentalRate = 299;

	public DateTime getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}

	public void setLastMaintenanceDate(DateTime lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}

	public void completeMaintenance(DateTime date) throws CompleteMaintainanceException, FileNotFoundException {
		// condition to check whether vehicle is under maintenance
		if (this.vehicleStatus.equals("Under Maintainance")) {

			// setting last maintenance date for a van
			this.setLastMaintenanceDate(date);
			this.vehicleStatus = "Available";
			Main.getRoot().setCenter(new VehicleDetailsPage(this));
		} else {
			throw new CompleteMaintainanceException();
		}

	}

	public Van(String vehicleId, int year, String make, String model, int numberOfPassengers, String imageName,
			String vehicleStatus) {
		// DateTime lastMaintenanceDate) {
		super(vehicleId, year, make, model, numberOfPassengers, "Van", imageName, vehicleStatus);
		// this.lastMaintenanceDate = lastMaintenanceDate;
	}

	// calculate fee by first calling rentDays() to determine number of days
	// returned late or early
	@Override
	public float calculateRentalFee() {
		rentDays();
		return this.getNumberOfDaysRentedBeforeEstimate() * rentalRate;
	}

	// calculate fee by first calling rentDays() to determine number of days
	// returned late or early
	@Override
	public float calculateLateFee() {
		rentDays();
		return (this.getNumberOfDaysRentedAfterEstimate() * lateRentalRate);
	}

	@Override
	public String toString() {
		return getVehicleId() + ":" + getYear() + ":" + getMake() + ":" + getModel() + ":" + getNumberOfPassengers()
				+ ":" + getVehicleStatus() + ":" + getLastMaintenanceDate() + ":" + getImageName();
	}
}