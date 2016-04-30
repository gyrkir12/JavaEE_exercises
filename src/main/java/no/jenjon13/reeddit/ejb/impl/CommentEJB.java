package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.Comment;
import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJB;
import no.jenjon13.reeddit.ejb.util.VoteCheck;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CommentEJB extends EntityEJB<Comment> {
    public CommentEJB() {
        super(Comment.class);
    }

    public void upvote(Comment comment, SiteUser siteUser) {
        final List<SiteUser> usersUpvoted = comment.getScore().getUsersUpvoted();
        if (VoteCheck.hasUserUpvoted(siteUser, usersUpvoted)) return;

        final List<SiteUser> usersDownvoted = comment.getScore().getUsersDownvoted();
        if (VoteCheck.hasUserDownvoted(siteUser, usersDownvoted)) {
            usersDownvoted.remove(siteUser);
        }

        usersUpvoted.add(siteUser);
    }

    public void downvote(Comment comment, SiteUser siteUser) {
        final List<SiteUser> usersDownvoted = comment.getScore().getUsersDownvoted();
        if (VoteCheck.hasUserDownvoted(siteUser, usersDownvoted)) return;

        final List<SiteUser> usersUpvoted = comment.getScore().getUsersUpvoted();
        if (VoteCheck.hasUserUpvoted(siteUser, usersUpvoted)) {
            usersUpvoted.remove(siteUser);
        }

        usersDownvoted.add(siteUser);
    }
}
