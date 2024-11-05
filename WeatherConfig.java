package com.example.weather;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "weather")
public class WeatherConfig {
 private String defaultCondition;
 private Map<String,TemperatureRange> cityTempratureRanges=new HashMap<>();

 public String getDefaultCondition(){
    return defaultCondition;
 }
 public void setDefaultCondition(String defaultCondition){
    this.defaultCondition = defaultCondition;
 }
 public Map<String,TemperatureRange> getCityTempratureRanges(){
    return this.cityTempratureRanges;
 }
 public void setCityTempratureRanges(Map<String,TemperatureRange> cityTempratureRanges){
    this.cityTempratureRanges = cityTempratureRanges;
 }
 public static class TemperatureRange{
    private double min;
    private double max;
    public double getMin(){
        return min;
    }
    public void setMin(double min){
        this.min = min;
    }
    public double getMax(){
        return max;
    }
    public void setMax(double max){
        this.max = max;
    }
 }
}
