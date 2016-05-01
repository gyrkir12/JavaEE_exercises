package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.Comment;
import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.data.entities.ValidTestCommentFactory;
import no.jenjon13.reeddit.data.entities.ValidTestUserFactory;
import no.jenjon13.reeddit.data.entities.embeddables.Score;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJBTest;
import no.jenjon13.reeddit.ejb.util.VoteCheck;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommentEJBTest extends EntityEJBTest {
    private CommentEJB commentEJB;
    private SiteUserEJB siteUserEJB;

    @Before
    public void setUp() throws Exception {
        commentEJB = getEJB(CommentEJB.class);
        siteUserEJB = getEJB(SiteUserEJB.class);
        clean();
    }

    @After
    public void clean() {
        commentEJB.deleteAll();
        siteUserEJB.deleteAll();
    }

    private Comment createComment() {
        final SiteUser testSiteUser = ValidTestUserFactory.create();
        final Comment testComment = ValidTestCommentFactory.create(testSiteUser);
        return commentEJB.create(testComment);
    }

    @Test
    public void testCreatedCommentGetsPersisted() throws Exception {
        final Comment managedTestComment = createComment();
        final Comment fetchedComment = commentEJB.getAll().get(0);
        Assert.assertEquals(managedTestComment.getId(), fetchedComment.getId());
    }

    @Test
    public void testUserCanUpvote() throws Exception {
        final Comment comment = createComment();
        final SiteUser siteUser = siteUserEJB.getAll().get(0);
        Assert.assertNotNull(siteUser);

        final Score commentScore = comment.getScore();
        Assert.assertFalse(VoteCheck.hasUserUpvoted(siteUser, commentScore.getUsersUpvoted()));
        Assert.assertEquals(0, comment.getScore().getPoints());

        commentEJB.upvote(comment, siteUser);
        Assert.assertTrue(VoteCheck.hasUserUpvoted(siteUser, commentScore.getUsersUpvoted()));
        Assert.assertEquals(1, comment.getScore().getPoints());
    }

    @Test
    public void testUserCanDownvote() throws Exception {
        final Comment comment = createComment();
        final SiteUser siteUser = siteUserEJB.getAll().get(0);
        Assert.assertNotNull(siteUser);

        final Score commentScore = comment.getScore();
        Assert.assertFalse(VoteCheck.hasUserDownvoted(siteUser, commentScore.getUsersDownvoted()));
        Assert.assertEquals(0, comment.getScore().getPoints());

        commentEJB.downvote(comment, siteUser);
        Assert.assertTrue(VoteCheck.hasUserDownvoted(siteUser, commentScore.getUsersDownvoted()));
        Assert.assertEquals(-1, comment.getScore().getPoints());
    }
}
