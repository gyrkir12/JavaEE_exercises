import config.MainConfig;
import data.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

// TODO: test validation
public class CustomerIT {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory(MainConfig.PersistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testGetAll() throws Exception {
        final TypedQuery<User> query = entityManager.createQuery("SELECT c from User c", User.class);
        Assert.assertEquals(5, query.getResultList().size());
    }

    @Test
    public void testGetAllNorwegian() throws Exception {
        final TypedQuery<User> namedQuery = entityManager.createNamedQuery(User.allNorwegian, User.class);
        Assert.assertEquals(3, namedQuery.getResultList().size());
    }

    @Test
    public void testGetAllFromOslo() throws Exception {
        final TypedQuery<User> namedQuery = entityManager.createNamedQuery(User.allFromOslo, User.class);
        Assert.assertEquals(2, namedQuery.getResultList().size());
    }
}
