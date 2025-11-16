package com.example.LevelUp.service;

import com.example.LevelUp.model.ItemCarroEntity;
import com.example.LevelUp.repository.ItemCarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCarroService {
    @Autowired
    private ItemCarroRepository itemCarroRepository;

    public ItemCarroEntity save(ItemCarroEntity item) {
        return itemCarroRepository.save(item);
    }

    public List<ItemCarroEntity> findAll() {
        return itemCarroRepository.findAll();
    }

    public ItemCarroEntity findById(Long idItem) {
        return itemCarroRepository.findById(idItem)
                .orElseThrow(() -> new IllegalArgumentException("Ítem de carro no encontrado"));
    }

    //Actualiza un tiem, en este caso la cantidad
    public ItemCarroEntity update(Long idItem, int nuevaCantidad) {
        ItemCarroEntity existente = itemCarroRepository.findById(idItem)
                .orElseThrow(() -> new IllegalArgumentException("Ítem de carro no encontrado"));

        existente.setCantidad(nuevaCantidad);
        return itemCarroRepository.save(existente);
    }

    public void delete(Long idItem) {
        itemCarroRepository.deleteById(idItem);
    }
}
