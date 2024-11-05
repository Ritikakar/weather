
package com.example.weather.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class WeatherRequestLogger {
    private final String requestId = java.util.UUID.randomUUID().toString();
    private final LocalDateTime requestTime = LocalDateTime.now();
    private String cityRequested;

    public void logRequest(String city) {
        this.cityRequested = city;
    }

    public String getRequestInfo() {
        return String.format("Request ID: %s, Time: %s, City: %s", 
            requestId, requestTime, cityRequested);
    }
}