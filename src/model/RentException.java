package model;

public class RentException extends Exception {

	String message;

	public RentException() {
		super();
		this.message = "Vehicle cannot be rented!!";
	}

	@Override
	public String getMessage() {
		return message;
	}
}
