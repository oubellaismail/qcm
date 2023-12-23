package controller.qcmpro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;


    public void setLoginButtonActionHandler() {
        loginButton.setOnAction(event -> handleLoginButtonAction());
    }

    public void setRegisterButtonActionHandler() {
        registerButton.setOnAction(event -> handleRegisterButtonAction());
    }



    @FXML
    private void handleLoginButtonAction() {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        System.out.println("Login Button Clicked");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }

    @FXML
    private void handleRegisterButtonAction() {
        redirectToRegisterPage();
    }

    private void redirectToRegisterPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Registration Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
