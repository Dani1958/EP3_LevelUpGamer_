package com.example.LevelUp.service;

import com.example.LevelUp.model.ProductoEntity;
import com.example.LevelUp.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoEntity> findAll() {
        return productoRepository.findAll();
    }

    public ProductoEntity findById(Long idProducto) {
        return productoRepository.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));
    }

    public ProductoEntity save(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    public ProductoEntity update(Long idProducto, ProductoEntity productoNuevo) {
        ProductoEntity existente = productoRepository.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));

        existente.setNombre(productoNuevo.getNombre());
        existente.setCategoria(productoNuevo.getCategoria());
        existente.setPrecio(productoNuevo.getPrecio());
        existente.setMarca(productoNuevo.getMarca());
        existente.setStock(productoNuevo.getStock());

        return productoRepository.save(existente);
    }

    public void delete(Long idProducto) {
        productoRepository.deleteById(idProducto);
    }

    public List<ProductoEntity> findByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<ProductoEntity> findByMarca(String marca) {
        return productoRepository.findByMarca(marca);
    }

    public void saveAll(List<ProductoEntity> productos) {
        productoRepository.saveAll(productos);
    }
}
