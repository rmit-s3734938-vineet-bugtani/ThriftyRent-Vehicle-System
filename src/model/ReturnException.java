package model;

public class ReturnException extends Exception {

	String message;

	public ReturnException() {
		super();
		this.message = "Vehicle cannot be returned!!";
	}

	@Override
	public String getMessage() {
		return message;
	}
}