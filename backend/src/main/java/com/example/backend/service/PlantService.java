package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.Plant;
import com.example.backend.repository.PlantRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.model.User;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private UserRepository userRepository;
    public Plant createPlant(Plant plant, String username) {
        if (plant.getName()==null|| plant.getName().isEmpty()) {
            throw new IllegalArgumentException("Plant name is required");
        }if (plant.getLocation()==null|| plant.getLocation().isEmpty()) {
            throw new IllegalArgumentException("Plant name is required");
        }if (plant.getDescription()==null|| plant.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Plant name is required");
        }
        User user = userRepository.findByUsername(username).get();
        if (user != null) {
            plant.setUser(user);
            return plantRepository.save(plant);
        }
        return null;
    }
    public List<Plant> getPlantsByUsername(String username) {
        return plantRepository.findByUserUsername(username);
    }
}
