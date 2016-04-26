import entities.Customer;
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
    public static String PersistenceUnitName = "DefaultUnit";
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory(CustomerIT.PersistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testGetAll() throws Exception {
        final TypedQuery<Customer> query = entityManager.createQuery("SELECT c from Customer c", Customer.class);
        Assert.assertEquals(5, query.getResultList().size());
    }

    @Test
    public void testGetAllNorwegian() throws Exception {
        final TypedQuery<Customer> namedQuery = entityManager.createNamedQuery(Customer.allNorwegian, Customer.class);
        Assert.assertEquals(3, namedQuery.getResultList().size());
    }

    @Test
    public void testGetAllFromOslo() throws Exception {
        final TypedQuery<Customer> namedQuery = entityManager.createNamedQuery(Customer.allFromOslo, Customer.class);
        Assert.assertEquals(2, namedQuery.getResultList().size());
    }
}
