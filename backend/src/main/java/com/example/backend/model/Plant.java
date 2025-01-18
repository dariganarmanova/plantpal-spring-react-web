package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "plant_table")
public class Plant {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String description;
    private Number age;
    private String watered;
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    @JsonIgnore
    private User user;
    public Plant () {

    };
    public Plant(String name, String location, String description, Number age, String watered, User user) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.age = age;
        this.watered= watered;
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
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

}
