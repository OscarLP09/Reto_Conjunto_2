/**
 * Clase encargada de realizar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre las entidades `Peliculas` en la base de datos utilizando Hibernate.
 * Implementa la interfaz `DAO` para la entidad `Peliculas`.
 */
package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.HibernateUtil;
import org.example.reto_conjunto.models.Peliculas;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PeliculasDAO implements DAO<Peliculas>  {

    private SessionFactory sessionFactory;

    /**
     * Constructor sin parámetros.
     */
    public PeliculasDAO() {

    }

    /**
     * Constructor con `SessionFactory` para inicializar la sesión de Hibernate.
     *
     * @param sessionFactory la fábrica de sesiones para la conexión con la base de datos.
     */
    public PeliculasDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Obtiene todas las películas registradas en la base de datos.
     *
     * @return una lista de todas las películas.
     */
    public List<Peliculas> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Peliculas> query = session.createQuery("from Peliculas", Peliculas.class);
            return query.list();
        }
    }

    /**
     * Obtiene una película específica por su ID.
     *
     * @param id el ID de la película a obtener.
     * @return la película con el ID especificado, o null si no se encuentra.
     */
    public Peliculas findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Peliculas.class, id);
        }
    }

    /**
     * Guarda una nueva película en la base de datos.
     *
     * @param pelicula la película a guardar.
     */
    public void save(Peliculas pelicula) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(pelicula);
            session.getTransaction().commit();
        }
    }

    /**
     * Actualiza una película existente en la base de datos.
     *
     * @param pelicula la película a actualizar.
     */
    public void update(Peliculas pelicula) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(pelicula);
            session.getTransaction().commit();
        }
    }

    /**
     * Elimina una película de la base de datos.
     *
     * @param pelicula la película a eliminar.
     */
    public void delete(Peliculas pelicula) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(pelicula);
            session.getTransaction().commit();
        }
    }

    /**
     * Obtiene todas las películas de un género específico.
     *
     * @param genero el género de las películas a obtener.
     * @return una lista de películas que pertenecen al género especificado.
     */
    public List<Peliculas> findByGenero(String genero) {
        try (Session session = sessionFactory.openSession()) {
            Query<Peliculas> query = session.createQuery("from Peliculas where genero = :genero", Peliculas.class);
            query.setParameter("genero", genero);
            return query.list();
        }
    }

    /**
     * Obtiene una lista de todas las películas y sus copias, incluyendo el nombre de la película,
     * el estado de la copia, la cantidad de copias disponibles y el tipo de soporte (e.g., DVD, Blu-Ray).
     *
     * @return una lista de arreglos de objetos donde cada arreglo contiene el nombre de la película,
     *         el estado, la cantidad y el soporte de cada copia asociada.
     */
    public List<Object[]> obtenerPeliculasYCopias() {
        List<Object[]> listaPeliculasCopias;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
             SELECT p.nombrePeli, c.estado, c.cantidad, c.soporte
            FROM Peliculas p JOIN p.copias c
        """;
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            listaPeliculasCopias = query.getResultList();
        }
        return listaPeliculasCopias;
    }

    /**
     * Obtiene una película por su nombre.
     *
     * @param nombrePeli el nombre de la película a buscar.
     * @return la película con el nombre especificado, o null si no se encuentra.
     */
    public Peliculas obtenerPeliculaPorNombre(String nombrePeli) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Peliculas pelicula = null;
        try {
            Query<Peliculas> query = session.createQuery("FROM Peliculas WHERE nombrePeli = :nombrePeli", Peliculas.class);
            query.setParameter("nombrePeli", nombrePeli);
            pelicula = query.uniqueResult();
            if (pelicula == null) {
                System.out.println("No se encontró la película: " + nombrePeli);
            } else {
                System.out.println("Pelicula encontrada: " + pelicula.getNombrePeli());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pelicula;
    }
}
