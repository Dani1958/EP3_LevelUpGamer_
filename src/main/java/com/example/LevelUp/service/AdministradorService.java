package com.example.LevelUp.service;

import com.example.LevelUp.model.AdministradorEntity;
import com.example.LevelUp.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository adminRepository;

    public AdministradorEntity save(AdministradorEntity admin) {
        return adminRepository.save(admin);
    }

    public List<AdministradorEntity> findAll() {
        return adminRepository.findAll();
    }

    public AdministradorEntity findById(Long idAdministrador) {
        return adminRepository.findById(idAdministrador)
                .orElseThrow(() -> new IllegalArgumentException("Administrador no encontrado"));
    }

    public AdministradorEntity update(Long idAdministrador, AdministradorEntity nuevosDatos) {
        AdministradorEntity existente = adminRepository.findById(idAdministrador)
                .orElseThrow(() -> new IllegalArgumentException("Administrador no encontrado"));

        existente.setRut(nuevosDatos.getRut());
        existente.setNombre(nuevosDatos.getNombre());
        existente.setApellido(nuevosDatos.getApellido());
        existente.setFecha_nacimiento(nuevosDatos.getFecha_nacimiento());
        existente.setEdad(nuevosDatos.getEdad());

        return adminRepository.save(existente);
    }

    public void delete(Long idAdministrador) {
        adminRepository.deleteById(idAdministrador);
    }
}
