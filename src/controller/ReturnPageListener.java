package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.DateTime;
import model.InvalidInputException;
import model.ReturnException;
import view.ErrorDialog;
import view.ReturnPage;
import view.SucessDialog;

public class ReturnPageListener implements EventHandler<ActionEvent> {

	private ReturnPage theView;

	public ReturnPageListener(ReturnPage theView) {
		this.theView = theView;
	}

	@Override
	public void handle(ActionEvent event) {

		try {
			// call return method of vehicle, if returned successfully show success
			theView.getVehicle().returnVehicle(DateTime.stringToDateTime(theView.getReturnDate()));
			new SucessDialog("Vehicle was returned successfully");

		} catch (Exception e) {
			// clear the form,if data entered was in valid format and vehicle could not be
			// returned show return exception else display invalid data entered message
			theView.setReturnDate("");

			if (!(e instanceof ReturnException))
				new ErrorDialog(new InvalidInputException().getMessage());
			else
				new ErrorDialog(e.getMessage());

		}

	}
}
