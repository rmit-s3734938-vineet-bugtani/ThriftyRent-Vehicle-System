package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.DateTime;
import model.InvalidInputException;
import model.RentException;
import view.ErrorDialog;
import view.RentPage;
import view.SucessDialog;

public class RentPageListener implements EventHandler<ActionEvent> {

	private RentPage theView;

	public RentPageListener(RentPage theView) {
		this.theView = theView;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			// call rent method of vehicle, if rented successfully show success
			theView.getVan().rent(theView.getCustomerId(), DateTime.stringToDateTime(theView.getRentDate()),
					Integer.parseInt(theView.getNumberOfDays()));
			new SucessDialog("Vehicle was rented successfully");
		} catch (Exception e) {
			// clear the form,if data entered was in valid format and vehicle could not be
			// rented show rent exception else display invalid data entered message
			theView.setCustomerId("");
			theView.setRentDate("");
			theView.setNumberOfDays("");
			if (!(e instanceof RentException))
				new ErrorDialog(new InvalidInputException().getMessage());
			else
				new ErrorDialog(e.getMessage());
		}
	}
}
