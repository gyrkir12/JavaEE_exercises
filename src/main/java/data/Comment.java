package data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@NamedQueries(value = {
        @NamedQuery(name = Comment.query_getAll, query = "SELECT c from Comment c"),
        @NamedQuery(name = Comment.query_getById, query = "SELECT c from Comment c WHERE c.id = :id")
})
public class Comment {
    public static final String query_getAll = "Comment.all";
    public static final String query_getById = "Comment.byId";

    @Size(max = 64)
    private String title;

    @NotNull
    @Size(max = 500)
    private String content;

    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private Date timestamp;

    @NotNull
    @ManyToOne
    private User author;

    //    private Score score; TODO
    @Id
    @GeneratedValue
    private int id;

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
