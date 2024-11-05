// WeatherRecord.java
// WeatherRecord.java
package com.example.weather.service;

import org.springframework.stereotype.Service;
 

@Service  // Singleton by default
public class WeatherService {
    private java.util.Map<String, WeatherRecord> weatherData = new java.util.HashMap<>();
    private int totalRequests = 0;  // Shared counter for all users
    public void updateWeather(String city, double temperature, String condition) {
        weatherData.put(city, new WeatherRecord(city, temperature, condition));
        totalRequests++;
    }

    public WeatherRecord getWeather(String city) {
        totalRequests++;
        return weatherData.get(city);
    }

    public int getTotalRequests() {
        return totalRequests;
    }
}