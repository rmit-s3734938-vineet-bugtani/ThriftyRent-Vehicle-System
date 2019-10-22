package model;

public class PerformMaintainanceException extends Exception {

	String message;

	public PerformMaintainanceException() {
		super();
		this.message = "Vehicle cannot go under maintainance!!";
	}

	@Override
	public String getMessage() {
		return message;
	}
}