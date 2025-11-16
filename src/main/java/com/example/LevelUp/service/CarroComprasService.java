package com.example.LevelUp.service;

import com.example.LevelUp.model.CarroComprasEntity;
import com.example.LevelUp.model.ItemCarroEntity;
import com.example.LevelUp.model.UsuarioEntity;
import com.example.LevelUp.repository.CarroComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CarroComprasService {
    @Autowired
    CarroComprasRepository carroRepository;

    //No corresponde una busqueda por idCarro ya que este siempre esta y es solo 1, pero se puede "buscar" por usuario
    public CarroComprasEntity obtenerCarroPorUsuario(UsuarioEntity usuario) {
        return carroRepository.findByIdUsuario(usuario);
    }

    public List<ItemCarroEntity> obtenerItemsCarroPorUsuario(UsuarioEntity usuario) {
        CarroComprasEntity carro = carroRepository.findByIdUsuario(usuario);
        return carro != null ? carro.getItems() : Collections.emptyList();
    }

    public CarroComprasEntity save(CarroComprasEntity carroCompras) {
        //calcular el total
        double total = carroCompras.getCantidad() * carroCompras.getIdProducto().getPrecio();
        carroCompras.setTotalCompra(total);
        carroCompras.setFecha(new Date());
        return carroRepository.save(carroCompras);
    }

    public CarroComprasEntity update(Long idCarro, CarroComprasEntity carroNuevo) {
        CarroComprasEntity existente = carroRepository.findById(idCarro)
                .orElseThrow(() -> new IllegalArgumentException("Carro no disponible"));

        existente.setCantidad(carroNuevo.getCantidad());
        existente.setIdProducto(carroNuevo.getIdProducto());
        existente.setIdUsuario(carroNuevo.getIdUsuario());
        // Recalcular el total
        double total = existente.getCantidad() * existente.getIdProducto().getPrecio();
        existente.setTotalCompra(total);
        existente.setFecha(new Date());

        return carroRepository.save(existente);
    }

    //Eliminar item del carro
    public void delete(Long idCarro) {
        carroRepository.deleteById(idCarro);
    }



}
