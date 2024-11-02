package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.models.Copias;

import java.util.List;

public class CopiasDAO implements DAO<Copias> {
    @Override
    public List<Copias> findAll() {
        return List.of();
    }

    @Override
    public Copias findById(Long id) {
        return null;
    }

    @Override
    public void save(Copias copias) {

    }

    @Override
    public void update(Copias copias) {

    }

    @Override
    public void delete(Copias copias) {

    }
}
