package com.example.LevelUp.repository;


import com.example.LevelUp.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByCorreo(String correo);

}
