package org.acme.controller;


import org.acme.model.User;
import org.acme.services.UserService;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UserController {

    @Inject
    UserService userService;

    @GET
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public User getUserById(String id){
        ObjectId objectId = new ObjectId(id);
        User usuario = userService.getUserById(objectId);
        if (usuario == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return usuario;
    }

    @GET
    @Path("/email/{email}")
    public User getUserByEmail(String email){
        User usuario = userService.getUserByEmail(email);
        if (usuario == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return usuario;
    }



    @POST
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }



    @PUT
    public Response updateUser(String id, User user) {
        userService.updateUser(id, user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }


    @DELETE
    @Path("/{id}")
    public Response RemoveUser(String id) {
        userService.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT.ordinal()).entity(id).build();
    }
}

