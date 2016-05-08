package no.jenjon13.reeddit.ejb.interfaces;

import java.util.List;

/**
 * Interface for abstract EJB template class.
 *
 * @param <T> Annotated @Entity POJO type that the implemented methods should operate on.
 */
public interface IEntityEJB<T> {
    T create(T entity);

    T getById(long id);

    List<T> getAll();

    T update(T entity);

    void delete(T entity);

    int deleteAll();
}
