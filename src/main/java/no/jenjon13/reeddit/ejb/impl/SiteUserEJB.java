package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.ejb.interfaces.IEntityEJB;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class SiteUserEJB implements IEntityEJB<SiteUser> {
    @PersistenceContext
    private EntityManager entityManager;

    public SiteUser create(SiteUser siteUser) {
        entityManager.persist(siteUser);
        return siteUser;
    }

    @Override
    public SiteUser getById(long id) throws NoResultException {
        final TypedQuery<SiteUser> query = entityManager
                .createNamedQuery(SiteUser.QUERY_GET_BY_ID, SiteUser.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<SiteUser> getAll() {
        final TypedQuery<SiteUser> query = entityManager.createNamedQuery(SiteUser.QUERY_GET_ALL, SiteUser.class);
        return query.getResultList();
    }

    @Override
    public SiteUser update(SiteUser entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(SiteUser entity) {
        final SiteUser siteUser = entityManager.contains(entity) ? entity : entityManager.merge(entity);
        entityManager.remove(siteUser);
    }

    public int deleteAll() {
        return entityManager.createNamedQuery(SiteUser.QUERY_DELETE_ALL).executeUpdate();
    }
}
