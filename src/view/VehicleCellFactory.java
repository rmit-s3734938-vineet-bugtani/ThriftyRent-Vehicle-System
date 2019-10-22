package view;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Vehicle;

public class VehicleCellFactory implements Callback<ListView<Vehicle>, ListCell<Vehicle>> {
	@Override
	public ListCell<Vehicle> call(ListView<Vehicle> listview) {
		return new VehicleInfo();
	}
}