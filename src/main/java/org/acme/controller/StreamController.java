package org.acme.controller;


import org.acme.model.Stream;
import org.acme.services.StreamService;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tweets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StreamController {

    @Inject
    StreamService streamService;


    @GET
    public List<Stream> getAllTweets(){
        return streamService.getAllStreams();
    }

    @GET
    @Path("/{id}")
    public Stream getTweetById(String id){
        ObjectId objectId = new ObjectId(id);
        Stream stream = streamService.getStreamById(objectId);
        if (stream == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return stream;
    }


    @POST
    public Response createTweet(Stream stream) {
        streamService.createStream(stream);
        return Response.status(Response.Status.CREATED).entity(stream).build();
    }



    @PUT
    public Response updateUser(String id, Stream stream) {
        streamService.updateStream(id, stream);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @DELETE
    @Path("/{id}")
    public Response RemoveUser(String id) {
        streamService.deleteStream(id);
        return Response.status(Response.Status.NO_CONTENT.ordinal()).build();
    }
}
