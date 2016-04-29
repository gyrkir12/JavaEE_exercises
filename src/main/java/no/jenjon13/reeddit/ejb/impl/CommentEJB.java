package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.Comment;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJB;

import javax.ejb.Stateless;

@Stateless
public class CommentEJB extends EntityEJB<Comment> {
    public CommentEJB() {
        super(Comment.class);
    }
}
