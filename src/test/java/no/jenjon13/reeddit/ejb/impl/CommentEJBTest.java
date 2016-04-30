package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.Comment;
import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.data.entities.ValidTestCommentFactory;
import no.jenjon13.reeddit.data.entities.ValidTestUserFactory;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJBTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommentEJBTest extends EntityEJBTest {
    private CommentEJB commentEJB;

    @Before
    public void setUp() throws Exception {
        commentEJB =  getEJB(CommentEJB.class);
    }

    @After
    public void shutDown() {
        commentEJB.deleteAll();
    }

    @Test
    public void testCreateComment() throws Exception {
        final SiteUser testSiteUser = ValidTestUserFactory.create();
        final Comment testComment = ValidTestCommentFactory.create(testSiteUser);
        final Comment managedTestComment = commentEJB.create(testComment);

        final Comment fetchedComment = commentEJB.getAll().get(0);
        Assert.assertEquals(managedTestComment.getId(), fetchedComment.getId());
    }
}
