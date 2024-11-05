package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.HibernateUtil;
import org.example.reto_conjunto.models.PeliculasCopia;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PeliculasCopiaDAO implements DAO<PeliculasCopia> {

    public List<Object[]> obtenerPeliculasYCopias() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> resultados = null;

        try {
            String hql = "SELECT p.nombrePeli, c.estado, c.cantidad, c.soporte " +
                    "FROM Peliculas p JOIN Copias c ON p.id = c.pelicula.id";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            resultados = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return resultados;
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
