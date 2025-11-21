package com.example.LevelUp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data //lombok que hace todos los getter y setter
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    private String rut;
    private String nombre_completo;
    private LocalDate fecha_nacimiento;
    private String correo;
    private boolean descuentoDuoc;
    private String password;
    private String rol;
}
