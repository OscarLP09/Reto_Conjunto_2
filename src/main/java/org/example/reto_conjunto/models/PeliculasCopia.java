/**
 * Clase que representa una copia de una película.
 * Esta clase contiene información detallada sobre la película, como su título, género, año de lanzamiento,
 * director, estado, cantidad de copias y el soporte en el que está disponible.
 */
package org.example.reto_conjunto.models;

public class PeliculasCopia {

    /**
     * Nombre de la película.
     */
    private String nombrePeli;

    /**
     * Género de la película.
     */
    private String genero;

    /**
     * Año de lanzamiento de la película.
     */
    private int anho;

    /**
     * Director de la película.
     */
    private String director;

    /**
     * Estado de la copia de la película (e.g., disponible, prestado).
     */
    private String estado;

    /**
     * Cantidad de copias disponibles.
     */
    private Integer cantidad;

    /**
     * Soporte en el que está disponible la película (e.g., DVD, Blu-ray).
     */
    private String soporte;

    /**
     * Constructor completo para crear una instancia de PeliculasCopia con todos los atributos.
     *
     * @param nombrePeli el nombre de la película.
     * @param genero el género de la película.
     * @param anho el año de lanzamiento de la película.
     * @param director el director de la película.
     * @param estado el estado de la película.
     * @param cantidad la cantidad de copias disponibles.
     * @param soporte el soporte en el que está la película.
     */
    public PeliculasCopia(String nombrePeli, String genero, int anho, String director, String estado, Integer cantidad, String soporte) {
        this.nombrePeli = nombrePeli;
        this.genero = genero;
        this.anho = anho;
        this.director = director;
        this.estado = estado;
        this.cantidad = cantidad;
        this.soporte = soporte;
    }

    /**
     * Constructor alternativo para crear una instancia de PeliculasCopia con los atributos principales.
     *
     * @param nombrePeli el nombre de la película.
     * @param estado el estado de la película.
     * @param cantidad la cantidad de copias disponibles.
     * @param soporte el soporte en el que está la película.
     */
    public PeliculasCopia(String nombrePeli, String estado, Integer cantidad, String soporte) {
        this.nombrePeli = nombrePeli;
        this.estado = estado;
        this.cantidad = cantidad;
        this.soporte = soporte;
    }

    // Métodos getter y setter

    /**
     * Obtiene el nombre de la película.
     *
     * @return el nombre de la película.
     */
    public String getNombrePeli() {
        return nombrePeli;
    }

    /**
     * Establece el nombre de la película.
     *
     * @param nombrePeli el nombre de la película.
     */
    public void setNombrePeli(String nombrePeli) {
        this.nombrePeli = nombrePeli;
    }

    /**
     * Obtiene el género de la película.
     *
     * @return el género de la película.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género de la película.
     *
     * @param genero el género de la película.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene el año de lanzamiento de la película.
     *
     * @return el año de lanzamiento de la película.
     */
    public int getAnho() {
        return anho;
    }

    /**
     * Establece el año de lanzamiento de la película.
     *
     * @param anho el año de lanzamiento de la película.
     */
    public void setAnho(int anho) {
        this.anho = anho;
    }

    /**
     * Obtiene el director de la película.
     *
     * @return el director de la película.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Establece el director de la película.
     *
     * @param director el director de la película.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Obtiene el estado de la copia de la película.
     *
     * @return el estado de la copia.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la copia de la película.
     *
     * @param estado el estado de la copia.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la cantidad de copias disponibles.
     *
     * @return la cantidad de copias.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de copias disponibles.
     *
     * @param cantidad la cantidad de copias.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el soporte en el que está disponible la película.
     *
     * @return el soporte de la película.
     */
    public String getSoporte() {
        return soporte;
    }

    /**
     * Establece el soporte en el que está disponible la película.
     *
     * @param soporte el soporte de la película.
     */
    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }
}
