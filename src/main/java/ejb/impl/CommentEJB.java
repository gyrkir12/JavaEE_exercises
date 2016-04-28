package ejb.impl;

import config.MainConfig;
import data.Comment;
import ejb.interfaces.EntityEJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CommentEJB implements EntityEJB<Comment> {
    @PersistenceContext(unitName = MainConfig.PersistenceUnitName)
    private EntityManager entityManager;

    public CommentEJB() {
    }

    public CommentEJB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment create(Comment entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Comment getById(long id) throws NoResultException {
        final TypedQuery<Comment> query = entityManager
                .createNamedQuery(Comment.query_getById, Comment.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<Comment> getAll() {
        final TypedQuery<Comment> query = entityManager.createNamedQuery(Comment.query_getAll, Comment.class);
        return query.getResultList();
    }

    @Override
    public Comment update(Comment entity) {
        entityManager.getTransaction().begin();
        final Comment updatedComment = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return updatedComment;
    }

    @Override
    public void delete(Comment entity) {
        entityManager.getTransaction().begin();
        final Comment comment = entityManager.contains(entity) ? entity : entityManager.merge(entity);
        entityManager.remove(comment);
        entityManager.getTransaction().commit();
    }
}
