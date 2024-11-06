package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.HibernateUtil;
import org.example.reto_conjunto.models.PeliculasCopia;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PeliculasCopiaDAO implements DAO<PeliculasCopia> {

    public static List<PeliculasCopia> obtenerPeliculasYCopias() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PeliculasCopia> resultados = new ArrayList<>();

        try {
            String hql = "SELECT p.nombrePeli, c.estado, c.cantidad, c.soporte " +
                    "FROM Peliculas p JOIN Copias c ON p.id = c.pelicula.id";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
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

        return resultados; // Ahora devuelve la lista de PeliculasCopia
    }

    @Override
    public List<PeliculasCopia> findAll() {
        return List.of();
    }

    @Override
    public PeliculasCopia findById(Long id) {
        return null;
    }

    @Override
    public void save(PeliculasCopia peliculasCopia) {

    }

    @Override
    public void update(PeliculasCopia peliculasCopia) {

    }

    @Override
    public void delete(PeliculasCopia peliculasCopia) {

    }
}
