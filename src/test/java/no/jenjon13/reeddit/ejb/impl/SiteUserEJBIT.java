package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.data.entities.ValidTestUserFactory;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJBIT;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SiteUserEJBIT extends EntityEJBIT {
    private SiteUserEJB siteUserEJB;

    @Before
    public void setUp() {
        siteUserEJB = getEJB(SiteUserEJB.class);
    }

    @After
    public void tearDown() {
        siteUserEJB.deleteAll();
    }

    private SiteUser createAndPersistDefaultUser() {
        final SiteUser siteUser = ValidTestUserFactory.create();
        return siteUserEJB.create(siteUser);
    }

    @Test
    public void testGetAllShouldReturnEmpty() throws Exception {
        final List<SiteUser> siteUsers = siteUserEJB.getAll();
        Assert.assertEquals(0, siteUsers.size());
    }

    @Test
    public void testCreateUser() throws Exception {
        SiteUser createdSiteUser = createAndPersistDefaultUser();
        final List<SiteUser> all = siteUserEJB.getAll();
        Assert.assertEquals(1, all.size());
        Assert.assertEquals(createdSiteUser.getId(), all.get(0).getId());
    }

    @Test
    public void testUpdateUser() throws Exception {
        final SiteUser createdSiteUser = createAndPersistDefaultUser();
        final String newUserName = "newUserName";
        createdSiteUser.setUsername(newUserName);
        siteUserEJB.update(createdSiteUser);

        final SiteUser updatedSiteUser = siteUserEJB.getById(createdSiteUser.getId());
        Assert.assertEquals(newUserName, updatedSiteUser.getUsername());
    }

    @Test
    public void testDeleteUser() throws Exception {
        final SiteUser managedSiteUser = createAndPersistDefaultUser();
        final SiteUser foundCreatedSiteUser = siteUserEJB.getById(managedSiteUser.getId());
        Assert.assertEquals(managedSiteUser.getId(), foundCreatedSiteUser.getId());
        Assert.assertEquals(1, siteUserEJB.getAll().size());

        siteUserEJB.delete(foundCreatedSiteUser);
        Assert.assertEquals(0, siteUserEJB.getAll().size());
    }

    @Test(expected = Exception.class)
    public void testCannotHaveDuplicateUserNames() throws Exception {
        final String identicalUserName = "IDENTICAL_USER_NAME";

        final SiteUser siteUser1 = ValidTestUserFactory.create();
        siteUser1.setUsername(identicalUserName);
        siteUserEJB.create(siteUser1);

        final SiteUser siteUser2 = ValidTestUserFactory.create();
        siteUser2.setUsername(identicalUserName);
        siteUserEJB.create(siteUser2);
    }
}
