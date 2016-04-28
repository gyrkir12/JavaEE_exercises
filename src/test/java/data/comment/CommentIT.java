package data.comment;

import config.MainConfig;
import data.Comment;
import data.User;
import data.user.ValidTestUserFactory;
import ejb.impl.CommentEJB;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CommentIT {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager commentEntityManager;
    private CommentEJB commentEJB;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory(MainConfig.PersistenceUnitName);
        commentEntityManager = entityManagerFactory.createEntityManager();
        commentEJB = new CommentEJB(commentEntityManager);
    }

    @After
    public void tearDown() throws Exception {
        if (commentEntityManager.isOpen()) commentEntityManager.close();
        if (entityManagerFactory.isOpen()) entityManagerFactory.close();
    }

    @Test
    public void testCreateComment() throws Exception {
        final User user = ValidTestUserFactory.create();
        final Comment testComment = ValidTestCommentFactory.create(user);
        final Comment persistedTestComment = commentEJB.create(testComment);

        Assert.assertEquals(persistedTestComment, commentEJB.getAll().get(0));
    }
}
