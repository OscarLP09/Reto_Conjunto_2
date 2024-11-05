package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.models.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class UsuarioDAO implements DAO<Usuario> {
    private SessionFactory sessionFactory;

    public UsuarioDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Usuario> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Usuario> query = session.createQuery("from Usuario", Usuario.class);
            return query.list();
        }
    }

    public Usuario findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    public void save(Usuario usuario) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(usuario);
            session.getTransaction().commit();
        }
    }

    public void update(Usuario usuario) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(usuario);
            session.getTransaction().commit();
        }
    }

    public void delete(Usuario usuario) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(usuario);
            session.getTransaction().commit();
        }
    }

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
