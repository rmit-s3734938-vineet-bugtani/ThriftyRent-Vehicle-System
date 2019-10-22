package view;

import java.io.FileNotFoundException;

import controller.LandingPageListener;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.ThriftyRentSystem;
import model.Vehicle;

public class LandingPage extends BorderPane {

	private static LandingPage single_instance = null;

	private FilteredList<Vehicle> filteredItems = new FilteredList<Vehicle>(ThriftyRentSystem.getInstance().getVehicleList()); // added
	private ListView<Vehicle> vehicleListView = new ListView<>();

	private HBox hbox = new HBox();

	private LandingPageListener l = new LandingPageListener(this);

	private ChoiceBox<String> statusChoiceBox = new ChoiceBox<String>();
	private ChoiceBox<String> typeChoiceBox = new ChoiceBox<String>();
	private ChoiceBox<String> seatsChoiceBox = new ChoiceBox<String>();
	private ChoiceBox<String> modelsChoiceBox = new ChoiceBox<String>();

	private Label filterByStatusLabel = new Label("Status: ");
	private Label filterByTypeLabel = new Label("Type: ");
	private Label filterBySeatsLabel = new Label("Seats: ");
	private Label filterByMakeLabel = new Label("Make: ");

	private LandingPage() throws FileNotFoundException {

		vehicleListView.setCellFactory(new VehicleCellFactory());
		vehicleListView.setItems(filteredItems);

		statusChoiceBox.setValue("All");
		typeChoiceBox.setValue("All");
		modelsChoiceBox.setValue("All");
		seatsChoiceBox.setValue("All");

		statusChoiceBox.getItems().add("All");
		statusChoiceBox.getItems().add("Available");
		statusChoiceBox.getItems().add("Rented");
		statusChoiceBox.getItems().add("Under Maintainance");

		seatsChoiceBox.getItems().add("All");
		seatsChoiceBox.getItems().add("4");
		seatsChoiceBox.getItems().add("7");
		seatsChoiceBox.getItems().add("15");

		typeChoiceBox.getItems().add("All");
		typeChoiceBox.getItems().add("Car");
		typeChoiceBox.getItems().add("Van");

		modelsChoiceBox.getItems().add("All");
		for (int i = 0; i <= ThriftyRentSystem.getInstance().getVehicleList().size() - 1; i++) {
			Vehicle v = ThriftyRentSystem.getInstance().getVehicleList().get(i);
			if (!modelsChoiceBox.getItems().contains(v.getMake())) {
				modelsChoiceBox.getItems().add(v.getMake());
			}
		}

		hbox.getChildren().addAll(filterByStatusLabel, statusChoiceBox, filterByTypeLabel, typeChoiceBox,
				filterByMakeLabel, modelsChoiceBox, filterBySeatsLabel, seatsChoiceBox);
		hbox.setSpacing(20);

		ScrollPane scrollPane = new ScrollPane(vehicleListView);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		this.setCenter(scrollPane);
		this.setTop(hbox);

		Insets insets = new Insets(20, 0, 90, 0);
		BorderPane.setMargin(hbox, insets);

		statusChoiceBox.setOnAction(l);
		typeChoiceBox.setOnAction(l);
		modelsChoiceBox.setOnAction(l);
		seatsChoiceBox.setOnAction(l);

	}

	public FilteredList<Vehicle> getFilteredItems() {
		return filteredItems;
	}

	public ChoiceBox<String> getModelsChoiceBox() {
		return modelsChoiceBox;
	}

	public void setModelsChoiceBox(ChoiceBox<String> modelsChoiceBox) {
		this.modelsChoiceBox = modelsChoiceBox;
	}

	public ChoiceBox<String> getTypeChoiceBox() {
		return typeChoiceBox;
	}

	public void setTypeChoiceBox(ChoiceBox<String> typeChoiceBox) {
		this.typeChoiceBox = typeChoiceBox;
	}

	public ChoiceBox<String> getSeatsChoiceBox() {
		return seatsChoiceBox;
	}

	public void setSeatsChoiceBox(ChoiceBox<String> seatsChoiceBox) {
		this.seatsChoiceBox = seatsChoiceBox;
	}

	public ChoiceBox<String> getStatusChoiceBox() {
		return statusChoiceBox;
	}

	public void setChoiceBox(ChoiceBox<String> statusChoiceBox) {
		this.statusChoiceBox = statusChoiceBox;
	}

	// making landing page singleton to save its state
	public static LandingPage getInstance() throws FileNotFoundException {
		if (single_instance == null) {
			single_instance = new LandingPage();
		}
		return single_instance;
	}

}
