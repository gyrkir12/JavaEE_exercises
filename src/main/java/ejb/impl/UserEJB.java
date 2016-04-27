package ejb.impl;

import data.User;
import ejb.interfaces.EntityEJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserEJB implements EntityEJB<User> {
    @PersistenceContext(unitName = config.MainConfig.PersistenceUnitName)
    private EntityManager entityManager;

    public UserEJB() {
    }

    public UserEJB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User getById(long id) {
        final TypedQuery<User> query = entityManager
                .createNamedQuery(User.query_getById, User.class)
                .setParameter(User.query_idField, id);
        return query.getSingleResult();
    }

    @Override
    public List<User> getAll() {
        final TypedQuery<User> query = entityManager.createNamedQuery(User.query_getAll, User.class);
        return query.getResultList();
    }

    @Override
    public void delete(User entity) {
        final User user = entityManager.contains(entity) ? entity : entityManager.merge(entity);
        entityManager.remove(user);
    }

    @Override
    public User update(User entity) {
        return entityManager.merge(entity);
    }
}
