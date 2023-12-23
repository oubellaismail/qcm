package controller.qcmpro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file and set the controller
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();

        // Get the controller instance
        LoginController loginController = fxmlLoader.getController();

        // Set up the stage
        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("Login Form");
        stage.setScene(scene);

        // Set event handlers


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
