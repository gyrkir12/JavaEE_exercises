package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJB;

import javax.ejb.Stateless;

@Stateless
//@LocalBean
public class SiteUserEJB extends EntityEJB<SiteUser> {
    public SiteUserEJB() {
        super(SiteUser.class);
    }
}
