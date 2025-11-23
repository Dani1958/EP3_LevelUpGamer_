package com.example.LevelUp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "evento")
@AllArgsConstructor
@NoArgsConstructor
public class EventoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEvento;
    private String titulo;
    private String fecha;
    private String lugar;
    private String descripcion;
}
