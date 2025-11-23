package com.example.LevelUp.model;

public class CartItemDTO {
    private Long idProducto;
    private int cantidad;

    public CartItemDTO() {} // Constructor vacío requerido por Spring

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
