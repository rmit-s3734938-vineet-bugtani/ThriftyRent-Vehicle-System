package controller;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import model.Files;
import model.Main;
import model.SaveData;
import view.AddVehiclePage;
import view.LandingPage;

public class MenuListener implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {

		Button button = (Button) event.getSource();
		String buttonName = button.getText();

		switch (buttonName) {
		case "Back":
			try {
				// display landingpage on press of back button
				Main.getRoot().setCenter(LandingPage.getInstance());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "Add Vehicle":
			Main.getRoot().setCenter(new AddVehiclePage());
			break;
		case "Quit":
			Platform.exit();
			break;
		case "Save":
			new SaveData();
			break;
		case "Import data":
			try {
				// open a file chooser, if file is selected read it
				FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
				if (fileChooser != null)
					Files.readFromFile(file.getAbsolutePath());
			} catch (Exception e) {
			}
			break;
		case "Export data":
			try {
				// select a directory to save textfile with exported data
				DirectoryChooser fileChooser = new DirectoryChooser();
				File file = fileChooser.showDialog(Main.getPrimaryStage());
				if (fileChooser != null)
					Files.writeToFile(file.getAbsolutePath());
			} catch (Exception e) {

			}
			break;
		}
	}

}
