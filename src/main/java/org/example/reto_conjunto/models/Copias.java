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
    private int id;

    // Relación ManyToOne con Pelicula
    @ManyToOne
    @JoinColumn(name = "id_pelicula", nullable = false)
    private Peliculas pelicula;

    // Relación ManyToOne con Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Soporte soporte;


    // Enum para el estado
    public enum Estado {
        DISPONIBLE,
        PRESTADO,
        EN_MANTENIMIENTO
    }

    // Enum para el soporte
    public enum Soporte {
        DVD,
        BLUE_RAY
    }

    @Override
    public String toString() {
        return "Copias{" +
                "id=" + id +
                ", pelicula=" + pelicula +
                ", usuario=" + usuario +
                ", estado=" + estado +
                ", cantidad=" + cantidad +
                ", soporte=" + soporte +
                '}';
    }
}
