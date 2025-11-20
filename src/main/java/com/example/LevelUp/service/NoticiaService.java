package com.example.LevelUp.service;

import com.example.LevelUp.model.NoticiaEntity;
import com.example.LevelUp.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaService {
    
    @Autowired
    private NoticiaRepository noticiaRepository;

    // Obtener todas las noticias
    public List<NoticiaEntity> findAll() {
        return noticiaRepository.findAll();
    }

    // Obtener noticia por ID
    public NoticiaEntity findById(Long id) {
        return noticiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Noticia no encontrada con ID: " + id));
    }

    // Crear nueva noticia
    public NoticiaEntity save(NoticiaEntity noticia) {
        return noticiaRepository.save(noticia);
    }

    // Actualizar noticia existente
    public NoticiaEntity update(Long id, NoticiaEntity noticiaActualizada) {
        NoticiaEntity noticia = findById(id);
        noticia.setTitulo(noticiaActualizada.getTitulo());
        noticia.setFecha(noticiaActualizada.getFecha());
        noticia.setResumen(noticiaActualizada.getResumen());
        noticia.setUrl(noticiaActualizada.getUrl());
        return noticiaRepository.save(noticia);
    }

    // Eliminar noticia
    public void delete(Long id) {
        NoticiaEntity noticia = findById(id);
        noticiaRepository.delete(noticia);
    }

    // Guardar múltiples noticias (bulk insert)
    public void saveAll(List<NoticiaEntity> noticias) {
        noticiaRepository.saveAll(noticias);
    }

    // Buscar por título
    public List<NoticiaEntity> findByTitulo(String titulo) {
        return noticiaRepository.findByTituloContaining(titulo);
    }

    // Buscar por fecha
    public List<NoticiaEntity> findByFecha(String fecha) {
        return noticiaRepository.findByFecha(fecha);
    }
}
