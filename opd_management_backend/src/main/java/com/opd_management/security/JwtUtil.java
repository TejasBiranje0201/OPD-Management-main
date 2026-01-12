package com.opd_management.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.opd_management.entities.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {

    private final String SECRET = "ThisIsASecretKeyForJwtTokenGeneration12345"; // 32+ chars
    private final long EXPIRATION = 1000 * 60 * 60 * 24; // 1 day

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Generate JWT token
    public String generateToken(String email,Role role) {
        return Jwts.builder()
                .setSubject(email) // usually email or username
                .claim("role", role)
                .setIssuedAt(new Date()) // token creation time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    // Extract email
    public String extractEmail(String token) {
        return parseToken(token).getBody().getSubject();
    }

    // Validate token
    public boolean isValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //Extract Role from JWT
    public String extractRole(String token) {
        return parseToken(token).getBody().get("role", String.class);
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
}
