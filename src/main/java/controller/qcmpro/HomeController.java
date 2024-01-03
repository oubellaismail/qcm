package controller.qcmpro;

import com.json.model.Quiz;
import com.json.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private Button startButton;
    @FXML
    private Text userName;
    @FXML
    private Text levels;
    @FXML
    private Button Historique;
    private User user;
    @FXML
    private void handlestartButtonAction() {
        redirectToQuizPage(user);
    }
    @FXML
    private void handleHistoButtonAction() {
        redirectToHistoPage(user);
    }


    public void initData(User u) {
        user = u;
        userName.setText(u.getuserName());
        levels.setText("level:"+u.getLevel());
    }

    private void redirectToQuizPage(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            QuizController quizController = loader.getController();
            quizController.initData(user);
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Quiz");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void redirectToHistoPage(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hesto.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            HestoController hestoController = loader.getController();
            hestoController.initData(user);
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Quiz");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
