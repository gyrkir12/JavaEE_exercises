package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.News;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJBIT;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class NewsEJBIT extends EntityEJBIT {
    private NewsEJB newsEJB;

    @Before
    public void setUp() throws Exception {
        newsEJB = getEJB(NewsEJB.class);
    }

    @After
    public void tearDown() throws Exception {
        newsEJB.deleteAll();
    }

    @Test
    public void testCreateNews() throws Exception {
        final News news = new News();
        news.setTitle("Test title");
        news.setContent("Test content");
        news.setTimestamp(new Date());

        final News managedNews = newsEJB.create(news); // TODO rename 'news' to 'thread'?
        final News fetchedNews = newsEJB.getAll().get(0);

        Assert.assertEquals(managedNews.getId(), fetchedNews.getId());
        Assert.assertEquals(news.getTitle(), fetchedNews.getTitle());
        Assert.assertEquals(news.getContent(), fetchedNews.getContent());
    }
}
