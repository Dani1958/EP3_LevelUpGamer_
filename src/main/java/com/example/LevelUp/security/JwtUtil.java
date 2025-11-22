package com.example.LevelUp.security;

import com.example.LevelUp.model.AdministradorEntity;
import com.example.LevelUp.model.UsuarioEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.KeyStore.SecretKeyEntry;
import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long EXPIRATION_TIME = 86400000; // 24h

    public String generateToken(UsuarioEntity usuario) {
        return Jwts.builder()
                .setSubject(usuario.getCorreo())
                .claim("rol", "USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String generateToken(AdministradorEntity admin) {
        return Jwts.builder()
                .setSubject(admin.getNombre())
                .claim("rol", "ADMIN") // Guardas el rol en el JWT
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Utilidad para validar y obtener claims del token
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractRol(String token) {
        return extractAllClaims(token).get("rol", String.class);
    }
}
