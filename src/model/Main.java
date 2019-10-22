package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.LandingPage;
import view.Menu;

public class Main extends Application {

	private static BorderPane root = new BorderPane();
	private static Stage primaryStage;

	public static BorderPane getRoot() {
		return root;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		Main.primaryStage = primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {

		try {
			// uncomment to restore original data in database
			// new DeleteTable();
			// new CreateTable();
			// new InsertData();
			new SelectQuery();
			Menu menu = new Menu();
			root.setTop(menu);
			root.setCenter(LandingPage.getInstance());
			Scene scene = new Scene(root, 900, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
