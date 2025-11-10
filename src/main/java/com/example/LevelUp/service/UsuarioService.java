package com.example.LevelUp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LevelUp.model.Usuario;
import com.example.LevelUp.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired //inyectar una dependecia
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).get();
    }

    public Usuario save(Usuario user) {
        return usuarioRepository.save(user);
    }

    public Usuario delete(Long idUsuario, Usuario user) {
        user.setIdUsuario(idUsuario);
        return usuarioRepository.save(user);
    }

    //update

    //Validar que edad sea sobre 18 años
}
