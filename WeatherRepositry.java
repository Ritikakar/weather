package com.example.weather;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface WeatherRepositry extends JpaRepository<WeatherRecord,Long> {

    Optional<WeatherRecord> findByCity(String city);
} 
