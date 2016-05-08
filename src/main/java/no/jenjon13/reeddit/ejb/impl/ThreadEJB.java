package no.jenjon13.reeddit.ejb.impl;

import no.jenjon13.reeddit.data.entities.Thread;
import no.jenjon13.reeddit.ejb.abstracts.EntityEJB;

import javax.ejb.Stateless;

@Stateless
public class ThreadEJB extends EntityEJB<Thread> {
    public ThreadEJB() {
        super(Thread.class);
    }
}
