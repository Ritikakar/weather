package com.example.weather.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.weather.WeatherRecord;
import com.example.weather.service.UserWeatherPreferences;
import com.example.weather.service.WeatherQuery;
import com.example.weather.service.WeatherRequestLogger;
import com.example.weather.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;              // Singleton
    private final WeatherRequestLogger requestLogger;         // Request Scope
    private final UserWeatherPreferences userPreferences;     // Session Scope
    private final ApplicationContext applicationContext;      // For Prototype

    public WeatherController(
            WeatherService weatherService,
            WeatherRequestLogger requestLogger,
            UserWeatherPreferences userPreferences,
            ApplicationContext applicationContext) {
        this.weatherService = weatherService;
        this.requestLogger = requestLogger;
        this.userPreferences = userPreferences;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/{city}")
    public Map<String, Object> getWeather(@PathVariable String city) {
        // Request scope - log the request
        requestLogger.logRequest(city);

        // Session scope - add to recent searches
        userPreferences.addSearch(city);

        // Prototype scope - create new query
        WeatherQuery query = applicationContext.getBean(WeatherQuery.class);
        // Singleton - get weather data
        com.example.weather.service.WeatherRecord serviceWeather = weatherService.getWeather(city);
        com.example.weather.WeatherRecord weather = new com.example.weather.WeatherRecord();
        
        // Check if serviceWeather is not null to avoid NullPointerException
        if (serviceWeather != null) {
            weather.setCity(serviceWeather.getCity());
            weather.setTemperature(serviceWeather.getTemperature());
            weather.setCondition(serviceWeather.getCondition());
        } else {
            // Handle the case where weather data is not available
            // For example, set default values or throw an exception
            weather.setCity(city);
            weather.setTemperature(0); // Default temperature
            weather.setCondition("Unavailable"); // Default condition
        }

        Map<String, Object> response = new HashMap<>();
        response.put("weather", weather);
        response.put("requestInfo", requestLogger.getRequestInfo());
        response.put("recentSearches", userPreferences.getRecentSearches());
        response.put("queryInfo", Map.of(
            "queryId", query.getQueryId(),
            "queryTime", query.getQueryTime()
        ));
        response.put("totalRequests", weatherService.getTotalRequests());

        return response;
    }

    @PostMapping("/{city}")
    public Map<String, Object> updateWeather(
            @PathVariable String city,
            @RequestParam double temperature,
            @RequestParam String condition) {
        weatherService.updateWeather(city, temperature, condition);
        return getWeather(city);
    }

    @PostMapping("/preferences/unit")
    public Map<String, String> setUnit(@RequestParam String unit) {
        userPreferences.setTemperatureUnit(unit);
        return Map.of(
            "sessionId", userPreferences.getSessionId(),
            "unit", userPreferences.getTemperatureUnit()
        );
    }
}