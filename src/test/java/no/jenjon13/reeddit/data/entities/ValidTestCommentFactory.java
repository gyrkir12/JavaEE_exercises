package no.jenjon13.reeddit.data.entities;

import no.jenjon13.reeddit.data.entities.embeddables.Score;

import java.util.Date;

public class ValidTestCommentFactory {
    public static Comment create(SiteUser author) {
        Comment testComment = new Comment();
        testComment.setTitle("Comment title");
        testComment.setContent("Comment content");
        testComment.setTimestamp(new Date());
        testComment.setSiteUser(author);
        testComment.setScore(new Score());

        return testComment;
    }
}
