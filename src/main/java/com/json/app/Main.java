package com.json.app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.json.DAO.*;
import com.json.DAOimpl.*;
import com.json.model.*;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuizDAO quizDAO = new QuizDAOimp();
        UserDAO userDAO = new UserDAOimp();
        User user = userDAO.findUser(new User("ismail@mail.com", "just"));
        if (user == null) {
            try {
                CombinedQuestionResponse combinedResponse = new CombinedQuestionResponse();

                for (String difficulty : List.of("easy", "medium", "hard")) {
                    String apiUrl = "https://quizapi.io/api/v1/questions?apiKey=ktpARm1lcXvIQel8yfOQSWXLMyNe0LKLhdgpMhlG&limit=1&difficulty=" + difficulty;

                    URI uri = URI.create(apiUrl);
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    String json = response.body();

                    Gson gson = new Gson();
                    Type questionListType = new TypeToken<List<Question>>() {}.getType();
                    List<Question> questions = gson.fromJson(json, questionListType);

                    switch (difficulty) {
                        case "easy":
                            combinedResponse.setEasyQuestions(questions);
                            break;
                        case "medium":
                            combinedResponse.setMediumQuestions(questions);
                            break;
                        case "hard":
                            combinedResponse.setHardQuestions(questions);
                            break;
                    }
                }

                String combinedJson = new Gson().toJson(combinedResponse);
                // System.out.println(combinedJson);

                quizDAO.insertQuiz(new Quiz(combinedResponse, user.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // System.out.println("User not found.");
            System.out.println(quizDAO.findQuiz(4));
        }
    }
}
