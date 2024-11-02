package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.models.Usuario;

import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {
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
}
