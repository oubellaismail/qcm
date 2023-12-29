package com.json.app;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.lang.reflect.Type;
import java.util.List;
import com.json.model.*;

public class Main {
    public static void main(String[] args) {

        try {
            String apiUrl = "https://quizapi.io/api/v1/questions?apiKey=ktpARm1lcXvIQel8yfOQSWXLMyNe0LKLhdgpMhlG&limit=1";

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
            System.out.println(response.body());
            String json = response.body();

            // Use Gson to parse the JSON string into a list of Quiz objects
            Gson gson = new Gson();
            Type questionListType = new TypeToken<List<Question>>() {}.getType();
            List<Question> questionzes = gson.fromJson(json, questionListType);

            // Now you can work with the list of Quiz objects
            for (Question question : questionzes) {
                System.out.println("*********************************");
                System.err.println("id :" + question.getId());
                System.err.println("question :" + question.getQuestion());
                System.err.println("description :" + question.getDescription());

                System.err.println("Answers : ");
                System.err.println("answer_a : " + question.getAnswers().getAnswer_a());
                System.err.println("answer_b : " + question.getAnswers().getAnswer_b());
                System.err.println("answer_c : " + question.getAnswers().getAnswer_c());
                System.err.println("answer_d : " + question.getAnswers().getAnswer_d());
                System.err.println("answer_e : " + question.getAnswers().getAnswer_e());
                System.err.println("answer_f : " + question.getAnswers().getAnswer_f());
                
                System.err.println("Multiple correct answers : " + question.getMultiple_correct_answers());
                
                System.err.println("Correct Answers : ");
                System.err.println("answer_a_correct : " + question.getCorrect_answers().getAnswer_a_correct());
                System.err.println("answer_b_correct : " + question.getCorrect_answers().getAnswer_b_correct());
                System.err.println("answer_c_correct : " + question.getCorrect_answers().getAnswer_c_correct());
                System.err.println("answer_d_correct : " + question.getCorrect_answers().getAnswer_d_correct());
                System.err.println("answer_e_correct : " + question.getCorrect_answers().getAnswer_e_correct());
                System.err.println("answer_f_correct : " + question.getCorrect_answers().getAnswer_f_correct());

                System.err.println("Correct answers : " + question.getCorrect_answer());
                System.err.println("Explanation : " + question.getExplanation());
                System.err.println("tip : " + question.getTip());
                System.err.println("Tag : " + question.getTags().get(0).getName());
                System.err.println("Category : " + question.getCategory());
                System.err.println("Defificulty : " + question.getDifficulty());


                System.out.println("*********************************");
                System.err.println(question);
                System.out.println("*********************************");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
