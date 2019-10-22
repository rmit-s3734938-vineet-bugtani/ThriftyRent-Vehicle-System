package view;

import controller.ReturnPageListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Vehicle;

public class ReturnPage extends GridPane {

	private Vehicle vehicle;
	private TextField returnDate = new TextField();

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ReturnPage(Vehicle vehicle) {
		this.setAlignment(Pos.CENTER);
		this.vehicle = vehicle;
		this.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		this.setHgap(5.5);
		this.setVgap(35.5);

		this.add(new Label("Enter the return date:"), 7, 1);
		this.add(returnDate, 10, 1);

		Button returnVehicle = new Button("Return");
		this.add(returnVehicle, 8, 3);

		ReturnPageListener r = new ReturnPageListener(this);
		returnVehicle.setOnAction(r);

	}

	public void setReturnDate(String returnDate) {
		this.returnDate.setText(returnDate);
	}

	public String getReturnDate() {
		return this.returnDate.getText();
	}
}