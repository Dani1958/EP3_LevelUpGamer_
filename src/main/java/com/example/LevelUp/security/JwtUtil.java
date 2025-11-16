package com.example.LevelUp.security;

import com.example.LevelUp.model.AdministradorEntity;
import com.example.LevelUp.model.UsuarioEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "clave_secreta";
    private final long EXPIRATION_TIME = 86400000; // 24h

    public String generateToken(UsuarioEntity usuario) {
        return Jwts.builder()
                .setSubject(usuario.getCorreo())
                .claim("rol", usuario.getRol())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String generateToken(AdministradorEntity admin) {
        return Jwts.builder()
                .setSubject(admin.getNombre())
                .claim("rol", admin.getRol()) // Guardas el rol en el JWT
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
