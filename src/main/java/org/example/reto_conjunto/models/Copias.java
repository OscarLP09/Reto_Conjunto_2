/**
 * Clase que representa una copia de una película en el sistema.
 * Esta clase contiene información sobre el estado, la cantidad y el soporte de una copia,
 * así como las relaciones con las entidades `Peliculas` y `Usuario`.
 */
package org.example.reto_conjunto.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Copias")
public class Copias implements Serializable {

    /**
     * Identificador único de la copia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relación muchos a uno con la entidad `Peliculas`.
     * Representa la película a la que pertenece esta copia.
     */
    @ManyToOne
    @JoinColumn(name = "id_pelicula", nullable = false)
    private Peliculas pelicula;

    /**
     * Relación muchos a uno con la entidad `Usuario`.
     * Representa el usuario que tiene la copia de la película.
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    /**
     * Estado de la copia de la película (e.g., disponible, prestado, en mantenimiento).
     */
    private String estado;

    /**
     * Cantidad de copias disponibles o asignadas.
     */
    private Integer cantidad;

    /**
     * Tipo de soporte en el que está disponible la copia (e.g., DVD, Blu-ray, digital).
     */
    private String soporte;
}
