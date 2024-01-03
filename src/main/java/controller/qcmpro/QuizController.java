package controller.qcmpro;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.json.DAO.QuizDAO;
import com.json.DAO.UserDAO;
import com.json.DAOimpl.QuizDAOimp;
import com.json.DAOimpl.UserDAOimp;
import com.json.model.Question;
import com.json.model.Quiz;
import com.json.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.stage.Stage;

public class QuizController {

    @FXML
    private Text question;
    @FXML
    private Button restart;
    @FXML
    private Button next;
    @FXML
    private Text difficulties;
    @FXML
    private Text levels;
    @FXML
    private Text categories;
    @FXML
    private Text userName;
    @FXML
    private VBox checkboxContainer;
    @FXML
    private FontAwesomeIconView userManage;
    private int cont = 1;
    private boolean isFirstClick = true;
    private boolean levelTest = true;
    private int diffCont = 0;
    private final Map<String, Boolean> correctAnswers = new HashMap<>();
    UserDAO userDAO = new UserDAOimp();
    QuizDAO quizDAO = new QuizDAOimp();
    private User user;
    private  Quiz quiz;

    public void initData(User u) {
        user = u;
        quiz = new Quiz(user.getId());
        quizDAO.insertQuiz(quiz);
        loadQuestion();
        userManage.setOnMouseClicked(event -> redirectToUserView());
    }


    @FXML
    public void next() {
        if (cont < user.getLevel() * 3 ) {
            if (isFirstClick) {
                handleCheckboxSelection();
                isFirstClick = false;
            } else {
                loadQuestion();
                isFirstClick = true;
                cont++;
            }
        } else end();
    }


    private String fetchDataFromAPI() throws IOException, InterruptedException {
        String difficulty = getDifficultyString();
        URI uri = URI.create("https://quizapi.io/api/v1/questions?apiKey=ktpARm1lcXvIQel8yfOQSWXLMyNe0LKLhdgpMhlG&limit=1&difficulty=" + difficulty);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
  private void loadQuestion() {
        try {
            String json = fetchDataFromAPI();
            Gson gson = new Gson();
            Type questionListType = new TypeToken<List<Question>>() {
            }.getType();
            List<Question> questions = gson.fromJson(json, questionListType);

            if (!questions.isEmpty()) {
                Question question = questions.getFirst();
                String questionText = question.getQuestion();
                this.question.setText(questionText);
                clearCheckboxes();

                // Create checkboxes for true answers
                createCheckboxes(question.getAnswers().getAnswer_a(), "Answer A");
                createCheckboxes(question.getAnswers().getAnswer_b(), "Answer B");
                createCheckboxes(question.getAnswers().getAnswer_c(), "Answer C");
                createCheckboxes(question.getAnswers().getAnswer_d(), "Answer D");
                createCheckboxes(question.getAnswers().getAnswer_e(), "Answer E");
                createCheckboxes(question.getAnswers().getAnswer_f(), "Answer F");


                correctAnswers.clear();
                correctAnswers.put("Answer A", question.getCorrect_answers().getAnswer_a_correct());
                correctAnswers.put("Answer B", question.getCorrect_answers().getAnswer_b_correct());
                correctAnswers.put("Answer C", question.getCorrect_answers().getAnswer_c_correct());
                correctAnswers.put("Answer D", question.getCorrect_answers().getAnswer_d_correct());
                correctAnswers.put("Answer E", question.getCorrect_answers().getAnswer_e_correct());
                correctAnswers.put("Answer F", question.getCorrect_answers().getAnswer_f_correct());

                difficulties.setText("Difficulty: "+ question.getDifficulty());
                categories.setText("Category: "+ question.getCategory());
                userName.setText(user.getuserName());
                levels.setText("Level : "+ user.getLevel());
                if (diffCont < 3) diffCont++;
                quiz.getQuestions().addLast(question);
                quizDAO.updateQuiz(quiz);
            } else {
                this.question.setText("No questions available");
            }
            } catch(Exception e){
                e.printStackTrace();
            }

        }

    private String getDifficultyString() {
        if (diffCont >= 3) diffCont=0;
        String[] difficulties = {"easy", "medium", "hard"};
        return difficulties[diffCont];
    }



    private void handleCheckboxSelection() {
        // Your existing logic for handling checkbox selection
        boolean ischecked = false;
        for (Node node : checkboxContainer.getChildren()) {
            if (node instanceof CheckBox checkbox) {
                String checkboxId = checkbox.getId();
                if (correctAnswers.containsKey(checkboxId) && correctAnswers.get(checkboxId).equals(true)) {
                    checkbox.setStyle("-fx-font-size: 14; -fx-text-fill: green; -fx-font-weight: bold; -fx-padding: 5;");
                } else if (checkbox.isSelected()){
                    checkbox.setStyle("-fx-font-size: 14;-fx-text-fill: red; -fx-font-weight: bold; -fx-padding: 5;");
                    levelTest = false;
                }
                if (checkbox.isSelected()) ischecked = true;
            }
        }
        if (!ischecked) levelTest = false;
    }
    private void clearCheckboxes() {
        checkboxContainer.getChildren().clear();
    }
    private void createCheckboxes(String answer, String id) {
        if (answer != null) {
            CheckBox newCheckbox = new CheckBox(answer);
            newCheckbox.setId(id); // Set the ID
            newCheckbox.setPrefHeight(100);
            newCheckbox.setPrefWidth(500);
            newCheckbox.setStyle("-fx-font-size: 14; -fx-padding: 5; -fx-font-weight: bold;");
            checkboxContainer.getChildren().add(newCheckbox);
        }
    }
    private void end(){
        handleCheckboxSelection();
        restart.setVisible(true);
//        question.setVisible(false);
//        difficulties.setVisible(false);
//        levels.setVisible(false);
//        categories.setVisible(false);
//        checkboxContainer.setVisible(false);
        next.setVisible(false);
        if (levelTest){
            int updatedLevel = user.getLevel() + 1;
            user.setLevel(updatedLevel);
            userDAO.updateUser(user);
        }
        else levelTest = true;

    }
    @FXML
    private  void restart(){
        cont = 1;
        loadQuestion();
        question.setVisible(true);
        checkboxContainer.setVisible(true);
        difficulties.setVisible(true);
        levels.setVisible(true);
        categories.setVisible(true);
        restart.setVisible(false);
        next.setVisible(true);
    }

    private void redirectToUserView() {
        // Add logic to redirect to the user view
        // You can use FXMLLoader to load the user view FXML and switch scenes, for example.
        // Here is a simplified example:
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            UserController userController = loader.getController();
            userController.initData(user);
            Stage stage = (Stage) userManage.getScene().getWindow();

            // Set the new scene
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


}

