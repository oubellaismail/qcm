package controller.qcmpro;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        System.out.println("Register Button Clicked");
    }
}
