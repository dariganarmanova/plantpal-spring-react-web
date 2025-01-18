package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

public class DashboardPlant {

    private String name;
    private String location;
    private String description;
    private Number age;  // Use Number to match Plant's getAge()
    private String watered;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    private boolean shouldBeWatered;

    // Updated constructor
    public DashboardPlant(String name, String location, String description, Number age, String watered, User user, boolean shouldBeWatered) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.age = age;  // No need to cast if age is a Number
        this.watered = watered;
        this.user = user;
        this.shouldBeWatered = shouldBeWatered;
    }

    // Getters and setters (if needed)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Number getAge() {
        return age;
    }

    public void setAge(Number age) {
        this.age = age;
    }

    public String getWatered() {
        return watered;
    }

    public void setWatered(String watered) {
        this.watered = watered;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isShouldBeWatered() {
        return shouldBeWatered;
    }

    public void setShouldBeWatered(boolean shouldBeWatered) {
        this.shouldBeWatered = shouldBeWatered;
    }
}
