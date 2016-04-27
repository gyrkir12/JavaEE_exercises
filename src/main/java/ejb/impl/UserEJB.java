package ejb.impl;

import data.User;
import ejb.interfaces.EntityEJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User getById(long id) throws NoResultException {
        final TypedQuery<User> query = entityManager
                .createNamedQuery(User.query_getById, User.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<User> getAll() {
        final TypedQuery<User> query = entityManager.createNamedQuery(User.query_getAll, User.class);
        return query.getResultList();
    }

    @Override
    public User update(User entity) {
        entityManager.getTransaction().begin();
        final User updatedUser = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return updatedUser;
    }

    @Override
    public void delete(User entity) {
        entityManager.getTransaction().begin();
        final User user = entityManager.contains(entity) ? entity : entityManager.merge(entity);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}
