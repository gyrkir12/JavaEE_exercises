package no.jenjon13.reeddit.rest.util;

import no.jenjon13.reeddit.data.entities.SiteUser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement
public class SiteUserXMLWrapper extends ArrayList<SiteUser> {
    @XmlElement
    private Collection<? extends SiteUser> users;

    public SiteUserXMLWrapper() {
    }

    public SiteUserXMLWrapper(Collection<? extends SiteUser> users) {
        super(users);
        this.users = users;
    }
}
