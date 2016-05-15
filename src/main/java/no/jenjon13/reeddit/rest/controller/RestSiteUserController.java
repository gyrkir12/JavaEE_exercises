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
    private static final String SITEUSER_ID_PATH = "siteUserId";

    @Inject
    private SiteUserEJB siteUserEJB;

    @GET
    public Response all() {
        final List<SiteUser> siteUsers = siteUserEJB.getAll();
        final SiteUserXMLWrapper userArray = new SiteUserXMLWrapper(siteUsers);
        return Response.ok(userArray).build();
    }

    @GET
    @Path("{" + SITEUSER_ID_PATH + "}")
    public Response getSiteUser(@PathParam(SITEUSER_ID_PATH) long id) {
        SiteUser siteUser = siteUserEJB.getById(id);
        if (siteUser == null) {
            return Response.noContent().build();
        }

        return Response.ok(siteUser).build();
    }

    @POST
    public Response save(SiteUser siteUser) {
        SiteUser user = siteUserEJB.create(siteUser);
        return Response.ok(user).build();
    }
}
