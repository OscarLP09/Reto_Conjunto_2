package org.example.reto_conjunto.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="Peliculas")
public class Peliculas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre_peli;
    private String genero;
    private Integer year;
    private String descripcion;
    private String director;

    @OneToMany(mappedBy = "peliculas", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Copias> copias;
}
