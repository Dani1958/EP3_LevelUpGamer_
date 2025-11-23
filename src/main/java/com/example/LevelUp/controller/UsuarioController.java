package com.example.LevelUp.controller;


import com.example.LevelUp.model.AdminInfoDTO;
import com.example.LevelUp.model.AdministradorEntity;
import com.example.LevelUp.model.JwtResponse;
import com.example.LevelUp.model.LoginRequest;
import com.example.LevelUp.model.UsuarioEntity;
import com.example.LevelUp.security.JwtUtil;
import com.example.LevelUp.service.AdministradorService;
import com.example.LevelUp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AdministradorService administradorService;
    @Autowired
    private JwtUtil jwtUtil;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/privado")
    public String solouSER() {
        return "Vista exclusiva para usuarios!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Primero intenta autenticar como usuario
        UsuarioEntity usuario = usuarioService.autenticar(loginRequest.getCorreo(), loginRequest.getPassword());
        if (usuario != null) {
            String token = jwtUtil.generateToken(usuario);
            return ResponseEntity.ok(new JwtResponse(token, "USER", usuario));
        }
        // Si no es usuario, intenta como administrador (por "correo" o "nombre" según tu modelo)
        AdministradorEntity admin = administradorService.autenticar(loginRequest.getCorreo(), loginRequest.getPassword());
        if (admin != null) {
            String token = jwtUtil.generateToken(admin);
            AdminInfoDTO adminDto = new AdminInfoDTO(admin);
            return ResponseEntity.ok(new JwtResponse(token, "ADMIN", admin));
        }
        // Credenciales inválidas
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }


    @GetMapping
    public List<UsuarioEntity> listarUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioEntity> obtenerUsuario(@PathVariable Long idUsuario) {
        UsuarioEntity usuario = usuarioService.findById(idUsuario);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> crearUsuario(@RequestBody UsuarioEntity usuario) {
        try {
            usuario.setRol("USER");
            UsuarioEntity creado = usuarioService.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            // validación de edad y descuento Duoc
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioEntity> actualizarUsuario(@PathVariable Long idUsuario,
                                                           @RequestBody UsuarioEntity usuario) {
        try {
            UsuarioEntity actualizado = usuarioService.update(idUsuario, usuario);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long idUsuario) {
        usuarioService.delete(idUsuario);
        return ResponseEntity.noContent().build();
    }

}
