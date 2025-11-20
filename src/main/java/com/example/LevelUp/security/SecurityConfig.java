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
                .cors(cors -> {}) // Usa la configuración por defecto o define un bean CorsConfigurationSource
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/auth/**", "/register").permitAll() // Endpoints públicos para login y registro
                        .requestMatchers(HttpMethod.GET, "/api/v1/producto/**").permitAll() // Permitir ver productos sin login
                        .requestMatchers(HttpMethod.POST, "/api/v1/producto/bulk").permitAll() // <--- Esta línea es clave
                        .requestMatchers(HttpMethod.POST, "/api/v1/producto").permitAll() // Opcional, para crear por POST normal
                        .requestMatchers(HttpMethod.GET, "/api/v1/noticia/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/noticia/bulk").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/evento/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/evento/bulk").permitAll()
                        .requestMatchers("/usuarios/**").hasRole("USER")  // Sólo usuarios logueados
                        .requestMatchers("/administradores/**").hasRole("ADMIN") // Sólo admin
                        .anyRequest().permitAll()
                );
        // Debes agregar el filtro JWT aquí si lo implementas
        return http.build();
    }
}

