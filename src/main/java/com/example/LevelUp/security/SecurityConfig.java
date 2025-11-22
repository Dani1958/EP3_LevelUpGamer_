package com.example.LevelUp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Permite usar @PreAuthorize en controllers
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/api/v1/administrador").permitAll()
                        // Permitidos sin login (ajusta según tus rutas reales de login/registro)
                        .requestMatchers("/api/v1/usuario/login", "/api/v1/usuario", "/api/v1/administrador/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/producto/**").permitAll()
                        // Permisos solo admin para manipular productos
                        //.requestMatchers(HttpMethod.POST, "/api/v1/producto/**").hasAuthority("ADMIN")
                        //.requestMatchers(HttpMethod.PUT, "/api/v1/producto/**").hasAuthority("ADMIN")
                        //.requestMatchers(HttpMethod.DELETE, "/api/v1/producto/**").hasAuthority("ADMIN")
                        // Acceso usuario/administrador a sus rutas
                        .requestMatchers("/api/v1/usuario/privado").hasAuthority("USER")
                        .requestMatchers("/api/v1/usuario/*").hasAuthority("USER")
                        .requestMatchers("/api/v1/administrador/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll()
                );
        // Aquí deberías añadir tu filtro JWT si tienes uno
        return http.build();
    }
}

