package no.jenjon13.reeddit.rest.config;

import no.jenjon13.reeddit.rest.controller.RestSiteUserController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<>();
        set.add(RestSiteUserController.class);
        return set;
    }
}
