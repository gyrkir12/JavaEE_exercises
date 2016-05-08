package no.jenjon13.reeddit.ejb.abstracts;

import no.jenjon13.reeddit.ejb.interfaces.IEntityEJB;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

/**
 * Abstract class implementing the methods specified by the interface.
 * Provides basic CRUD functionality for the given type T, using the injected EntityManager.
 *
 * @param <T> The type which the EJB is to be used for.
 */
public abstract class EntityEJB<T> implements IEntityEJB<T> {
    private Class<T> clazz;
    @PersistenceContext
    protected EntityManager entityManager;

    public EntityEJB(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T create(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<T> cv : constraintViolations) {
                System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }
        } else {
            entityManager.persist(entity);
        }

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
