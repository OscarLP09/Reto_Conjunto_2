package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.HibernateUtil;
import org.example.reto_conjunto.models.Peliculas;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PeliculasDAO implements DAO<Peliculas>  {
    private SessionFactory sessionFactory;

    public PeliculasDAO() {

    }

    public PeliculasDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Peliculas> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Peliculas> query = session.createQuery("from Peliculas", Peliculas.class);
            return query.list();
        }
    }

    public Peliculas findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Peliculas.class, id);
        }
    }

    public void save(Peliculas pelicula) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(pelicula);
            session.getTransaction().commit();
        }
    }

    public void update(Peliculas pelicula) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(pelicula);
            session.getTransaction().commit();
        }
    }

    public void delete(Peliculas pelicula) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(pelicula);
            session.getTransaction().commit();
        }
    }

    public List<Peliculas> findByGenero(String genero) {
        try (Session session = sessionFactory.openSession()) {
            Query<Peliculas> query = session.createQuery("from Peliculas     where genero = :genero", Peliculas.class);
            query.setParameter("genero", genero);
            return query.list();
        }
    }


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
