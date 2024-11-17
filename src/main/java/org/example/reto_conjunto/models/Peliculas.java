/**
 * Clase que representa una entidad de película en el sistema.
 * Esta clase contiene información sobre la película, como su nombre, género, año de lanzamiento,
 * descripción, director y las copias asociadas.
 */
package org.example.reto_conjunto.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "peliculas")
public class Peliculas implements Serializable {

    /**
     * Identificador único de la película.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la película.
     */
    @Column(name = "nombre_peli")
    private String nombrePeli;

    /**
     * Género de la película (e.g., comedia, drama, ciencia ficción).
     */
    private String genero;

    /**
     * Año de lanzamiento de la película.
     */
    private int año;

    /**
     * Descripción de la película, que incluye detalles sobre la trama o características relevantes.
     */
    private String descripcion;

    /**
     * Nombre del director de la película.
     */
    private String director;

    /**
     * Lista de copias asociadas a la película.
     * Cada copia puede tener un estado diferente y otras características relacionadas.
     */
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Copias> copias;
}
