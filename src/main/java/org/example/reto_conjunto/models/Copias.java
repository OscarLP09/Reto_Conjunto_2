package org.example.reto_conjunto.models;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;

@Data
@Entity
@Table(name = "Copias")
public class Copias implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pelicula", nullable = false)
    private Peliculas pelicula;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    private String estado;
    private Integer cantidad;
    private String soporte;



}
