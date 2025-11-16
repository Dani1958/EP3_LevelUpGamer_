package com.example.LevelUp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data //lombok que hace todos los getter y setter
@Table(name = "carro_compras")
@AllArgsConstructor
@NoArgsConstructor
public class CarroComprasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarro;
    private int cantidad;
    private Date fecha;
    private double totalCompra;

    @ManyToOne
    UsuarioEntity idUsuario;

    @ManyToOne
    ProductoEntity idProducto;

    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL)
    private List<ItemCarroEntity> items; //lista que contiene los items del carro
}
