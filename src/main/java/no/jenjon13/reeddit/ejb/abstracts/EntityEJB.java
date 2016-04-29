package no.jenjon13.reeddit.ejb.abstracts;

import no.jenjon13.reeddit.ejb.interfaces.IEntityEJB;

public abstract class EntityEJB<T> implements IEntityEJB<T> {
    /*
    @PersistenceContext
    private EntityManager entityManager;

    public T create(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

//    @Override
//    public T getById(long id) {
//        entityManager.find()
//        return null;
//    }

//    @Override
//    public List<T> getAll() {
//        return null;
//    }

    @Override
    public T update(T entity) {
        entityManager.getTransaction().begin();
        final T updatedEntity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return updatedEntity;
    }

    @Override
    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
    */
}
