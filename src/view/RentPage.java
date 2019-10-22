package view;

import controller.RentPageListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Vehicle;

public class RentPage extends GridPane {

	private Vehicle vehicle;
	private TextField customerId = new TextField();
	private TextField rentDate = new TextField();
	private TextField numberOfDays = new TextField();

	public RentPage(Vehicle vehicle) {
		this.vehicle = vehicle;
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		this.setHgap(5.5);
		this.setVgap(35.5);

		// Place nodes in the pane
		this.add(new Label("Enter the customer id:"), 7, 0);
		this.add(customerId, 10, 0);

		this.add(new Label("Enter the rent date:"), 7, 1);
		this.add(rentDate, 10, 1);

		this.add(new Label("Enter the number of days:"), 7, 2);
		this.add(numberOfDays, 10, 2);

		Button rent = new Button("Rent");
		this.add(rent, 8, 3);

		RentPageListener r = new RentPageListener(this);
		rent.setOnAction(r);

	}

	public Vehicle getVan() {
		return vehicle;
	}

	public void setVan(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getCustomerId() {
		return this.customerId.getText();
	}

	public String getRentDate() {
		return this.rentDate.getText();
	}

	public String getNumberOfDays() {
		return this.numberOfDays.getText();
	}

	public void setCustomerId(String customerId) {
		this.customerId.setText(customerId);
	}

	public void setRentDate(String rentDate) {
		this.rentDate.setText(rentDate);
	}

	public void setNumberOfDays(String numberOfDays) {
		this.numberOfDays.setText(numberOfDays);
	}

}