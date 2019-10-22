package controller;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Main;
import model.Vehicle;
import view.VehicleDetailsPage;

public class VehicleInfoListener implements EventHandler<ActionEvent> {

	private Vehicle s;

	public VehicleInfoListener(Vehicle vehicle) {
		this.s = vehicle;
	}

	@Override
	public void handle(ActionEvent event) {

		VehicleDetailsPage vehicleDetailsPage = null;
		try {
			// display vehicle details page on click of view button in the list view on
			// landing page
			vehicleDetailsPage = new VehicleDetailsPage(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Main.getRoot().setCenter(vehicleDetailsPage);

	}
}