package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import com.example.backend.model.DashboardPlant;
import com.example.backend.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;
    @GetMapping("/plantRetrieve")
    @CrossOrigin
    public List<DashboardPlant> getPlantWithWatered(@RequestHeader("Authorization") String token){
        return dashboardService.getDashboardPlants(token);
    }
}
