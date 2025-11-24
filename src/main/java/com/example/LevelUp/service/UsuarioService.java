package com.example.LevelUp.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LevelUp.model.UsuarioEntity;
import com.example.LevelUp.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity autenticar(String correo, String password) {
        UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }

    public List<UsuarioEntity> findAll(){ return usuarioRepository.findAll(); }

    public UsuarioEntity findById(Long idUsuario) {

        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    private boolean esMayorDeEdad(LocalDate fecha_nacimiento) {
        return Period.between(fecha_nacimiento, LocalDate.now()).getYears() >=18;
    }

    public UsuarioEntity save(UsuarioEntity user) {
        if (!esMayorDeEdad(user.getFecha_nacimiento())) {
            throw new IllegalArgumentException("El usuario debe ser mayor de 18 años.");
        }
        if(user.getCorreo().endsWith("@duocuc.cl")) {
            user.setDescuentoDuoc(true);
        } else {
            user.setDescuentoDuoc(false);
        }
        return usuarioRepository.save(user);
    }

    public void delete(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public UsuarioEntity update(Long idUsuario, UsuarioEntity newUser) {
        UsuarioEntity existente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        if (!esMayorDeEdad(existente.getFecha_nacimiento())) {
            throw new IllegalArgumentException("El usuario debe ser amyor de 18 años.");
        }
        existente.setNombre_completo(newUser.getNombre_completo());
        existente.setCorreo(newUser.getCorreo());
        existente.setFecha_nacimiento(newUser.getFecha_nacimiento());

        if (newUser.getCorreo().endsWith("@duocuc.cl")) {
            existente.setDescuentoDuoc(true);
        } else {
            existente.setDescuentoDuoc(false);
        }

        return usuarioRepository.save(existente);
    }


}
