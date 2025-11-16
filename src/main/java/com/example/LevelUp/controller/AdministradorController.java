package com.example.LevelUp.controller;

import com.example.LevelUp.model.AdministradorEntity;
import com.example.LevelUp.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/administrador")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<AdministradorEntity> listarAdministradores() {
        return administradorService.findAll();
    }

    @GetMapping("/{idAdministrador}")
    public ResponseEntity<AdministradorEntity> obtenerAdministrador(@PathVariable Long idAdministrador) {
        try {
            AdministradorEntity admin = administradorService.findById(idAdministrador);
            return ResponseEntity.ok(admin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AdministradorEntity> crearAdministrador(@RequestBody AdministradorEntity admin) {
        AdministradorEntity creado = administradorService.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{idAdministrador}")
    public ResponseEntity<AdministradorEntity> actualizarAdministrador(@PathVariable Long idAdministrador,
                                                                       @RequestBody AdministradorEntity actualizacion) {
        try {
            AdministradorEntity actualizado = administradorService.update(idAdministrador, actualizacion);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idAdministrador}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable Long idAdministrador) {
        administradorService.delete(idAdministrador);
        return ResponseEntity.noContent().build();
    }
}
