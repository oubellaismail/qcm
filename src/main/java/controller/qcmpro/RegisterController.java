package controller.qcmpro;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private void handleRegisterButtonAction() {
        // Add your registration logic here
        System.out.println("Register Button Clicked");
        System.out.println("First Name: " + firstNameField.getText());
        System.out.println("Last Name: " + lastNameField.getText());
        System.out.println("Email: " + emailField.getText());
        System.out.println("Password: " + passwordField.getText());
    }
}
