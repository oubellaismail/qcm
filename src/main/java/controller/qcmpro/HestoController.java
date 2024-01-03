package controller.qcmpro;

import com.json.model.Quiz;
import com.json.model.User;
import com.json.DAOimpl.QuizDAOimp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class HestoController {

    @FXML
    private ListView<Quiz> quizListView;
    @FXML
    private Text userName;
    @FXML
    private Text levels;
    @FXML
    private Button home;

    private User user;
    private QuizDAOimp quizDAO = new QuizDAOimp();
    private Quiz quiz;

    public void initData(User u) {
        user = u;
        quiz = new Quiz(user.getId());
        quizDAO.insertQuiz(quiz);
        userName.setText(u.getuserName());
        levels.setText("Level:"+ u.getLevel());
        loadQuizzes();
    }

    private void loadQuizzes() {
        List<Quiz> quizzes = quizDAO.findAll(user.getId());
        ObservableList<Quiz> quizObservableList = FXCollections.observableArrayList(quizzes);
        quizListView.setItems(quizObservableList);
        quizListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Quiz> call(ListView<Quiz> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Quiz quiz, boolean empty) {
                        super.updateItem(quiz, empty);
                        if (empty || quiz == null) {
                            setText(null);
                        } else {
                            setText("Quiz ID: " + quiz.getId());
                        }
                    }
                };
            }
        });
    }
    @FXML
    private void redirectToHomePage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("home-view.fxml"));
            Parent root = fxmlLoader.load();
            HomeController homeController = fxmlLoader.getController();
            homeController.initData(user);
            Scene scene = new Scene(root);
            Stage stage = (Stage) home.getScene().getWindow();
            stage.setTitle("Quiz Application");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
