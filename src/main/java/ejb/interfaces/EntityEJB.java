package ejb.interfaces;

import java.util.List;

public interface EntityEJB<T> {
    T create(T entity);
    T getById(long id);
    List<T> getAll();
    void delete(T entity);
    T update(T entity);
}
