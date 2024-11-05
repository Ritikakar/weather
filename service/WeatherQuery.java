
package com.example.weather.service;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WeatherQuery {
    private final String queryId = java.util.UUID.randomUUID().toString();
    private final LocalDateTime queryTime = LocalDateTime.now();

    public String getQueryId() { return queryId; }
    public LocalDateTime getQueryTime() { return queryTime; }
}
 
