package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.VehicleInfoListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Vehicle;

public class VehicleInfo extends ListCell<Vehicle> {

	private BorderPane b = new BorderPane();
	private Vehicle v;

	public BorderPane getB() {
		return b;
	}

	public void setB(BorderPane b) {
		this.b = b;
	}

	public Vehicle getV() {
		return v;
	}

	public void setV(Vehicle v) {
		this.v = v;
	}

	// logic to convert image to image view to display
	private ImageView imageToImageView(String imageName) {

		FileInputStream file = null;
		try {
			file = new FileInputStream(imageName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Image iconImage = new Image(file);
		return new ImageView(iconImage);
	}

	@Override
	public void updateItem(Vehicle vehicle, boolean empty) {
		super.updateItem(vehicle, empty);
		if (vehicle == null || empty) {
			setGraphic(null);
		} else {

			this.v = vehicle;
			VehicleInfoListener handler = new VehicleInfoListener(vehicle);

			b.setMaxSize(900, 300);
			b.setStyle("-fx-border-color: black");

			ImageView imageView;
			if (vehicle.getImageName().equals("no image")) {
				imageView = imageToImageView("images/no image.jpg");
			} else {
				imageView = imageToImageView("images/" + vehicle.getImageName() + ".jpg");
			}
			imageView.setFitHeight(200);
			imageView.setFitWidth(300);
			b.setLeft(imageView);

			Button bookButton = new Button("View");
			bookButton.setOnAction(handler);
			b.setRight(bookButton);
			BorderPane.setAlignment(bookButton, Pos.CENTER);

			VBox v = new VBox();

			Label l = new Label(vehicle.getModel() + " " + vehicle.getMake());
			l.setStyle("-fx-font: 20 arial;");

			HBox h = new HBox();

			String[] imageNames = new String[3];
			// names of icons displayed in list view on landing page
			imageNames[0] = "images/seats.jpg";
			imageNames[1] = "images/calender.jpg";
			imageNames[2] = "images/type.jpg";

			ImageView[] images = new ImageView[3];

			// converting to image views
			for (int i = 0; i <= 2; i++) {
				images[i] = imageToImageView(imageNames[i]);
				images[i].setFitHeight(40);
				images[i].setFitWidth(40);
			}

			String seats = Integer.toString(vehicle.getNumberOfPassengers());

			Label seatsLabel = new Label((": " + seats));
			seatsLabel.setStyle("-fx-font: 15 arial;");
			seatsLabel.setPadding(new Insets(15, 0, 00, 00));

			String date = Integer.toString(vehicle.getYear());

			Label dateLabel = new Label((": " + date));
			dateLabel.setStyle("-fx-font: 15 arial;");
			dateLabel.setPadding(new Insets(15, 0, 00, 00));

			String type = vehicle.getVehicleType();

			Label typeLabel = new Label((": " + type));
			typeLabel.setStyle("-fx-font: 15 arial;");
			typeLabel.setPadding(new Insets(15, 0, 00, 00));

			h.getChildren().addAll(images[0], seatsLabel, images[1], dateLabel, images[2], typeLabel);
			h.setSpacing(20);

			v.getChildren().addAll(l, h);
			v.setSpacing(40);
			v.setPadding(new Insets(10, 0, 10, 10));

			b.setCenter(v);
			setGraphic(b);

		}

	}
}
