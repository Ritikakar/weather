package com.example.weather;

public class weatherValidationConstant {
    
    // City constraints
    public static final int CITY_MIN_LENGTH = 3;
    public static final int CITY_MAX_LENGTH = 20;
    
    // Temperature constraints
    public static final int TEMPERATURE_MIN = -50;
    public static final int TEMPERATURE_MAX = 50;
    
    // Weather condition constraints
    public static final int WEATHER_CONDITION_MIN_LENGTH = 3;
    public static final int WEATHER_CONDITION_MAX_LENGTH = 20;
    public static final String WEATHER_CONDITION_PATTERN = "^(Sunny|cloudy|rainy|snowy)$";
}


    

