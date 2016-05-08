package no.jenjon13.reeddit.data.entities;

import java.util.Date;

public class ValidTestPostFactory {
    public static Post create(SiteUser author) {
        Post testPost = new Post();
        testPost.setTitle("Post title");
        testPost.setContent("Post content");
        testPost.setTimestamp(new Date());
        testPost.setSiteUser(author);

        return testPost;
    }
}
