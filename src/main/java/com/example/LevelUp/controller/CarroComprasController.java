package com.example.LevelUp.controller;

import com.example.LevelUp.model.CarroComprasEntity;
import com.example.LevelUp.model.ItemCarroEntity;
import com.example.LevelUp.model.UsuarioEntity;
import com.example.LevelUp.service.CarroComprasService;
import com.example.LevelUp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carro_compras")
public class CarroComprasController {
    @Autowired
    private CarroComprasService carroComprasService;
    private UsuarioService usuarioService;

    // Obtener el carro de compras por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<CarroComprasEntity> obtenerCarroPorUsuario(@PathVariable Long idUsuario) {
        // Debes tener usuarioService para buscar UsuarioEntity
        UsuarioEntity usuario = usuarioService.findById(idUsuario);
        CarroComprasEntity carro = carroComprasService.obtenerCarroPorUsuario(usuario);
        return carro != null ? ResponseEntity.ok(carro) : ResponseEntity.notFound().build();
    }

    // Obtener todos los items del carro por usuario
    @GetMapping("/usuario/{idUsuario}/items")
    public ResponseEntity<List<ItemCarroEntity>> obtenerItemsCarroPorUsuario(@PathVariable Long idUsuario) {
        UsuarioEntity usuario = usuarioService.findById(idUsuario);
        List<ItemCarroEntity> items = carroComprasService.obtenerItemsCarroPorUsuario(usuario);
        return ResponseEntity.ok(items); // Lista vacía si no hay items
    }

    // Agregar producto al carro
    @PostMapping
    public ResponseEntity<CarroComprasEntity> agregarAlCarro(@RequestBody CarroComprasEntity carro) {
        CarroComprasEntity guardado = carroComprasService.save(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // Actualizar cantidad o item del carro
    @PutMapping("/{idCarro}")
    public ResponseEntity<CarroComprasEntity> actualizarItem(@PathVariable Long idCarro,
                                                             @RequestBody CarroComprasEntity carroNuevo) {
        try {
            CarroComprasEntity actualizado = carroComprasService.update(idCarro, carroNuevo);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idCarro}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long idCarro) {
        carroComprasService.delete(idCarro);
        return ResponseEntity.noContent().build();
    }
}
