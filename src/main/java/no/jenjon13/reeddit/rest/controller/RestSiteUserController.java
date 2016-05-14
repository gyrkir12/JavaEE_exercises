package no.jenjon13.reeddit.rest.controller;

import no.jenjon13.reeddit.data.entities.SiteUser;
import no.jenjon13.reeddit.ejb.impl.SiteUserEJB;
import no.jenjon13.reeddit.rest.util.SiteUserXMLWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Stateless
@Produces({APPLICATION_JSON, APPLICATION_XML})
@Consumes({APPLICATION_JSON, APPLICATION_XML})
@Path("/user")
public class RestSiteUserController {
    @Inject
    private SiteUserEJB siteUserEJB;

    @GET
    public Response all() {
        final List<SiteUser> siteUsers = siteUserEJB.getAll();
        final SiteUserXMLWrapper userArray = new SiteUserXMLWrapper(siteUsers);
        return Response.ok(userArray).build();
    }

    @GET
    @Path("/{siteUserId}")
    public Response getSiteUser(@PathParam("siteUserId") long id) {
        SiteUser siteUser = siteUserEJB.getById(id);
        if (siteUser == null) {
            return Response.noContent().build();
        }

        final List<SiteUser> siteUsers = siteUserEJB.getAll();
        final SiteUserXMLWrapper userArray = new SiteUserXMLWrapper(siteUsers);
        return Response.ok(userArray).build();
    }

}
