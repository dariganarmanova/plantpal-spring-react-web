package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.repository.UserRepository;
import com.example.backend.util.JwtUtil;
import com.example.backend.model.User;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder; // Injecting PasswordEncoder

    public User createUser(String username, String password, String email) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Encrypting the password
        user.setEmail(email);
        
        return userRepository.save(user);
    }

    public String authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        
        if (!passwordEncoder.matches(password, user.getPassword())) { // Validating the password
            throw new RuntimeException("Invalid credentials");
        }
        
        return jwtUtil.generateToken(username);
    }
}
