package org.example.reto_conjunto.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="Usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasena;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Copias> copias;

    public void addCopias(Copias copia) {
        copia.setUsuario(this);
        this.copias.add(copia);
    }
}
