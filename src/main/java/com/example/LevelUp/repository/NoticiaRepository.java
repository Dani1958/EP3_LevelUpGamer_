package com.example.LevelUp.repository;

import com.example.LevelUp.model.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<NoticiaEntity, Long> {
    // Métodos personalizados opcionales
    List<NoticiaEntity> findByTituloContaining(String titulo);
    List<NoticiaEntity> findByFecha(String fecha);
}
