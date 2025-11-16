package com.example.LevelUp.controller;

import com.example.LevelUp.model.ProductoEntity;
import com.example.LevelUp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<ProductoEntity> listarProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoEntity> obtenerProducto(@PathVariable Long idProducto) {
        try {
            ProductoEntity producto = productoService.findById(idProducto);
            return ResponseEntity.ok(producto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductoEntity> crearProducto(@RequestBody ProductoEntity producto) {
        ProductoEntity creado = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<ProductoEntity> actualizarProducto(@PathVariable Long idProducto,
                                                             @RequestBody ProductoEntity productoNuevo) {
        try {
            ProductoEntity actualizado = productoService.update(idProducto, productoNuevo);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long idProducto) {
        productoService.delete(idProducto);
        return ResponseEntity.noContent().build();
    }

    // Listar productos por categoría
    @GetMapping("/categoria/{categoria}")
    public List<ProductoEntity> productosPorCategoria(@PathVariable String categoria) {
        return productoService.findByCategoria(categoria);
    }

    // Listar productos por marca
    @GetMapping("/marca/{marca}")
    public List<ProductoEntity> productosPorMarca(@PathVariable String marca) {
        return productoService.findByMarca(marca);
    }
}
