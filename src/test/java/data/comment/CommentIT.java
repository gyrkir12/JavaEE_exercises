package data.comment;

import config.MainConfig;
import data.Comment;
import data.User;
import data.user.ValidTestUserFactory;
import ejb.impl.CommentEJB;
import ejb.impl.UserEJB;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CommentIT {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private CommentEJB commentEJB;
    private UserEJB userEJB;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory(MainConfig.PersistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
        commentEJB = new CommentEJB(entityManager);
        userEJB = new UserEJB(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        if (entityManager.isOpen()) entityManager.close();
        if (entityManagerFactory.isOpen()) entityManagerFactory.close();
    }

    @Test
    public void testCreateComment() throws Exception {
        final User testAuthor = ValidTestUserFactory.create();
        final User persistedAuthor = userEJB.create(testAuthor);

        final Comment testComment = ValidTestCommentFactory.create(persistedAuthor);
        final Comment persistedTestComment = commentEJB.create(testComment);

        final List<Comment> allComments = commentEJB.getAll();
        Assert.assertEquals(persistedTestComment.getId(), allComments.get(0).getId());
    }
}
