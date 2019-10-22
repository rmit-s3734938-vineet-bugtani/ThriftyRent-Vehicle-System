package model;

public class InvalidIdException extends Exception {

	String message;

	public InvalidIdException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}