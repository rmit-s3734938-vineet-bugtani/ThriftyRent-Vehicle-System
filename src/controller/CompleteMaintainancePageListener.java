package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.CompleteMaintainanceException;
import model.DateTime;
import model.InvalidInputException;
import model.Van;
import view.CompleteMaintainancePage;
import view.ErrorDialog;
import view.SucessDialog;

public class CompleteMaintainancePageListener implements EventHandler<ActionEvent> {

    private CompleteMaintainancePage theView;

	public CompleteMaintainancePageListener(CompleteMaintainancePage theView) {
		this.theView = theView;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			//if van maintainance is completed display success message
			((Van) theView.getVan())
					.completeMaintenance(DateTime.stringToDateTime(theView.getMaintainanceCompletionDateId()));
			new SucessDialog("Maintainance completed successfully");
		} catch (Exception e) {
			theView.setMaintainanceCompletionDateId("");
			if (!(e instanceof CompleteMaintainanceException))
				new ErrorDialog(new InvalidInputException().getMessage());
			else
				new ErrorDialog(e.getMessage());
		}
	}
}
