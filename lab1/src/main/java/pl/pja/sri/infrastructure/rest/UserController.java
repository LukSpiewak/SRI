package pl.pja.sri.infrastructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pja.sri.app.UserAppService;
import pl.pja.sri.infrastructure.model.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Component
@Path("/rest/api/user")
@Produces("application/json")
@Consumes("application/json")
public class UserController {

    private final UserAppService userAppService;

    @Autowired
    public UserController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @GET
    public Response getAllUser() {
        return Response.ok(userAppService.getAllUser()).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Integer id, @Context Request request) {
        UserDTO user = userAppService.findUserById(Long.valueOf(id));

        CacheControl cc = new CacheControl();
        cc.setMaxAge(60);

        EntityTag etag = new EntityTag(user.hashCode()+"");
        Response.ResponseBuilder response = request.evaluatePreconditions(etag);

        if (response == null) {
            response = Response.ok(user);
            response.tag(etag);
        }

        response.cacheControl(cc);
        return response.build();
    }

    @PUT
    public Response addUser(UserDTO user) {
        URI uri = userAppService.addUser(user);

        return Response.created(uri).build();
    }

    @POST
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, UserDTO user) {
        userAppService.updateUser(id, user);
        return Response.ok().build();
    }
}

