package com.example.LevelUp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminInfoDTO {
    private Long idAdministrador;
    private String correo;
    private String rut;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private int edad;
    private String rol;

    public AdminInfoDTO(AdministradorEntity admin) {
        this.idAdministrador = admin.getIdAdministrador();
        this.correo = admin.getCorreo();
        this.rut = admin.getRut();
        this.nombre = admin.getNombre();
        this.apellido = admin.getApellido();
        this.fecha_nacimiento = admin.getFecha_nacimiento() != null ? admin.getFecha_nacimiento().toString() : null;
        this.edad = admin.getEdad();
        this.rol = admin.getRol();
    }

}