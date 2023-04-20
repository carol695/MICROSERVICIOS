package org.acme.controller;


import com.google.gson.Gson;
import org.acme.model.Tweet;
import org.acme.model.User;
import org.acme.services.TweetService;
import org.acme.services.UserService;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/tweets")


public class TweetController {

    @Inject
    TweetService tweetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTweets(){
        List<Tweet> tweets = tweetService.getAllTweets();
        Gson Json = new Gson();
        String res = Json.toJson(tweets);
        return res;
    }

    @GET
    @Path("/{id}")
    public Tweet getTweetById(String id){
       Optional<Tweet> tweet = tweetService.getTweetById(id);
        if (tweet.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return tweet.get();
    }


    @POST
    public Response createTweet(Tweet tweet) {
        tweetService.createTweet(tweet);
        return Response.status(Response.Status.CREATED).entity(tweet).build();
    }



    @PUT
    public Response updateUser(String id, Tweet tweet) {
        tweetService.updateTweet(id, tweet);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @DELETE
    @Path("/{id}")
    public Response RemoveUser(String id) {
        tweetService.deleteTweet(id);
        return Response.status(Response.Status.NO_CONTENT.ordinal()).build();
    }

}
