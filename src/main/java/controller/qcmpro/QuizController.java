package controller.qcmpro;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.json.model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

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
    @FXML
    private void initialize() {
        loadQuestion();
    }
    @FXML
    public void next(ActionEvent e){
        if(cont < 5) {
            loadQuestion();
            cont++;
        }
        else {
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
              createCheckboxes(questions.get(0).getAnswers().getAnswer_a());
              createCheckboxes(questions.get(0).getAnswers().getAnswer_b());
              createCheckboxes(questions.get(0).getAnswers().getAnswer_c());
              createCheckboxes(questions.get(0).getAnswers().getAnswer_d());
              createCheckboxes(questions.get(0).getAnswers().getAnswer_e());
              createCheckboxes(questions.get(0).getAnswers().getAnswer_f());

          } else {
              this.question.setText("No questions available");
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
    private void clearCheckboxes() {
        checkboxContainer.getChildren().clear();
    }

    private void createCheckboxes(String answer) {
        if (answer != null){
            CheckBox newCheckbox = new CheckBox(answer);
            newCheckbox.setPrefHeight(200);
            newCheckbox.setStyle("-fx-font-size: 14;");
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

