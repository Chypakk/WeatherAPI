package org.chypakk.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.chypakk.WeatherServer;
import org.chypakk.model.WeatherResponse;
import org.chypakk.service.WeatherService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class WeatherHandler implements HttpHandler {

    private final WeatherService weatherService = new WeatherService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())){
            sendResponse(exchange, 405, "Метод не поддерживается");
        }

        try {

            String query = exchange.getRequestURI().getQuery();
            Map<String, String> params = getParams(query);
            String city = params.get("city");

            if (city == null || city.isEmpty()){
                sendResponse(exchange, 400, "Параметр city обязателен");
            }

            WeatherResponse weatherResponse = weatherService.getWeather(city);
            sendResponse(exchange, 200, objectMapper.writeValueAsString(weatherResponse));

        } catch (Exception e){
            sendResponse(exchange, 500, "Ошибка сервера: " + e.getMessage());
        }
    }

    private void sendResponse(HttpExchange exchange, int code, String message) throws IOException {
        exchange.sendResponseHeaders(code, message.getBytes().length);
        try (OutputStream stream = exchange.getResponseBody()){
            stream.write(message.getBytes());
        }
    }

    private Map<String, String> getParams(String query){
        Map<String, String> params = new HashMap<>();

        for (var param : query.split("&")){
            String[] pair = param.split("=");
            if (pair.length > 0){
                params.put(pair[0], pair[1]);
            }
        }

        return params;
    }
}
