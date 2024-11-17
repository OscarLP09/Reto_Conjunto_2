package org.example.reto_conjunto.models;

public class PeliculasCopia {
    private String nombrePeli;
    private String genero;
    private int anho;
    private String director;
    private String estado;
    private Integer cantidad;
    private String soporte;


    public PeliculasCopia(String nombrePeli, String genero, int anho, String director, String estado, Integer cantidad, String soporte) {
        this.nombrePeli = nombrePeli;
        this.genero = genero;
        this.anho = anho;
        this.director = director;
        this.estado = estado;
        this.cantidad = cantidad;
        this.soporte = soporte;
    }

    public PeliculasCopia(String nombrePeli, String estado, Integer cantidad, String soporte) {
        this.nombrePeli = nombrePeli;
        this.estado = estado;
        this.cantidad = cantidad;
        this.soporte = soporte;
    }




    public String getNombrePeli() {
        return nombrePeli;
    }

    public void setNombrePeli(String nombrePeli) {
        this.nombrePeli = nombrePeli;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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
