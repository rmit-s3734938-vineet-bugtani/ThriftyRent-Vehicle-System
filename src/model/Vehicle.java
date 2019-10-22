package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import view.VehicleDetailsPage;

public abstract class Vehicle {
	private String vehicleId;
	private int year;
	private String make;
	private String model;
	private int numberOfPassengers;
	private String vehicleType;
	protected String vehicleStatus;
	private boolean printOnlyLatestRecord;
	private int numberOfDaysRentedBeforeEstimate;
	private int numberOfDaysRentedAfterEstimate;
	private String imageName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	ArrayList<RentalRecord> rentalRecordList = new ArrayList<>();
	Scanner in;

	public ArrayList<RentalRecord> getRentalRecordList() {
		return rentalRecordList;
	}

	public void setRentalRecordList(ArrayList<RentalRecord> rentalRecordList) {
		this.rentalRecordList = rentalRecordList;
	}

	protected Vehicle(String vehicleId, int year, String make, String model, int numberOfPassengers, String vehicleType,
			String imageName, String vehicleStatus) {
		this.vehicleId = vehicleId;
		this.year = year;
		this.make = make;
		this.model = model;
		this.numberOfPassengers = numberOfPassengers;
		this.vehicleType = vehicleType;
		this.imageName = imageName;
		this.vehicleStatus = vehicleStatus;
	}

	public boolean isPrintOnlyLatestRecord() {
		return printOnlyLatestRecord;
	}

	public void setPrintOnlyLatestRecord(boolean printOnlyLatestRecord) {
		this.printOnlyLatestRecord = printOnlyLatestRecord;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public int getNumberOfDaysRentedBeforeEstimate() {
		return numberOfDaysRentedBeforeEstimate;
	}

	public void setNumberOfDaysRentedBeforeEstimate(int numberOfNoOfDaysRentedBeforeEstimate) {
		this.numberOfDaysRentedBeforeEstimate = numberOfNoOfDaysRentedBeforeEstimate;
	}

	public int getNumberOfDaysRentedAfterEstimate() {
		return numberOfDaysRentedAfterEstimate;
	}

	public void setNumberOfDaysRentedAfterEstimate(int numberOfNoOfDaysRentedAfterEstimate) {
		this.numberOfDaysRentedAfterEstimate = numberOfNoOfDaysRentedAfterEstimate;
	}

	public abstract float calculateRentalFee();

	public abstract float calculateLateFee();

	@Override
	public abstract String toString();

	public void performMaintenance() throws PerformMaintainanceException, FileNotFoundException {
		// condition to check whether vehicle is not rented
		if (this.vehicleStatus.equals("Available")) {
			this.vehicleStatus = "Under Maintainance";
			Main.getRoot().setCenter(new VehicleDetailsPage(this));
		} else {
			throw new PerformMaintainanceException();
		}

	}

	public void rent(String customerId, DateTime date, int numberOfDays)
			throws RentException, InvalidInputException, FileNotFoundException {
		boolean canRent;
		DateTime estimatedReturnDate = DateTime.getEstimatedReturnDate(date.toString(), numberOfDays);
		if (this.vehicleStatus.equals("Available")) {
			if (this instanceof Van) {
				int check = 0;
				if (((Van) this).getLastMaintenanceDate() != null) {
					DateTime nextMaintainanceDate = DateTime
							.getEstimatedReturnDate(((Van) this).getLastMaintenanceDate().toString(), 12);
					// checking if rental period exceeds date on which van needs to go under
					// maintenance
					check = DateTime.diffDays(estimatedReturnDate, nextMaintainanceDate);

				}
				// checking if number of days to be rented is greater than one
				if (numberOfDays >= 1 && check <= 0)
					canRent = true;
				else
					canRent = false;
			} else {
				// condition checks for a car
				String dayOfTheWeek = date.getNameOfDay();
				if (((dayOfTheWeek.equals("Friday") || dayOfTheWeek.equals("Saturday")) && numberOfDays >= 3
						&& numberOfDays <= 14)
						|| ((dayOfTheWeek.equals("Sunday") || dayOfTheWeek.equals("Monday")
								|| dayOfTheWeek.equals("Tuesday") || dayOfTheWeek.equals("Wednesday")
								|| dayOfTheWeek.equals("Thursday")) && numberOfDays >= 2 && numberOfDays <= 14))
					canRent = true;
				else
					canRent = false;
			}
		} else {
			canRent = false;
		}

		// creating a new rental record, updating status after condition check
		if (canRent) {
			String recordId = this.vehicleId + "_" + customerId + "_" + date.getEightDigitDate();
			this.vehicleStatus = "Rented";
			this.getRentalRecordList().add(new RentalRecord(recordId, date, estimatedReturnDate));// addded
			Main.getRoot().setCenter(new VehicleDetailsPage(this));

		} else {
			throw new RentException();
		}
	}

	// segregating the number of days the vehicle was rented into days before and
	// after the estimated return date to ease the calculation of rental and late
	// fees

	public void rentDays() {
		// checking if vehicle was returned late
		if (DateTime.diffDays(
				this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).getActualReturnDate(),
				this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).getEstimatedReturnDate()) > 0) {
			numberOfDaysRentedBeforeEstimate = DateTime.diffDays(
					this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).getEstimatedReturnDate(),
					this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).getRentDate());
			numberOfDaysRentedAfterEstimate = DateTime.diffDays(
					this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).getActualReturnDate(),
					this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).getEstimatedReturnDate());
		} else {
			// loop is entered if vehicle was returned before estimated date
			numberOfDaysRentedBeforeEstimate = DateTime.diffDays(
					this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).getActualReturnDate(),
					this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).getRentDate());
			numberOfDaysRentedAfterEstimate = 0;
		}
	}

	public void returnVehicle(DateTime date) throws ReturnException, FileNotFoundException {
		float fees = 0;
		float lateFees = 0;
		// condition to check if vehicle is rented
		if (this.getVehicleStatus().equals("Rented") &&
		// check to verify return date entered by the user is greater than the rent date
				(DateTime.diffDays(date,
						this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).getRentDate()) > 0)) {
			// updating the latest rental record with actual return date and fees, updating
			// status after condition check
			this.vehicleStatus = "Available";
			this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).setActualReturnDate(date);
			if (this instanceof Car) {
				fees = ((Car) this).calculateRentalFee();
				lateFees = ((Car) this).calculateLateFee();
			} else {
				fees = ((Van) this).calculateRentalFee();
				lateFees = ((Van) this).calculateLateFee();
			}
			this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).setRentFee(fees);
			this.getRentalRecordList().get(this.getRentalRecordList().size() - 1).setLateFee(lateFees);

			Main.getRoot().setCenter(new VehicleDetailsPage(this));

		} else {
			throw new ReturnException();
		}

	}

}