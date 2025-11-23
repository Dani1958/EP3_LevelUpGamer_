package com.example.LevelUp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //lombok que hace todos los getter y setter
@Table(name = "producto")
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idProducto;
    private String nombre; 
    private String descripcion;
    private Boolean envioGratis;
    private double precio;
    private double descuento;
    private String imagen;
    private String marca;
    private String categoria;
    private String juego;
    private String estado;
    private int stock;
}
