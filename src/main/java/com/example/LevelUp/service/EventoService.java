package com.example.LevelUp.service;

import com.example.LevelUp.model.EventoEntity;
import com.example.LevelUp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;

    // Obtener todos los eventos
    public List<EventoEntity> findAll() {
        return eventoRepository.findAll();
    }

    // Obtener evento por ID
    public EventoEntity findById(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado con ID: " + id));
    }

    // Crear nuevo evento
    public EventoEntity save(EventoEntity evento) {
        return eventoRepository.save(evento);
    }

    // Actualizar evento existente
    public EventoEntity update(Long id, EventoEntity eventoActualizado) {
        EventoEntity evento = findById(id);
        evento.setTitulo(eventoActualizado.getTitulo());
        evento.setFecha(eventoActualizado.getFecha());
        evento.setLugar(eventoActualizado.getLugar());
        evento.setDescripcion(eventoActualizado.getDescripcion());
        return eventoRepository.save(evento);
    }

    // Eliminar evento
    public void delete(Long id) {
        EventoEntity evento = findById(id);
        eventoRepository.delete(evento);
    }

    // Guardar múltiples eventos (bulk insert)
    public void saveAll(List<EventoEntity> eventos) {
        eventoRepository.saveAll(eventos);
    }

    // Buscar por título
    public List<EventoEntity> findByTitulo(String titulo) {
        return eventoRepository.findByTituloContaining(titulo);
    }

    // Buscar por fecha
    public List<EventoEntity> findByFecha(String fecha) {
        return eventoRepository.findByFecha(fecha);
    }

    // Buscar por lugar
    public List<EventoEntity> findByLugar(String lugar) {
        return eventoRepository.findByLugar(lugar);
    }
}
