/**
 * Clase que maneja las operaciones de acceso a datos relacionadas con las copias de películas
 * y su asociación con los usuarios. Implementa la interfaz `DAO` para realizar operaciones
 * sobre la entidad `PeliculasCopia` en la base de datos.
 */
package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.HibernateUtil;
import org.example.reto_conjunto.models.PeliculasCopia;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PeliculasCopiaDAO implements DAO<PeliculasCopia> {

    /**
     * Obtiene una lista de las películas y sus copias asociadas a un usuario específico.
     * Este método realiza una consulta que une las tablas `Copias` y `Peliculas` para obtener
     * información relevante como el nombre de la película, el estado, la cantidad y el soporte
     * de cada copia asociada al usuario.
     *
     * @param userId el ID del usuario cuya información sobre copias de películas se desea obtener.
     * @return una lista de objetos `PeliculasCopia` que contienen los detalles de las películas
     *         y sus copias asociadas al usuario.
     */
    public static List<PeliculasCopia> obtenerPeliculasYCopias(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PeliculasCopia> resultados = new ArrayList<>();

        try {
            String hql = "SELECT p.nombrePeli, c.estado, c.cantidad, c.soporte " +
                    "FROM Copias c " +
                    "JOIN c.pelicula p " +
                    "WHERE c.usuario.id = :userId";

            Query<Object[]> query = session.createQuery(hql, Object[].class);
            query.setParameter("userId", 1); // Este valor está hardcodeado, se debe usar `userId` en lugar de `1`

            List<Object[]> rows = query.getResultList();

            for (Object[] row : rows) {
                String nombrePeli = (String) row[0];
                String estado = (String) row[1];
                Integer cantidad = (Integer) row[2];
                String soporte = (String) row[3];

                PeliculasCopia peliculaCopia = new PeliculasCopia(nombrePeli, estado, cantidad, soporte);
                resultados.add(peliculaCopia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return resultados;
    }

    /**
     * Obtiene el nombre de una película dado su ID.
     *
     * @param idPelicula el ID de la película cuya información se desea obtener.
     * @return el nombre de la película, o null si no se encuentra.
     */
    private static String getNombrePeliculaById(Integer idPelicula) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String nombrePeli = null;

        try {
            String hql = "SELECT p.nombrePeli FROM Peliculas p WHERE p.id = :idPelicula";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("idPelicula", idPelicula);
            nombrePeli = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return nombrePeli;
    }

    /**
     * Obtiene todas las instancias de `PeliculasCopia`.
     *
     * @return una lista vacía, ya que no se implementa la obtención de todas las copias.
     */
    @Override
    public List<PeliculasCopia> findAll() {
        return List.of(); // Devuelve una lista vacía
    }

    /**
     * Obtiene una `PeliculasCopia` específica por su ID.
     *
     * @param id el ID de la copia de película que se desea obtener.
     * @return null, ya que no se implementa la obtención por ID.
     */
    @Override
    public PeliculasCopia findById(Long id) {
        return null; // No implementado
    }

    /**
     * Guarda una nueva instancia de `PeliculasCopia` en la base de datos.
     *
     * @param peliculasCopia la instancia de `PeliculasCopia` a guardar.
     */
    @Override
    public void save(PeliculasCopia peliculasCopia) {
        // No implementado
    }

    /**
     * Actualiza una instancia existente de `PeliculasCopia` en la base de datos.
     *
     * @param peliculasCopia la instancia de `PeliculasCopia` a actualizar.
     */
    @Override
    public void update(PeliculasCopia peliculasCopia) {
        // No implementado
    }

    /**
     * Elimina una instancia de `PeliculasCopia` de la base de datos.
     *
     * @param peliculasCopia la instancia de `PeliculasCopia` a eliminar.
     */
    @Override
    public void delete(PeliculasCopia peliculasCopia) {
        // No implementado
    }
}
