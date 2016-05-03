package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.News;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJB;

import javax.ejb.Stateless;

@Stateless
public class NewsEJB extends EntityEJB<News> {
    public NewsEJB() {
        super(News.class);
    }
}
