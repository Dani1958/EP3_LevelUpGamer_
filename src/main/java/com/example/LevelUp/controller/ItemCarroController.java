package com.example.LevelUp.controller;

import com.example.LevelUp.model.ItemCarroEntity;
import com.example.LevelUp.service.ItemCarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemCarroController {
    @Autowired
    private ItemCarroService itemCarroService;

    // Listar todos los ítems de todos los carros
    @GetMapping
    public List<ItemCarroEntity> listarTodosLosItems() {
        return itemCarroService.findAll();
    }

    // Obtener un ítem del carro por su ID
    @GetMapping("/{idItemCarro}")
    public ResponseEntity<ItemCarroEntity> obtenerItemPorId(@PathVariable Long idItemCarro) {
        try {
            ItemCarroEntity item = itemCarroService.findById(idItemCarro);
            return ResponseEntity.ok(item);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ItemCarroEntity> crearItem(@RequestBody ItemCarroEntity item) {
        ItemCarroEntity creado = itemCarroService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCarroEntity> actualizarCantidad(@PathVariable Long id,
                                                              @RequestBody ItemCarroEntity itemNuevo) {
        try {
            ItemCarroEntity actualizado = itemCarroService.update(id, itemNuevo.getCantidad());
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long id) {
        itemCarroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
