package controller.qcmpro;

import DAO.PersonDAO;
import DAOimplementation.PersonDAOimpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;

import java.io.IOException;

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
    private Button loginButton;

    public void setRegisterButtonActionHandler() {
        registerButton.setOnAction(event -> handleRegisterButtonAction());
    }
    public void setLoginButtonActionHandler() {
        loginButton.setOnAction(event -> redirectToLoginPage());
    }
    PersonDAO personDAO = new PersonDAOimpl();

    @FXML
    private void handleRegisterButtonAction() {
        if(firstNameField.getText().equals("") || lastNameField.getText().equals("") || emailField.getText().equals("") ||passwordField.getText().equals("")){

            String s = "Path: " + getClass().getResource("/controller.qcmpro/login-view.fxml");
            showErrorAlert(s);

        }else {
        PersonDAO personDAO = new PersonDAOimpl();
        Person person = new Person(43,firstNameField.getText(), lastNameField.getText(), emailField.getText(), passwordField.getText());
        personDAO.insertPerson(person);
        redirectToLoginPage();
    }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void redirectToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
