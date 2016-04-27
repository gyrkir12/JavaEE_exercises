package data.user;

import config.MainConfig;
import data.User;
import ejb.impl.UserEJB;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class UserIT {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserEJB userEJB;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory(MainConfig.PersistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
        userEJB = new UserEJB(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        if (entityManager.isOpen()) entityManager.close();
        if (entityManagerFactory.isOpen()) entityManagerFactory.close();
    }

    @Test
    public void testGetAllShouldReturnEmpty() throws Exception {
        final List<User> users = userEJB.getAll();
        Assert.assertEquals(0, users.size());
    }

    private User createAndPersistDefaultUser() {
        final User user = ValidTestUserFactory.create();
        return userEJB.create(user);
    }

    @Test
    public void testCreateUser() throws Exception {
        User createdUser = createAndPersistDefaultUser();
        final List<User> all = userEJB.getAll();
        Assert.assertEquals(1, all.size());
        Assert.assertEquals(createdUser.getId(), all.get(0).getId());
    }

    @Test
    public void testUpdateUser() throws Exception {
        final User createdUser = createAndPersistDefaultUser();
        final String newUserName = "newUserName";
        createdUser.setUsername(newUserName);
        userEJB.update(createdUser);

        final User updatedUser = userEJB.getById(createdUser.getId());
        Assert.assertEquals(newUserName, updatedUser.getUsername());
    }

    @Test(expected = NoResultException.class)
    public void testDeleteUser() throws Exception {
        final User createdUser = createAndPersistDefaultUser();
        final User foundCreatedUser = userEJB.getById(createdUser.getId());
        Assert.assertEquals(createdUser.getId(), foundCreatedUser.getId());

        userEJB.delete(createdUser);
        userEJB.getById(createdUser.getId());
    }
}
