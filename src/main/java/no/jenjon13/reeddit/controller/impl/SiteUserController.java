package no.jenjon13.reeddit.controller.impl;

import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.ejb.impl.SiteUserEJB;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

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

    public String persist() {
        if (siteUserEJB.getByName(siteUser.getUsername()) != null) {
            final FacesMessage message = new FacesMessage(SEVERITY_ERROR, "Error", "Username is already taken");
            FacesContext.getCurrentInstance().addMessage("userform:username", message);
            return null;
        }

        siteUserEJB.create(siteUser);
        return "/user/index.xhtml?faces-redirect=true";
    }

    public List<SiteUser> getAll() {
        return siteUserEJB.getAll();
    }

    public void delete(int id) {
        final SiteUser siteUser = siteUserEJB.getById(id);
        siteUserEJB.delete(siteUser);
    }
}
