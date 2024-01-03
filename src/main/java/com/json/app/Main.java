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
        userDAO.inserUser(new User("ismail.code", "ismail@mail.com", "just"));
        User user = userDAO.findUser(new User("ismail@mail.com", "just"));
        Quiz quiz = new Quiz(user.getId());
        quizDAO.insertQuiz(quiz);
        int count = 0;

        if (user != null) {
            try {

                int level = 1;

                for (String difficulty : List.of("easy", "medium", "hard")) {

                    String apiUrl = "https://quizapi.io/api/v1/questions?apiKey=ktpARm1lcXvIQel8yfOQSWXLMyNe0LKLhdgpMhlG&limit=" 
                            + Integer.toString(level) + "&difficulty=" + difficulty;

                    // curl https://quizapi.io/api/v1/questions -G \
                    // -d apiKey=YOUR_API_KEY \
                    // -d limit=10 \
                    // -d category=Linux \
                    // -d difficulty=easy
                    // Create a URI object
                    URI uri = URI.create(apiUrl);
                    // Create an HttpClient
                    HttpClient client = HttpClient.newHttpClient();
                    // Create an HttpRequest
                    HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
                    // Send the request and get the response
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    // Print the response body
                    // System.out.println(response.body());
                    String json = response.body();

                    // Use Gson to parse the JSON string into a list of Quiz objects
                    Gson gson = new Gson();
                    Type questionListType = new TypeToken<List<Question>>() {
                    }.getType();
                    List<Question> questionzes = gson.fromJson(json, questionListType);
                    quiz.getQuestions().addAll(questionzes);
                    quizDAO.updateQuiz(quiz, count++ * level);
                }
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // System.out.print(quizDAO.findQuiz(12));
            // System.out.println("User not found.");
            // System.out.println(quizDAO.findQuiz(4));
            // System.out.println(quizDAO.findAll(1));
            // quizDAO.deleteQuiz(11);
            // userDAO.deleteUser(1);
            System.out.println("No user !");
        }
    }
}
