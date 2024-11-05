// Assuming this is the definition of WeatherValidationException
package com.example.weather;

import java.util.List;

public class WeatherValidationException extends Exception { // or RuntimeException
    public WeatherValidationException(String message, List<String> violations) {
        super(message);
        // Additional initialization if needed
    }
}