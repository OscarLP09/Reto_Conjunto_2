package org.example.reto_conjunto.models;

public class PeliculasCopia {
    private String nombrePeli;
    private String estado;
    private Integer cantidad;
    private String soporte;

    // Constructor
    public PeliculasCopia(String nombrePeli, String estado, Integer cantidad, String soporte) {
        this.nombrePeli = nombrePeli;
        this.estado = estado;
        this.cantidad = cantidad;
        this.soporte = soporte;
    }

    // Getters y Setters
    public String getNombrePeli() {
        return nombrePeli;
    }

    public void setNombrePeli(String nombrePeli) {
        this.nombrePeli = nombrePeli;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }
}
