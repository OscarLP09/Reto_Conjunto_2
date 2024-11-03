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
    @Override
    public List<Usuario> findAll() {
        return List.of();
    }

    @Override
    public Usuario findById(Long id) {
        return null;
    }

    @Override
    public void save(Usuario usuario) {

    }

    @Override
    public void update(Usuario usuario) {

    }

    @Override
    public void delete(Usuario usuario) {

    }

    public Usuario validateUsuario(String email, String password) {
        try (Session session = sessionFactory.openSession()) {
            Query<Usuario> q = session.createQuery(
                    "select u from Usuario u where u.email = :email and u.contrasena = :contrasena", Usuario.class
            );
            q.setParameter("email", email);
            q.setParameter("contrasena", password);

            Usuario user = q.uniqueResult();
            if (user != null) {
                user.getCopias();
            }
            return user;
        }
    }

}
