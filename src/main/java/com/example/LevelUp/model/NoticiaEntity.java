package com.example.LevelUp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "noticia")
@AllArgsConstructor
@NoArgsConstructor
public class NoticiaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idNoticia;
    private String titulo;
    private String fecha;
    private String resumen;
    private String url;
}