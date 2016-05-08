package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.Post;
import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJB;
import no.jenjon13.reeddit.ejb.util.VoteCheck;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PostEJB extends EntityEJB<Post> {
    public PostEJB() {
        super(Post.class);
    }

    public void upvote(Post post, SiteUser siteUser) {
        final List<SiteUser> usersUpvoted = post.getScore().getUsersUpvoted();
        if (VoteCheck.hasUserUpvoted(siteUser, usersUpvoted)) return;

        final List<SiteUser> usersDownvoted = post.getScore().getUsersDownvoted();
        if (VoteCheck.hasUserDownvoted(siteUser, usersDownvoted)) {
            usersDownvoted.remove(siteUser);
        }

        usersUpvoted.add(siteUser);
    }

    public void downvote(Post post, SiteUser siteUser) {
        final List<SiteUser> usersDownvoted = post.getScore().getUsersDownvoted();
        if (VoteCheck.hasUserDownvoted(siteUser, usersDownvoted)) return;

        final List<SiteUser> usersUpvoted = post.getScore().getUsersUpvoted();
        if (VoteCheck.hasUserUpvoted(siteUser, usersUpvoted)) {
            usersUpvoted.remove(siteUser);
        }

        usersDownvoted.add(siteUser);
    }
}
