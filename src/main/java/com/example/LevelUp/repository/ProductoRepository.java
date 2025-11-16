package com.example.LevelUp.repository;

import com.example.LevelUp.model.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    List<ProductoEntity> findByCategoria(String categoria);
    List<ProductoEntity> findByMarca(String marca);
}
