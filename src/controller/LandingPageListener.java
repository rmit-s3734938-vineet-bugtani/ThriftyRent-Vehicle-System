package controller;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.ThriftyRentSystem;
import model.Vehicle;
import view.LandingPage;

public class LandingPageListener implements EventHandler<ActionEvent> {

	private LandingPage theView;

	public LandingPageListener(LandingPage theView) {
		this.theView = theView;

		// adding a listener to vehicle list in ThriftyRentSystem class
		ThriftyRentSystem.getInstance().getVehicleList().addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change<?> c) {
				// when a new vehicle is added check if model exists in model filter in
				// view,else add it
				for (int i = 0; i <= ThriftyRentSystem.getInstance().getVehicleList().size() - 1; i++) {
					Vehicle v = ThriftyRentSystem.getInstance().getVehicleList().get(i);
					if (!theView.getModelsChoiceBox().getItems().contains(v.getMake())) {
						theView.getModelsChoiceBox().getItems().add(v.getMake());
					}
				}
			}
		});

	}

	@Override
	public void handle(ActionEvent event) {

		theView.getFilteredItems().setPredicate(item -> {

			// logic to filter vehicle,should satisfy all filter logic and then display the
			// vehicle
			if (((item.getVehicleStatus().toLowerCase().equals(theView.getStatusChoiceBox().getValue().toLowerCase()))
					|| ("all").equals(theView.getStatusChoiceBox().getValue().toLowerCase()))
					&& (((item.getVehicleType().toLowerCase()
							.equals(theView.getTypeChoiceBox().getValue().toLowerCase()))
							|| ("all").equals(theView.getTypeChoiceBox().getValue().toLowerCase())))
					&& (((item.getMake().toLowerCase().equals(theView.getModelsChoiceBox().getValue().toLowerCase()))
							|| ("all").equals(theView.getModelsChoiceBox().getValue().toLowerCase())))
					&& (((Integer.toString(item.getNumberOfPassengers()).toLowerCase()
							.equals(theView.getSeatsChoiceBox().getValue().toLowerCase()))
							|| ("all").equals(theView.getSeatsChoiceBox().getValue().toLowerCase())))) {
				return true;
			} else {
				return false;
			}
		});
	}
}
