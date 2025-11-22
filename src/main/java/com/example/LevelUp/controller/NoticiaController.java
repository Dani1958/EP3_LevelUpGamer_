package com.example.LevelUp.controller;

import com.example.LevelUp.model.NoticiaEntity;
import com.example.LevelUp.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/noticia")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    // Listar todas las noticias
    @GetMapping
    public List<NoticiaEntity> listarNoticias() {
        return noticiaService.findAll();
    }

    // Obtener noticia por ID
    @GetMapping("/{idNoticia}")
    public ResponseEntity<NoticiaEntity> obtenerNoticia(@PathVariable Long idNoticia) {
        try {
            NoticiaEntity noticia = noticiaService.findById(idNoticia);
            return ResponseEntity.ok(noticia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nueva noticia
    @PostMapping
    public ResponseEntity<NoticiaEntity> crearNoticia(@RequestBody NoticiaEntity noticia) {
        NoticiaEntity creada = noticiaService.save(noticia);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // Actualizar noticia existente
    @PutMapping("/{idNoticia}")
    public ResponseEntity<NoticiaEntity> actualizarNoticia(
            @PathVariable Long idNoticia,
            @RequestBody NoticiaEntity noticiaActualizada) {
        try {
            NoticiaEntity actualizada = noticiaService.update(idNoticia, noticiaActualizada);
            return ResponseEntity.ok(actualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar noticia
    @DeleteMapping("/{idNoticia}")
    public ResponseEntity<Void> eliminarNoticia(@PathVariable Long idNoticia) {
        try {
            noticiaService.delete(idNoticia);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Carga masiva de noticias (bulk insert)
    @PostMapping("/bulk")
    public ResponseEntity<Void> cargarNoticiasBulk(@RequestBody List<NoticiaEntity> noticias) {
        noticiaService.saveAll(noticias);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Buscar noticias por título
    @GetMapping("/titulo/{titulo}")
    public List<NoticiaEntity> noticiasPorTitulo(@PathVariable String titulo) {
        return noticiaService.findByTitulo(titulo);
    }

    // Buscar noticias por fecha
    @GetMapping("/fecha/{fecha}")
    public List<NoticiaEntity> noticiasPorFecha(@PathVariable String fecha) {
        return noticiaService.findByFecha(fecha);
    }
}
