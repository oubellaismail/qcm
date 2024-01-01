package controller.qcmpro;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.json.model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizController {

 @FXML
    private Text question;
    @FXML
    private Button restart;
    @FXML
    private Button next;

 @FXML
 private VBox checkboxContainer;

    private int cont = 1;
    private boolean isFirstClick = true;
    private Map<String, Boolean> correctAnswers = new HashMap<>();

    @FXML
    private void initialize() {
        loadQuestion();
    }
    @FXML
    public void next(ActionEvent e) {
        if (cont < 5) {
            if (isFirstClick) {
                handleCheckboxSelection();
                isFirstClick = false;
            } else {
                loadQuestion();
                isFirstClick = true;
                cont++;
            }
        } else {
            end();
        }
    }
  private void loadQuestion() {
      try {
          String apiUrl = "https://quizapi.io/api/v1/questions?apiKey=ktpARm1lcXvIQel8yfOQSWXLMyNe0LKLhdgpMhlG&limit=1";
          URI uri = URI.create(apiUrl);
          HttpClient client = HttpClient.newHttpClient();
          HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
          HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
          String json = response.body();
          Gson gson = new Gson();
          Type questionListType = new TypeToken<List<Question>>() {
          }.getType();
          List<Question> questions = gson.fromJson(json, questionListType);
          if (!questions.isEmpty()) {
              String questionText = questions.get(0).getQuestion();
              this.question.setText(questionText);
              clearCheckboxes();

              // Create checkboxes for true answers
              createCheckboxes(questions.get(0).getAnswers().getAnswer_a(), "Answer A");
              createCheckboxes(questions.get(0).getAnswers().getAnswer_b(), "Answer B");
              createCheckboxes(questions.get(0).getAnswers().getAnswer_c(), "Answer C");
              createCheckboxes(questions.get(0).getAnswers().getAnswer_d(), "Answer D");
              createCheckboxes(questions.get(0).getAnswers().getAnswer_e(), "Answer E");
              createCheckboxes(questions.get(0).getAnswers().getAnswer_f(), "Answer F");


              correctAnswers.clear();
              correctAnswers.put("Answer A", questions.get(0).getCorrect_answers().getAnswer_a_correct());
              correctAnswers.put("Answer B", questions.get(0).getCorrect_answers().getAnswer_b_correct());
              correctAnswers.put("Answer C", questions.get(0).getCorrect_answers().getAnswer_c_correct());
              correctAnswers.put("Answer D", questions.get(0).getCorrect_answers().getAnswer_d_correct());
              correctAnswers.put("Answer E", questions.get(0).getCorrect_answers().getAnswer_e_correct());
              correctAnswers.put("Answer F", questions.get(0).getCorrect_answers().getAnswer_f_correct());

          } else {
              this.question.setText("No questions available");
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }


    private void handleCheckboxSelection() {
        // Your existing logic for handling checkbox selection
        for (Node node : checkboxContainer.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkbox = (CheckBox) node;
                String checkboxId = checkbox.getId();

                if (correctAnswers.containsKey(checkboxId) && correctAnswers.get(checkboxId).equals(true)) {
                    checkbox.setStyle("-fx-font-size: 14; -fx-background-color: green;");
                } else {
                    checkbox.setStyle("-fx-font-size: 14;");
                }
            }
        }
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
            newCheckbox.setStyle("-fx-font-size: 14; -fx-padding: 5;");
            checkboxContainer.getChildren().add(newCheckbox);
        }
    }

    private void end(){
        question.setVisible(false);
        checkboxContainer.setVisible(false);
        restart.setVisible(true);
        next.setVisible(false);
    }
    @FXML
    private  void restart(ActionEvent e){
        cont = 1;
        question.setVisible(true);
        checkboxContainer.setVisible(true);
        restart.setVisible(false);
        next.setVisible(true);
    }


  }

