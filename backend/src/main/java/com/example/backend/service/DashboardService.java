package com.example.backend.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.model.DashboardPlant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.example.backend.model.Plant;
import com.example.backend.repository.PlantRepository;
import com.example.backend.util.JwtUtil;

import java.time.temporal.ChronoUnit;

@Service
public class DashboardService {
    @Autowired
    private PlantRepository plantRepository;
    @Autowired 
    private JwtUtil jwtUtil;
    public List<DashboardPlant> getDashboardPlants(String token) {
        String username = jwtUtil.extractUsername(token.substring(7));
        List<Plant> plants = plantRepository.findByUserUsername(username);
        return plants.stream()
            .map(this::convertToDashboardPlant)
            .collect(Collectors.toList());
    }
    private DashboardPlant convertToDashboardPlant(Plant plant) {
        LocalDateTime lastWateredTime = parseWateredField(plant.getWatered());
        long hoursSinceWatered = ChronoUnit.HOURS.between(lastWateredTime, LocalDateTime.now());
        boolean shouldBeWatered = hoursSinceWatered > 10;
        return new DashboardPlant(
            plant.getName(),
            plant.getLocation(),
            plant.getDescription(),
            plant.getAge(),
            plant.getWatered(),
            plant.getUser(),
            shouldBeWatered
        );
    }
    private LocalDateTime parseWateredField(String watered) {
        try {
            if (watered.contains("T") || watered.contains(":")) {
                return LocalDateTime.parse(watered, DateTimeFormatter.ISO_DATE_TIME);
            } else {
                return LocalDateTime.parse(watered+"T00:00", DateTimeFormatter.ISO_DATE_TIME);
            }
    } catch(Exception e) {
        return LocalDateTime.MIN;
    }
}
}
