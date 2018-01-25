package com.hot.pwm.app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Read file fxml and draw interface.
			// FXMLLoader.load(getClass().getResource("/view/main.fxml"));
			SpringFxmlLoader springFxmlLoader = new SpringFxmlLoader();
			Parent root = (Parent) springFxmlLoader.springLoad("/view/main.fxml");

			primaryStage.setTitle("Password Manager");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
