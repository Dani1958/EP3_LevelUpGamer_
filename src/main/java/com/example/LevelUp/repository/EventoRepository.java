package com.example.LevelUp.repository;

import com.example.LevelUp.model.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
    // Métodos personalizados opcionales
    List<EventoEntity> findByTituloContaining(String titulo);
    List<EventoEntity> findByFecha(String fecha);
    List<EventoEntity> findByLugar(String lugar);
}
