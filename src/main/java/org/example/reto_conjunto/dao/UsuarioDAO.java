package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.models.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class UsuarioDAO implements DAO<Usuario> {
    private SessionFactory sessionFactory;
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

    public Usuario validateUser(String email, String password){
        try(Session session = sessionFactory.openSession()){
            Query<Usuario> q = session.createQuery("select u from Usuario u  where u.email = :email and u.contrasena = :contrasena");
            q.setParameter("email", email);
            q.setParameter("password", password);

            Usuario user = q.uniqueResult();
            Usuario user1 = session.get(Usuario.class, user.getId());
            user1.getCopias();

            return user;
        }
    }
}
