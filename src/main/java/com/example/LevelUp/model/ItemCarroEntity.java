package com.example.LevelUp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "items_carro")
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idItemCarro;

    @ManyToOne
    UsuarioEntity usuario;

    @ManyToOne
    CarroComprasEntity carro;
    int cantidad;
}
