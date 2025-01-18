package com.example.backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret_key;
    private static final long expiration_time=1000*60*60;
    private SecretKey key;
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis()+expiration_time))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key).build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
    public boolean validateJwtToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key).build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration()
            .before(new Date());
    }
}
