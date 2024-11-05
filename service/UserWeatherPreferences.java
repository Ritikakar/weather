
package com.example.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserWeatherPreferences {
    private final String sessionId = java.util.UUID.randomUUID().toString();
    private final List<String> recentSearches = new ArrayList<>();
    private String temperatureUnit = "Celsius";

    public void addSearch(String city) {
        if (recentSearches.size() >= 5) {
            recentSearches.remove(0);
        }
        recentSearches.add(city);
    }

    public List<String> getRecentSearches() {
        return new ArrayList<>(recentSearches);
    }

    public String getSessionId() { return sessionId; }
    public void setTemperatureUnit(String unit) { this.temperatureUnit = unit; }
    public String getTemperatureUnit() { return temperatureUnit; }
}