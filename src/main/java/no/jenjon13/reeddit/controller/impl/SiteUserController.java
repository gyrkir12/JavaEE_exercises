package no.jenjon13.reeddit.controller.impl;

import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.ejb.impl.SiteUserEJB;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class SiteUserController {
    private SiteUser siteUser;

    @Inject
    private SiteUserEJB siteUserEJB;

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    public void create() {
        final SiteUser persistedUser = siteUserEJB.create(siteUserEJB.create(siteUser));
        System.out.println("Created user with ID " + persistedUser.getId());
    }
}
