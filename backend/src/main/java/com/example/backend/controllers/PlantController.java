package com.example.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Plant;
import com.example.backend.service.PlantService;
import com.example.backend.util.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlantController {

    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);

    @Autowired
    private PlantService plantService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/plant")
    @CrossOrigin
    public ResponseEntity<?> createPlant(@RequestBody Plant plant, @RequestHeader("Authorization") String token) {
        logger.info("Received token: " + token);  // Log the received token

        try {
            if (token == null || token.isEmpty()) {
                logger.error("Token is missing");
                return ResponseEntity.status(400).body("Token is missing");
            }

            String username = jwtUtil.extractUsername(token.substring(7));  // Bearer <token>
            logger.info("Extracted username from token: " + username);  // Log the extracted username

            if (username == null || username.isEmpty()) {
                logger.error("Invalid or expired token");
                return ResponseEntity.status(401).body("Invalid or expired token");
            }

            Plant savedPlant = plantService.createPlant(plant, username);
            
            if (savedPlant == null) {
                return ResponseEntity.status(400).body("Plant with this name already exists");
            }
            return ResponseEntity.ok(savedPlant);

        } catch (RuntimeException e) {
            logger.error("Error while creating plant: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/plants")
    @CrossOrigin
    public ResponseEntity<List<Plant>> getPlantsByUser(@RequestHeader("Authorization") String token) {
        logger.info("Received token for getPlantsByUser: " + token);  // Log the received token

        try {
            if (token == null || token.isEmpty()) {
                logger.error("Token is missing");
                return ResponseEntity.status(400).body(null);
            }

            String username = jwtUtil.extractUsername(token.substring(7));  // Bearer <token>
            logger.info("Extracted username for getPlantsByUser: " + username);  // Log the extracted username

            if (username == null || username.isEmpty()) {
                logger.error("Invalid or expired token");
                return ResponseEntity.status(401).body(null);
            }

            List<Plant> plants = plantService.getPlantsByUsername(username);
            return ResponseEntity.ok(plants);

        } catch (RuntimeException e) {
            logger.error("Error while fetching plants: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
}
