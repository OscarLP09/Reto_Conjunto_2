package org.example.reto_conjunto.dao;

import org.example.reto_conjunto.models.Peliculas;

import java.util.List;

public class PeliculasDAO implements DAO<Peliculas> {
    @Override
    public List<Peliculas> findAll() {
        return List.of();
    }

    @Override
    public Peliculas findById(Long id) {
        return null;
    }

    @Override
    public void save(Peliculas peliculas) {

    }

    @Override
    public void update(Peliculas peliculas) {

    }

    @Override
    public void delete(Peliculas peliculas) {

    }
}
