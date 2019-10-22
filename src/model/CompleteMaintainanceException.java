package model;

public class CompleteMaintainanceException extends Exception {

	String message;

	public CompleteMaintainanceException() {
		super();
		this.message = "Vehicle is not under maintainance!!";
	}

	@Override
	public String getMessage() {
		return message;
	}
}
