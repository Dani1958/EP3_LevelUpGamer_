package com.example.LevelUp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "administrador")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAdministrador;
    private String correo;
    private String rut;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private int edad;
    private String password;
    private String rol;
}
