package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.HibernateUtil;
import org.example.reto_conjunto.models.Copias;
import org.example.reto_conjunto.models.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import org.hibernate.query.Query;

public class CopiasDAO {
    private static SessionFactory sessionFactory;

    public CopiasDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Método para obtener una copia específica según el usuario y la película
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


    public List<Copias> findAllByUsuario(Usuario usuario) {
        try (Session session = sessionFactory.openSession()) {
            Query<Copias> query = session.createQuery("from Copias where usuario = :usuario", Copias.class);
            query.setParameter("usuario", usuario);
            return query.list();
        }
    }

    public void save(Copias copia) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(copia);
            session.getTransaction().commit();
        }
    }

    public void delete(Copias copia) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(copia);
            session.getTransaction().commit();
        }
    }

    public void update(Copias copia) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(copia);
            session.getTransaction().commit();
        }
    }

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
