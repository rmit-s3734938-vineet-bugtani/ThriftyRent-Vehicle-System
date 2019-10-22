
package model;

public class InValidNumberOfSeatsException extends Exception {

	String message;

	public InValidNumberOfSeatsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}