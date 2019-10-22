package controller;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import model.Car;
import model.CompleteMaintainanceException;
import model.Main;
import model.PerformMaintainanceException;
import model.Van;
import model.Vehicle;
import view.AddVehiclePage;
import view.CompleteMaintainancePage;
import view.ErrorDialog;
import view.RentPage;
import view.ReturnPage;
import view.SucessDialog;
import view.VehicleDetailsPage;

public class VehicleDetailsPageListener implements EventHandler<ActionEvent> {

	Dialog dialog = new Dialog();

	private Vehicle vehicle;

	public VehicleDetailsPageListener(VehicleDetailsPage theView) {

		this.vehicle = theView.getVehicle();

		dialog.setResizable(false);
		ButtonType buttonTypeOk = new ButtonType("Close", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
	}

	@Override
	public void handle(ActionEvent event) {

		Button button = (Button) event.getSource();
		String buttonName = button.getText();

		switch (buttonName) {
		case "Rent Vehicle":
			// display rent vehicle page
			RentPage rentPage = new RentPage(vehicle);
			dialog.getDialogPane().setContent(rentPage);
			dialog.showAndWait();
			break;
		case "Return Vehicle":
			// display return vehicle page
			ReturnPage returnPage = new ReturnPage(vehicle);
			dialog.getDialogPane().setContent(returnPage);
			dialog.showAndWait();
			break;
		case "Complete Maintainance":
			// if vehicle is a van display complete maintenance page for user to input
			// maintenance completion date
			if (vehicle instanceof Van) {
				CompleteMaintainancePage completeMaintainancePage = new CompleteMaintainancePage(vehicle);
				dialog.getDialogPane().setContent(completeMaintainancePage);
				dialog.showAndWait();
			} else {
				try {
					// if vehicle is car call complete maintenance method of car, if successful
					// display success message
					((Car) vehicle).completeMaintenance();
					new SucessDialog("Maintainance completed");
				} catch (CompleteMaintainanceException | FileNotFoundException e) {
					new ErrorDialog(new CompleteMaintainanceException().getMessage());
				}
			}
			break;
		case "Maintain Vehicle":
			try {
				// call perform maintenance method of vehicle, if successful display success
				// message
				this.vehicle.performMaintenance();
				new SucessDialog("Vehicle is now under maintainance");
			} catch (PerformMaintainanceException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				new ErrorDialog(new PerformMaintainanceException().getMessage());

			}
			break;
		case "Add Vehicle":
			Main.getRoot().setCenter(new AddVehiclePage());
			break;
		}
	}

}
