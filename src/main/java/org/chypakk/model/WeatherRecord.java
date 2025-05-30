package org.chypakk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherRecord(
        @JsonProperty("name") String city,
        @JsonProperty("main") TemperatureData temperatureData,
        @JsonProperty("weather") List< Weather> weather) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Weather(
            String description
    ){}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TemperatureData(
            @JsonProperty("temp") double temperature,
            double pressure,
            double humidity
    ){}
}

