package controller.qcmpro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ChoiceBox<String> category;

    @FXML
    private ChoiceBox<String> difficulty;
    @FXML
    private Button startButton;
    @FXML
    private void handlestartButtonAction() {
        redirectToRegisterPage();
    }

    private String[] categories = {"Lunix", "code", "Sql"};
    private String[] difficultes = {"Easy", "Medium", "Hard"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        category.getItems().add("Select an option");
        difficulty.getItems().add("Select an option");
        category.getItems().addAll(categories);
        difficulty.getItems().addAll(difficultes);

        category.getSelectionModel().selectFirst();
        difficulty.getSelectionModel().selectFirst();

        category.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.equals("Select an option")) {
                System.out.println("Nothing selected");
            } else {
                System.out.println("Selected: " + newValue);
            }
        });

        difficulty.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.equals("Select an option")) {
                System.out.println("Nothing selected");
            } else {
                System.out.println("Selected: " + newValue);
            }
        });
    }

    private void redirectToRegisterPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Registration Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
