package com.example.backend.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.backend.model.JwtResponse;
import com.example.backend.model.User;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired 
    private UserService userService;
    @PostMapping("/signup") 
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.createUser(user.getUsername(), user.getPassword(), user.getEmail());
            return ResponseEntity.ok("User registered successfuly");
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
             String token = userService.authenticateUser(user.getUsername(), user.getPassword());
             return ResponseEntity.ok(new JwtResponse(token));
        } catch(RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
