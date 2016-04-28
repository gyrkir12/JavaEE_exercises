package data.comment;

import data.Comment;
import data.User;

import java.util.Date;

public class ValidTestCommentFactory {
    public static Comment create(User author) {
        Comment testComment = new Comment();
        testComment.setTitle("Comment title");
        testComment.setContent("Comment content");
        testComment.setTimestamp(new Date());
        testComment.setAuthor(author);

        return testComment;
    }
}
