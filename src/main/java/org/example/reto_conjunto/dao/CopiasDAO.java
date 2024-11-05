package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.HibernateUtil;
import org.example.reto_conjunto.models.Copias;
import org.example.reto_conjunto.models.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CopiasDAO {
    private SessionFactory sessionFactory;

    public CopiasDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
