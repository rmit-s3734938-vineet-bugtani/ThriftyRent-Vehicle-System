package view;

import controller.CompleteMaintainancePageListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Vehicle;

public class CompleteMaintainancePage extends GridPane {
	private TextField maintainanceCompletionDate = new TextField();
	private Vehicle vehicle;

	public CompleteMaintainancePage(Vehicle vehicle) {
		this.vehicle = vehicle;

		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		this.setHgap(5.5);
		this.setVgap(35.5);

		// Place nodes in the pane

		this.add(new Label("Enter the maintainance completion date:"), 6, 1);
		this.add(maintainanceCompletionDate, 9, 1);

		Button maintainanceCompletion = new Button("Complete Maintainance");
		this.add(maintainanceCompletion, 8, 3);

		CompleteMaintainancePageListener c = new CompleteMaintainancePageListener(this);
		maintainanceCompletion.setOnAction(c);

	}

	public String getMaintainanceCompletionDateId() {
		return this.maintainanceCompletionDate.getText();
	}

	public void setMaintainanceCompletionDateId(String maintainanceCompletionDate) {
		this.maintainanceCompletionDate.setText(maintainanceCompletionDate);
	}

	public Vehicle getVan() {
		return vehicle;
	}

	public void setVan(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
