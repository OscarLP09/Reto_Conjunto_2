package org.example.reto_conjunto.models;

public class PeliculasCopia {
    private String nombrePeli;
    private String estado;
    private int cantidad;
    private String soporte;

    public PeliculasCopia() {
        this.nombrePeli = "";
        this.estado = "";
        this.cantidad = 0;
        this.soporte = "";
    }

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }
}
