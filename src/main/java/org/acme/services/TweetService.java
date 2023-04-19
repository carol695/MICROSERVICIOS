package org.acme.services;

import org.acme.model.Stream;
import org.acme.model.Tweet;
import org.acme.model.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface TweetService {

    List<Tweet> getAllTweets();

    void createTweet(Tweet tweet);

    Optional<Tweet> getTweetById(String id);

    Tweet updateTweet(String id, Tweet tweet);

    void deleteTweet(String id);
}
