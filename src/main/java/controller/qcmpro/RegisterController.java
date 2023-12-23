package controller.qcmpro;

import DAO.PersonDAO;
import DAOimplementation.PersonDAOimpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Person;

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

    public void setRegisterButtonActionHandler() {
        registerButton.setOnAction(event -> handleRegisterButtonAction());
    }

    PersonDAO personDAO = new PersonDAOimpl();

    @FXML
    private void handleRegisterButtonAction() {
        PersonDAO personDAO = new PersonDAOimpl();
        Person person = new Person(6,firstNameField.getText(), lastNameField.getText(), emailField.getText(), passwordField.getText());
        personDAO.insertPerson(person);
    }
}
