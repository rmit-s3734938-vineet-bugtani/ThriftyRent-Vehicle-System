package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.InValidNumberOfSeatsException;
import model.InvalidIdException;
import model.InvalidInputException;
import model.ThriftyRentSystem;
import view.AddVehiclePage;
import view.ErrorDialog;
import view.SucessDialog;

public class AddVehiclePageListener implements EventHandler<ActionEvent> {

	private ThriftyRentSystem theModel = ThriftyRentSystem.getInstance();
	private AddVehiclePage theView;

	public AddVehiclePageListener(AddVehiclePage theView) {
		
		this.theView = theView;

	}

	@Override
	public void handle(ActionEvent event) {
		try {
			// adding vehicle on press of add button and displaying success message if added
			// successfully
			theModel.addVehicle(this.theView.getVehicleId(), Integer.parseInt(this.theView.getYear()),
					this.theView.getMake(), this.theView.getModel(), Integer.parseInt(this.theView.getSeats()));
			new SucessDialog("Vehicle added successfully");
		} catch (Exception e) {
			if (!(e instanceof InvalidIdException || e instanceof InValidNumberOfSeatsException))
				new ErrorDialog(new InvalidInputException().getMessage());
			else
				new ErrorDialog(e.getMessage());

		}

	}
}