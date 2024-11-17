/**
 * Clase que maneja las operaciones de acceso a datos relacionadas con la entidad `Copias`.
 * Esta clase interactúa con la base de datos para realizar operaciones CRUD sobre las copias de películas
 * asociadas a usuarios.
 */
package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.HibernateUtil;
import org.example.reto_conjunto.models.Copias;
import org.example.reto_conjunto.models.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

public class CopiasDAO {

    /**
     * Instancia de la fábrica de sesiones de Hibernate.
     */
    private static SessionFactory sessionFactory;

    /**
     * Constructor que recibe una instancia de `SessionFactory` para la creación de sesiones de Hibernate.
     *
     * @param sessionFactory la fábrica de sesiones de Hibernate.
     */
    public CopiasDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Obtiene una copia específica de una película asociada a un usuario.
     *
     * @param usuarioId el ID del usuario que posee la copia.
     * @param peliculaId el ID de la película cuya copia se desea obtener.
     * @return la copia de la película asociada al usuario, o null si no se encuentra.
     */
    public static Copias obtenerCopiaPorUsuarioYPelicula(int usuarioId, int peliculaId) {
        Copias copia = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Copias c WHERE c.usuario.id = :usuarioId AND c.pelicula.id = :peliculaId";
            Query<Copias> query = session.createQuery(hql, Copias.class);
            query.setParameter("usuarioId", usuarioId);
            query.setParameter("peliculaId", peliculaId);
            copia = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copia;
    }

    /**
     * Obtiene todas las copias asociadas a un usuario.
     *
     * @param usuario el usuario cuya lista de copias se desea obtener.
     * @return una lista de copias asociadas al usuario.
     */
    public List<Copias> findAllByUsuario(Usuario usuario) {
        try (Session session = sessionFactory.openSession()) {
            Query<Copias> query = session.createQuery("from Copias where usuario = :usuario", Copias.class);
            query.setParameter("usuario", usuario);
            return query.list();
        }
    }

    /**
     * Guarda una nueva copia en la base de datos.
     *
     * @param copia la copia que se desea guardar.
     */
    public void save(Copias copia) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(copia);
            session.getTransaction().commit();
        }
    }

    /**
     * Elimina una copia de la base de datos.
     *
     * @param copia la copia que se desea eliminar.
     */
    public void delete(Copias copia) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(copia);
            session.getTransaction().commit();
        }
    }

    /**
     * Actualiza una copia existente en la base de datos.
     *
     * @param copia la copia que se desea actualizar.
     */
    public void update(Copias copia) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(copia);
            session.getTransaction().commit();
        }
    }

    /**
     * Obtiene todas las copias de películas, incluyendo los datos de las películas asociadas.
     *
     * @return una lista de copias con las películas asociadas.
     */
    public List<Copias> obtenerCopiasConPeliculas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Copias> copias = null;

        try {
            String hql = "FROM Copias c JOIN FETCH c.pelicula";
            Query<Copias> query = session.createQuery(hql, Copias.class);
            copias = query.getResultList();
        } finally {
            session.close();
        }

        return copias;
    }
}
