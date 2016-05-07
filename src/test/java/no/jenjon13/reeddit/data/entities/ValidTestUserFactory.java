package no.jenjon13.reeddit.data.entities;

import java.util.Date;

public class ValidTestUserFactory {
    // TODO replace with JavaFaker/Fariry

    public static SiteUser create() {
        final SiteUser siteUser = new SiteUser();
        siteUser.setDateOfRegistration(new Date());
        siteUser.setUsername("jenjon13");
        siteUser.setPassword("passw0rd");

        return siteUser;
    }
}
