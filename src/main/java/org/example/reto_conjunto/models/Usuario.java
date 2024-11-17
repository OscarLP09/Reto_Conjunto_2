/**
 * Clase que representa a un usuario en el sistema.
 * Esta entidad se mapea a la tabla "Usuario" en la base de datos y contiene la información
 * básica de un usuario, así como la relación con las copias de películas asociadas.
 */
package org.example.reto_conjunto.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {

    /**
     * Identificador único del usuario. Se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Apellidos del usuario.
     */
    private String apellidos;

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Contraseña del usuario.
     */
    private String contrasena;

    /**
     * Lista de copias de películas asociadas al usuario.
     * La relación es de uno a muchos y se mapea por el atributo "usuario" en la clase {@link Copias}.
     * Las operaciones en cascada se aplican y la carga de las copias es perezosa (LAZY).
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Copias> copias;
}
