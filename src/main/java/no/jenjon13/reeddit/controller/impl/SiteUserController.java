package no.jenjon13.reeddit.controller.impl;

import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.ejb.impl.SiteUserEJB;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class SiteUserController {
    private SiteUser siteUser = new SiteUser();

    @Inject
    private SiteUserEJB siteUserEJB;

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    public void persist() {
        siteUserEJB.create(siteUser);
    }

    public List<SiteUser> getAll() {
        return siteUserEJB.getAll();
    }

    public void delete(int id) {
        final SiteUser siteUser = siteUserEJB.getById(id);
        siteUserEJB.delete(siteUser);
    }
}
