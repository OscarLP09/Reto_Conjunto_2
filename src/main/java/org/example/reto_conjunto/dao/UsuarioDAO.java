/**
 * Clase encargada de realizar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre las entidades `Usuario` en la base de datos utilizando Hibernate.
 * Implementa la interfaz `DAO` para la entidad `Usuario`.
 */
package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.models.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {
    private SessionFactory sessionFactory;

    /**
     * Constructor con `SessionFactory` para inicializar la sesión de Hibernate.
     *
     * @param sessionFactory la fábrica de sesiones para la conexión con la base de datos.
     */
    public UsuarioDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Obtiene todos los usuarios registrados en la base de datos.
     *
     * @return una lista de todos los usuarios.
     */
    public List<Usuario> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Usuario> query = session.createQuery("from Usuario", Usuario.class);
            return query.list();
        }
    }

    /**
     * Obtiene un usuario específico por su ID.
     *
     * @param id el ID del usuario a obtener.
     * @return el usuario con el ID especificado, o null si no se encuentra.
     */
    public Usuario findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param usuario el usuario a guardar.
     */
    public void save(Usuario usuario) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(usuario);
            session.getTransaction().commit();
        }
    }

    /**
     * Actualiza un usuario existente en la base de datos.
     *
     * @param usuario el usuario a actualizar.
     */
    public void update(Usuario usuario) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(usuario);
            session.getTransaction().commit();
        }
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param usuario el usuario a eliminar.
     */
    public void delete(Usuario usuario) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(usuario);
            session.getTransaction().commit();
        }
    }

    /**
     * Valida la existencia de un usuario en la base de datos mediante su correo electrónico
     * y contraseña.
     *
     * @param email el correo electrónico del usuario.
     * @param password la contraseña del usuario.
     * @return el usuario si las credenciales son correctas, o null si no se encuentra o
     *         si las credenciales son incorrectas.
     */
    public Usuario validateUsuario(String email, String password) {
        try (Session session = sessionFactory.openSession()) {
            Query<Usuario> query = session.createQuery(
                    "from Usuario where email = :email and contrasena = :contrasena", Usuario.class);
            query.setParameter("email", email);
            query.setParameter("contrasena", password);
            return query.uniqueResult();
        }
    }
}
