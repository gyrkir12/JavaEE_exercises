package no.jenjon13.reeddit.data.entities;

import no.jenjon13.reeddit.data.embeddables.Score;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 64)
    private String title;

    @NotNull
    @Size(max = 500)
    private String content;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private SiteUser siteUser;

    @Embedded
    private Score score = new Score();

    @Override
    public String toString() {
        return String.format("Post{title='%s', content='%s', timestamp=%s, siteUser=%s, id=%d}",
                title, content, timestamp, siteUser, id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser author) {
        this.siteUser = author;
    }

    public Score getScore() {
        return score;
    }
}
