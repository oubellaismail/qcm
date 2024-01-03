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

public class LoginController {

    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    private User user;


    public void setLoginButtonActionHandler() {
        loginButton.setOnAction(event -> handleLoginButtonAction());
    }

    public void setRegisterButtonActionHandler() {
        registerButton.setOnAction(event -> handleRegisterButtonAction());
    }

    UserDAO userDAO = new UserDAOimp();

    @FXML
    private void handleLoginButtonAction() {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        if(email.equals("") || password.equals("")){
            String s = "Path: " + getClass().getResource("/controller.qcmpro/login-view.fxml");
            showErrorAlert(s);
        }else {
             user =  userDAO.findUser(new User(email, password));
        }
        if (user != null){
            redirectToHomePage(user);
    }
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

    private void redirectToHomePage(User user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("home-view.fxml"));
            Parent root = fxmlLoader.load();
            HomeController homeController = fxmlLoader.getController();
            homeController.initData(user);
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setTitle("Quiz Application");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
