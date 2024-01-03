package controller.qcmpro;


import com.json.DAO.UserDAO;
import com.json.DAOimpl.UserDAOimp;
import com.json.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField userName;
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
    UserDAO userDAO = new UserDAOimp();

    @FXML
    private void handleRegisterButtonAction() {
        if(userName.getText().equals("") || emailField.getText().equals("") ||passwordField.getText().equals("")){
            String s = "Path: " + getClass().getResource("/controller.qcmpro/login-view.fxml");
            showErrorAlert(s);

        }else {
            userDAO.inserUser(new User(userName.getText(), emailField.getText(), passwordField.getText()));
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
