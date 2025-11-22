package com.example.LevelUp.controller;

import com.example.LevelUp.model.EventoEntity;
import com.example.LevelUp.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // Listar todos los eventos
    @GetMapping
    public List<EventoEntity> listarEventos() {
        return eventoService.findAll();
    }

    // Obtener evento por ID
    @GetMapping("/{idEvento}")
    public ResponseEntity<EventoEntity> obtenerEvento(@PathVariable Long idEvento) {
        try {
            EventoEntity evento = eventoService.findById(idEvento);
            return ResponseEntity.ok(evento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nuevo evento
    @PostMapping
    public ResponseEntity<EventoEntity> crearEvento(@RequestBody EventoEntity evento) {
        EventoEntity creado = eventoService.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar evento existente
    @PutMapping("/{idEvento}")
    public ResponseEntity<EventoEntity> actualizarEvento(
            @PathVariable Long idEvento,
            @RequestBody EventoEntity eventoActualizado) {
        try {
            EventoEntity actualizado = eventoService.update(idEvento, eventoActualizado);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar evento
    @DeleteMapping("/{idEvento}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long idEvento) {
        try {
            eventoService.delete(idEvento);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Carga masiva de eventos (bulk insert)
    @PostMapping("/bulk")
    public ResponseEntity<Void> cargarEventosBulk(@RequestBody List<EventoEntity> eventos) {
        eventoService.saveAll(eventos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Buscar eventos por título
    @GetMapping("/titulo/{titulo}")
    public List<EventoEntity> eventosPorTitulo(@PathVariable String titulo) {
        return eventoService.findByTitulo(titulo);
    }

    // Buscar eventos por fecha
    @GetMapping("/fecha/{fecha}")
    public List<EventoEntity> eventosPorFecha(@PathVariable String fecha) {
        return eventoService.findByFecha(fecha);
    }

    // Buscar eventos por lugar
    @GetMapping("/lugar/{lugar}")
    public List<EventoEntity> eventosPorLugar(@PathVariable String lugar) {
        return eventoService.findByLugar(lugar);
    }
}
