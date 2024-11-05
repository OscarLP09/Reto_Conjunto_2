package org.example.reto_conjunto.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "peliculas")
public class Peliculas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePeli;
    private String genero;
    private int año;
    private String descripcion;
    private String director;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Copias> copias;

}
