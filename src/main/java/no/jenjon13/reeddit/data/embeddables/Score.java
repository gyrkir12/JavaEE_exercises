package no.jenjon13.reeddit.data.embeddables;

import no.jenjon13.reeddit.data.entities.SiteUser;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
public class Score {
    @OneToMany
    private List<SiteUser> usersUpvoted;

    @OneToMany
    private List<SiteUser> usersDownvoted;

    public List<SiteUser> getUsersUpvoted() {
        return usersUpvoted;
    }

    public void setUsersUpvoted(List<SiteUser> usersUpvoted) {
        this.usersUpvoted = usersUpvoted;
    }

    public List<SiteUser> getUsersDownvoted() {
        return usersDownvoted;
    }

    public void setUsersDownvoted(List<SiteUser> usersDownvoted) {
        this.usersDownvoted = usersDownvoted;
    }

    public int getPoints() {
        return getUsersUpvoted().size() - getUsersDownvoted().size();
    }
}
