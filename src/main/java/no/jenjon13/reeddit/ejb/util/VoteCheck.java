package no.jenjon13.reeddit.ejb.util;

import no.jenjon13.reeddit.data.entities.SiteUser;

import java.util.List;

public class VoteCheck {
    public static boolean hasUserUpvoted(SiteUser siteUser, List upvoters) {
        return upvoters.contains(siteUser);
    }

    public static boolean hasUserDownvoted(SiteUser siteUser, List downvoters) {
        return downvoters.contains(siteUser);
    }
}
