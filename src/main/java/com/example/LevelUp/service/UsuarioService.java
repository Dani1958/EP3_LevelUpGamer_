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

    @Autowired //inyectar una dependecia
    private UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> findAll(){ return usuarioRepository.findAll(); }

    public UsuarioEntity findById(Long idUsuario) {

        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    //para validar la edad
    private boolean esMayorDeEdad(LocalDate fecha_nacimiento) {
        return Period.between(fecha_nacimiento, LocalDate.now()).getYears() >=18;
    }

    //VALIDACIONES de reglas de negocio
    public UsuarioEntity save(UsuarioEntity user) {
        //Validar edad mayor a 18
        if (!esMayorDeEdad(user.getFecha_nacimiento())) {
            throw new IllegalArgumentException("El usuario debe ser mayor de 18 años.");
        }
        // Aplicar descuento si es de Duoc (booleano)
        if(user.getCorreo().endsWith("@duocuc.cl")) {
            user.setDescuentoDuoc(true);
        } else {
            user.setDescuentoDuoc(false);
        }
        //guardar usuario
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
        //Actualizar cambios
        existente.setNombre(newUser.getNombre());
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
