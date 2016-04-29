package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.data.entities.ValidTestUserFactory;
import org.junit.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiteSiteUserEJBTest {
    private static SiteUserEJB userEJB;
    private static EJBContainer ec;
    private static Context ctx;

    @BeforeClass
    public static void initContainer() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        ec = EJBContainer.createEJBContainer(properties);
        ctx = ec.getContext();
        userEJB = (SiteUserEJB) ctx.lookup("java:global/classes/" + SiteUserEJB.class.getSimpleName() + "!" + SiteUserEJB.class.getName());
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ctx != null)
            ctx.close();
        if (ec != null)
            ec.close();
    }

    @Before
    @After
    public void emptyDatabase() {
        userEJB.deleteAll();
    }

    @Test
    public void testGetAllShouldReturnEmpty() throws Exception {
        final List<SiteUser> siteUsers = userEJB.getAll();
        Assert.assertEquals(0, siteUsers.size());
    }

    private SiteUser createAndPersistDefaultUser() {
        final SiteUser siteUser = ValidTestUserFactory.create();
        System.out.println(siteUser);
        return userEJB.create(siteUser);
    }

    @Test
    public void testCreateUser() throws Exception {
        SiteUser createdSiteUser = createAndPersistDefaultUser();
        final List<SiteUser> all = userEJB.getAll();
        Assert.assertEquals(1, all.size());
        Assert.assertEquals(createdSiteUser.getId(), all.get(0).getId());
    }

    @Test
    public void testUpdateUser() throws Exception {
        final SiteUser createdSiteUser = createAndPersistDefaultUser();
        final String newUserName = "newUserName";
        createdSiteUser.setUsername(newUserName);
        userEJB.update(createdSiteUser);

        final SiteUser updatedSiteUser = userEJB.getById(createdSiteUser.getId());
        Assert.assertEquals(newUserName, updatedSiteUser.getUsername());
    }

    @Test
    public void testDeleteUser() throws Exception {
        final SiteUser createdSiteUser = createAndPersistDefaultUser();
        final SiteUser foundCreatedSiteUser = userEJB.getById(createdSiteUser.getId());
        Assert.assertEquals(createdSiteUser.getId(), foundCreatedSiteUser.getId());

        userEJB.delete(foundCreatedSiteUser);
        Assert.assertEquals(0, userEJB.getAll().size());
    }
}
