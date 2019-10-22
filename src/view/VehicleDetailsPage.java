package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.VehicleDetailsPageListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Van;
import model.Vehicle;

public class VehicleDetailsPage extends BorderPane {

	private Vehicle vehicle;
	private GridPane grid;
	private Button rentVehicle = new Button("Rent Vehicle");
	private Button returnVehicle = new Button("Return Vehicle");
	private Button maintainVehicle = new Button("Maintain Vehicle");
	private Button completeVehicle = new Button("Complete Maintainance");

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public VehicleDetailsPage(Vehicle s) throws FileNotFoundException {
		this.vehicle = s;
		Dialog dialog = new Dialog();
		dialog.setResizable(false);
		ButtonType buttonTypeOk = new ButtonType("Close", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

		grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		grid.setHgap(5);
		grid.setVgap(20);
		grid.setMaxSize(2000, 2000);

		VehicleDetailsPageListener handler = new VehicleDetailsPageListener(this);

		rentVehicle.setOnAction(handler);
		returnVehicle.setOnAction(handler);
		maintainVehicle.setOnAction(handler);
		completeVehicle.setOnAction(handler);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(rentVehicle, returnVehicle, maintainVehicle, completeVehicle);
		vbox.setSpacing(60);
		vbox.setPadding(new Insets(150, 0, 0, 40));

		this.setLeft(vbox);

		FileInputStream input;
		// if vehicle does not have a image database and text file store "no image" in
		// image attribute.
		// if image attribute contains "no image" display a no image available logo
		if (s.getImageName().equals("no image")) {
			input = new FileInputStream("images/no image.jpg");
		} else {
			input = new FileInputStream("images/" + s.getImageName() + ".jpg");

		}

		Image image = new Image(input);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(250);
		imageView.setFitWidth(350);
		grid.add(imageView, 20, 0);

		grid.add(new Label(s.getModel() + " " + s.getMake()), 20, 1);
		grid.add(new Label("Vehicle Type: " + s.getVehicleType()), 20, 2);
		grid.add(new Label("Year manufactured: " + s.getYear()), 20, 3);
		grid.add(new Label("Number of seats: " + s.getNumberOfPassengers()), 20, 4);

		// show last maintenance date on page only if not null
		grid.add(new Label("Status: " + s.getVehicleStatus()), 20, 5);
		if (s instanceof Van && ((Van) s).getLastMaintenanceDate() != null) {
			grid.add(new Label("Last Maintenance Date: " + ((Van) s).getLastMaintenanceDate()), 20, 6);
			grid.add(new Label("Rental Records:"), 20, 7);
		} else
			grid.add(new Label("Rental Records:"), 20, 6);

		VBox v = new VBox();

		if (s.getRentalRecordList().size() == 0) {
			v.getChildren().addAll(new Label("Empty!"));
		} else {
			for (int i = s.getRentalRecordList().size() - 1; i >= 0; i--) {
				if (s.getRentalRecordList().get(i) != null) {
					GridPane newGrid = new GridPane();
					newGrid.add(new Label("Record Id: " + s.getRentalRecordList().get(i).getRecordId()), 20, 1);
					newGrid.add(new Label("Rent Date: " + s.getRentalRecordList().get(i).getRentDate()), 20, 2);
					newGrid.add(
							new Label(
									"Estimated Return Date:" + s.getRentalRecordList().get(i).getEstimatedReturnDate()),
							20, 3);
					newGrid.add(new Label(" "), 20, 4);
					// show complete rental record only if vehicle is returned i.e. has a actual
					// return date
					if (s.getRentalRecordList().get(i).getActualReturnDate() != null) {
						newGrid.add(
								new Label("Actual Return Date:" + s.getRentalRecordList().get(i).getActualReturnDate()),
								20, 4);
						newGrid.add(new Label("Rent Fees:" + s.getRentalRecordList().get(i).getRentFee()), 20, 5);
						newGrid.add(new Label("Late Fees:" + s.getRentalRecordList().get(i).getLateFee()), 20, 6);
						newGrid.add(new Label(""), 20, 7);
					}
					v.getChildren().addAll(newGrid);
				}
			}
		}

		ScrollPane scrollPane = new ScrollPane(v);
		scrollPane.setFitToWidth(true);
		scrollPane.setStyle("-fx-background-color:transparent;");
		grid.add(scrollPane, 20, 7);
		this.setCenter(grid);
	}
}
