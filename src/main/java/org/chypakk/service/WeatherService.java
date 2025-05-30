package org.chypakk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chypakk.model.WeatherRecord;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
    private final String API_TOKEN = System.getenv("API_TOKEN");
    private final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=ru";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherRecord getWeather(String city) throws IOException, InterruptedException {
        String url = String.format(API_URL, city, API_TOKEN);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) throw new RuntimeException("Ошибка API: " + response.body());

        String json = response.body();
        return objectMapper.readValue(json, WeatherRecord.class);
    }

}
