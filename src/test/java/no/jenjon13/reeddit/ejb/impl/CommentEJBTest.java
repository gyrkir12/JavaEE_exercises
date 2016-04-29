package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.data.entities.ValidTestCommentFactory;
import no.jenjon13.reeddit.data.entities.Comment;
import no.jenjon13.reeddit.data.entities.ValidTestUserFactory;
import org.junit.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CommentEJBTest {
    private static CommentEJB commentEJB;
    private static SiteUserEJB userEJB;
    private static EJBContainer ec;
    private static Context ctx;

    @BeforeClass
    public static void initContainer() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        ec = EJBContainer.createEJBContainer(properties);
        ctx = ec.getContext();
        commentEJB = (CommentEJB) ctx.lookup("java:global/classes/" + CommentEJB.class.getSimpleName() + "!" + CommentEJB.class.getName());
        userEJB = (SiteUserEJB) ctx.lookup("java:global/classes/" + SiteUserEJB.class.getSimpleName() + "!" + SiteUserEJB.class.getName());
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ctx != null) ctx.close();
        if (ec != null) ec.close();
    }

    @Before
    @After
    public void setUp() throws Exception {
        commentEJB.deleteAll();
        userEJB.deleteAll();
    }

    @Test
    public void testCreateComment() throws Exception {
        final SiteUser testSiteUser = ValidTestUserFactory.create();
        final Comment testComment = ValidTestCommentFactory.create(testSiteUser);
        final Comment managedTestComment = commentEJB.create(testComment);

        final Comment fetchedComment = commentEJB.getAll().get(0);
        Assert.assertEquals(managedTestComment.getId(), fetchedComment.getId());
    }
}
