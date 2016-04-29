package no.jenjon13.reeddit.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@NamedQueries(value = {
        @NamedQuery(name = Comment.QUERY_GET_ALL, query = "SELECT c FROM Comment c"),
        @NamedQuery(name = Comment.QUERY_GET_BY_ID, query = "SELECT c FROM Comment c WHERE c.id = :id"),
        @NamedQuery(name = Comment.QUERY_DELETE_ALL, query = "DELETE FROM Comment")
})
public class Comment {
    public static final String QUERY_GET_ALL = "Comment.all";
    public static final String QUERY_GET_BY_ID = "Comment.byId";
    public static final String QUERY_DELETE_ALL = "Comment.deleteAll";
    @Size(max = 64)
    private String title;
    @NotNull
    @Size(max = 500)
    private String content;
    @NotNull
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private SiteUser siteUser;
    //    private Score score; TODO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Override
    public String toString() {
        return String.format("Comment{title='%s', content='%s', timestamp=%s, siteUser=%s, id=%d}",
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

    //    @JoinColumn(name = "AUTHOR_ID")
    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser author) {
        this.siteUser = author;
    }
}
