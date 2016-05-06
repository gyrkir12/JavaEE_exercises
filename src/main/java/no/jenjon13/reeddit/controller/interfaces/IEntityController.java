package no.jenjon13.reeddit.controller.interfaces;

import java.util.List;

public interface IEntityController<T> {
    T create(T entity);

    T getById(long id);

    List<T> getAll();

    T update(T entity);

    void delete(T entity);

    int deleteAll();
}
