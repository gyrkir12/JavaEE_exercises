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
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    //    private Score score; TODO
    @Id
    @GeneratedValue
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (getId() != comment.getId()) return false;
        if (getTitle() != null ? !getTitle().equals(comment.getTitle()) : comment.getTitle() != null) return false;
        if (!getContent().equals(comment.getContent())) return false;
        if (!getTimestamp().equals(comment.getTimestamp())) return false;
        return getUser().equals(comment.getUser());

    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + getContent().hashCode();
        result = 31 * result + getTimestamp().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getId();
        return result;
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
    public User getUser() {
        return user;
    }

    public void setUser(User author) {
        this.user = author;
    }
}
