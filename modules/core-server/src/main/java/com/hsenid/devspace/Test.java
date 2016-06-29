package com.hsenid.devspace;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by hsenid on 6/29/16.
 */
@Path("/test")
public class Test {
    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String test(@PathParam("name") String name) {
        return "Hello, " + name;
    }
}
