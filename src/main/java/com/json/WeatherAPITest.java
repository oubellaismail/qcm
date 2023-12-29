package com.json;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherAPITest {
    public static void main(String[] args) {
        try {
            // API URL
            String apiUrl = "https://quizapi.io/api/v1/questions?apiKey=ktpARm1lcXvIQel8yfOQSWXLMyNe0LKLhdgpMhlG&limit=2";

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
