package com.example.LevelUp.repository;


import com.example.LevelUp.model.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<AdministradorEntity, Long> {
    AdministradorEntity findByCorreo(String correo);

}
