package com.sergeev.itroi.repository;

import com.sergeev.itroi.entity.Identifiable;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends Identifiable, I> {

    void save(T entity);

    void saveAll(List<T> entities);

    void remove(I id);

    Optional<T> getOne(I id);

    List<T> getAll();
}
