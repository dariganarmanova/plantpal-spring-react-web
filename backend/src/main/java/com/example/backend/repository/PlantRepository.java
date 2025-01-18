package com.example.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findByUserUsername(String username);
}
