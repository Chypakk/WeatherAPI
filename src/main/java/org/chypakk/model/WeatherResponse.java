package org.chypakk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    @JsonProperty("name")
    private String city;

    @JsonProperty("main")
    private TemperatureData temperatureData;

    @JsonProperty("weather")
    private Weather[] weather;

    public WeatherResponse(){}
    public WeatherResponse(String city, TemperatureData temperatureData, Weather[] weather){
        this.city = city;
        this.temperatureData = temperatureData;
        this.weather = weather;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TemperatureData(
            @JsonProperty("temp") double temperature,
            double pressure,
            double humidity,
            @JsonProperty("temp_min") double min,
            @JsonProperty("temp_max") double max
    ){}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Weather(
            String main,
            String description
    ){}

    public String getCity() {
        return city;
    }

    public TemperatureData getTemperatureData() {
        return temperatureData;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperatureData(TemperatureData temperatureData) {
        this.temperatureData = temperatureData;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }
}
