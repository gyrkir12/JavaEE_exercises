package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.Comment;
import no.jenjon13.reeddit.ejb.interfaces.IEntityEJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.jenjon13.reeddit.config.MainConfig.PERSISTENCE_UNIT_NAME;

@Stateless
public class CommentEJB implements IEntityEJB<Comment> {
    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    @Override
    public Comment create(Comment entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Comment getById(long id) throws NoResultException {
        final TypedQuery<Comment> query = entityManager
                .createNamedQuery(Comment.QUERY_GET_BY_ID, Comment.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<Comment> getAll() {
        final TypedQuery<Comment> query = entityManager.createNamedQuery(Comment.QUERY_GET_ALL, Comment.class);
        return query.getResultList();
    }

    @Override
    public Comment update(Comment entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Comment entity) {
        final Comment comment = entityManager.contains(entity) ? entity : entityManager.merge(entity);
        entityManager.remove(comment);
    }

    @Override
    public int deleteAll() {
        return entityManager.createNamedQuery(Comment.QUERY_DELETE_ALL).executeUpdate();
    }
}
