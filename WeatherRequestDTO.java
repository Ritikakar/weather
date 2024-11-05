package com.example.weather;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WeatherRequestDTO{
    @NotBlank(message = "City is required and cannot be blank")
    @Size(min= 3, max = 20, message = "City must be between 3 to 20 characters")
    private String city;
    
    @Min(value = -50, message = "temperature cannot be less than -50")
    @Max(value = 50, message = "temperature cannot be more than 50")
    private int temperature;
    @NotBlank(message = "weather is required and cannot be blank")
    @Size(min= 3, max = 20, message = "weather condition must be between 3 to 20 characters")
    @Pattern(regexp = "^(Sunny|cloudy|rainy|snowy)$", message = "weather condition must be sunny")
    
    private String weatherCondition;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
}