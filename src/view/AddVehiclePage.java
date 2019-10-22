package view;

import controller.AddVehiclePageListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddVehiclePage extends GridPane {

	TextField vehicleId = new TextField();
	TextField year = new TextField();
	TextField make = new TextField();
	TextField model = new TextField();
	TextField numberOfSeats = new TextField();

	public AddVehiclePage() {
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		this.setHgap(5.5);
		this.setVgap(35.5);
		this.setMaxSize(900, 900);

		Button add = new Button("Add");

		Label label = new Label();
		this.add(label, 11, 1);

		AddVehiclePageListener addvehiclePageListener = new AddVehiclePageListener(this);
		add.setOnAction(addvehiclePageListener);

		// Place nodes in the pane
		this.add(new Label("Enter the vehicle id:"), 7, 0);
		this.add(vehicleId, 11, 0);

		this.add(new Label("Enter the year:"), 7, 1);
		this.add(year, 11, 1);

		this.add(new Label("Enter the make:"), 7, 2);
		this.add(make, 11, 2);

		this.add(new Label("Enter the model:"), 7, 3);
		this.add(model, 11, 3);

		this.add(new Label("Enter the number of seats:"), 7, 4);
		this.add(numberOfSeats, 11, 4);

		this.add(add, 9, 5);

	}

	public String getVehicleId() {
		return this.vehicleId.getText();
	}

	public String getYear() {
		return this.year.getText();
	}

	public String getMake() {
		return this.make.getText();
	}

	public String getModel() {
		return this.model.getText();
	}

	public String getSeats() {
		return this.numberOfSeats.getText();
	}

}
