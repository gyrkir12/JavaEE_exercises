package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.Thread;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJBIT;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ThreadEJBIT extends EntityEJBIT {
    private ThreadEJB threadEJB;

    @Before
    public void setUp() throws Exception {
        threadEJB = getEJB(ThreadEJB.class);
    }

    @After
    public void tearDown() throws Exception {
        threadEJB.deleteAll();
    }

    @Test
    public void testCreateNews() throws Exception {
        final Thread thread = new Thread();
        thread.setTitle("Test title");
        thread.setContent("Test content");
        thread.setTimestamp(new Date());

        final Thread managedThread = threadEJB.create(thread);
        final Thread fetchedThread = threadEJB.getAll().get(0);

        Assert.assertEquals(managedThread.getId(), fetchedThread.getId());
        Assert.assertEquals(thread.getTitle(), fetchedThread.getTitle());
        Assert.assertEquals(thread.getContent(), fetchedThread.getContent());
    }
}
