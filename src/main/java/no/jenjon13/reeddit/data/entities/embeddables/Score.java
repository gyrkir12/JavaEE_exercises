package no.jenjon13.reeddit.data.entities.embeddables;

import no.jenjon13.reeddit.data.entities.SiteUser;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
public class Score {
    private int points;

    @OneToMany
    private List<SiteUser> usersUpvoted;

    @OneToMany
    private List<SiteUser> usersDownvoted;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

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
}
