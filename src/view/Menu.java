package view;

import controller.MenuListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Menu extends HBox {

	private Button back = new Button("Back");
	private Button addVehicle = new Button("Add Vehicle");
	private Button importData = new Button("Import data");
	private Button exportData = new Button("Export data");
	private Button saveData = new Button("Save");
	private Button quit = new Button("Quit");

	public Menu() {

		MenuListener handler = new MenuListener();
		back.setOnAction(handler);
		addVehicle.setOnAction(handler);
		importData.setOnAction(handler);
		exportData.setOnAction(handler);
		saveData.setOnAction(handler);
		quit.setOnAction(handler);

		this.getChildren().addAll(back, addVehicle, importData, exportData, saveData, quit);
		this.setPadding(new Insets(20));
		this.setSpacing(15);
		this.setAlignment(Pos.TOP_LEFT);
	}

}
