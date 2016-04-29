package no.jenjon13.reeddit.ejb.abstracts;

import no.jenjon13.reeddit.ejb.interfaces.IEntityEJB;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class EntityEJB<T> implements IEntityEJB<T> {
    private Class<T> clazz;
    @PersistenceContext
    private EntityManager entityManager;

    public EntityEJB(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T getById(long id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public List<T> getAll() {
        final TypedQuery<T> query = entityManager.createQuery("SELECT o FROM " + clazz.getSimpleName() + " o", clazz);
        return query.getResultList();
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        final T entityToDelete = entityManager.contains(entity) ? entity : entityManager.merge(entity);
        entityManager.remove(entityToDelete);
    }

    @Override
    public int deleteAll() {
        return entityManager.createQuery("DELETE FROM " + clazz.getSimpleName()).executeUpdate();
    }
}
