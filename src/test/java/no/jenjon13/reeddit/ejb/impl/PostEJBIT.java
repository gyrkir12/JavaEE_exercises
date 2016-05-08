package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.Post;
import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.data.entities.ValidTestPostFactory;
import no.jenjon13.reeddit.data.entities.ValidTestUserFactory;
import no.jenjon13.reeddit.data.entities.embeddables.Score;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJBIT;
import no.jenjon13.reeddit.ejb.util.VoteCheck;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostEJBIT extends EntityEJBIT {
    private PostEJB postEJB;
    private SiteUserEJB siteUserEJB;

    @Before
    public void setUp() throws Exception {
        postEJB = getEJB(PostEJB.class);
        siteUserEJB = getEJB(SiteUserEJB.class);
        tearDown();
    }

    @After
    public void tearDown() {
        postEJB.deleteAll();
        siteUserEJB.deleteAll();
    }

    private Post createComment() {
        final SiteUser testSiteUser = ValidTestUserFactory.create();
        final Post testPost = ValidTestPostFactory.create(testSiteUser);
        return postEJB.create(testPost);
    }

    @Test
    public void testCreatedCommentGetsPersisted() throws Exception {
        final Post managedTestPost = createComment();
        final Post fetchedPost = postEJB.getAll().get(0);
        Assert.assertEquals(managedTestPost.getId(), fetchedPost.getId());
    }

    @Test
    public void testUserCanUpvote() throws Exception {
        final Post post = createComment();
        final SiteUser siteUser = siteUserEJB.getAll().get(0);
        Assert.assertNotNull(siteUser);

        final Score commentScore = post.getScore();
        Assert.assertFalse(VoteCheck.hasUserUpvoted(siteUser, commentScore.getUsersUpvoted()));
        Assert.assertEquals(0, post.getScore().getPoints());

        postEJB.upvote(post, siteUser);
        Assert.assertTrue(VoteCheck.hasUserUpvoted(siteUser, commentScore.getUsersUpvoted()));
        Assert.assertEquals(1, post.getScore().getPoints());
    }

    @Test
    public void testUserCanDownvote() throws Exception {
        final Post post = createComment();
        final SiteUser siteUser = siteUserEJB.getAll().get(0);
        Assert.assertNotNull(siteUser);

        final Score commentScore = post.getScore();
        Assert.assertFalse(VoteCheck.hasUserDownvoted(siteUser, commentScore.getUsersDownvoted()));
        Assert.assertEquals(0, post.getScore().getPoints());

        postEJB.downvote(post, siteUser);
        Assert.assertTrue(VoteCheck.hasUserDownvoted(siteUser, commentScore.getUsersDownvoted()));
        Assert.assertEquals(-1, post.getScore().getPoints());
    }
}
