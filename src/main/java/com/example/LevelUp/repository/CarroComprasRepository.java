package com.example.LevelUp.repository;

import com.example.LevelUp.model.CarroComprasEntity;
import com.example.LevelUp.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroComprasRepository extends JpaRepository<CarroComprasEntity, Long> {

    CarroComprasEntity findByIdUsuario(UsuarioEntity usuario);
}
