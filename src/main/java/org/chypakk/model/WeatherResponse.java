package org.chypakk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private String city;
    private String description;
    private double temperature;

    public WeatherResponse(){}
    public WeatherResponse(String city, String description, double temperature){
        this.city = city;
        this.description = description;
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
