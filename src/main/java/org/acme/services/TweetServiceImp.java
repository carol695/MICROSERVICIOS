package org.acme.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.acme.model.Tweet;
import org.acme.model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TweetServiceImp implements TweetService {

    @Inject
    MongoClient mongoClient;

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("arep-laboratorio").getCollection("tweets");
    }

    @Override
    public List<Tweet> getAllTweets() {
            List<Tweet> list = new ArrayList<>();
            MongoCursor<Document> cursor = getCollection().find().iterator();
            try {
                while (cursor.hasNext()) {
                    Document tweetDocument = cursor.next();
                    Tweet tweet = new Tweet();
                    tweet.setId(String.valueOf(tweetDocument.getObjectId("_id")));
                    tweet.setText(tweetDocument.getString("text"));
                    tweet.setDate(tweetDocument.getDate("date"));
                    User userTweet = new User();
                    Document documentUser = (Document) tweetDocument.get("user");
                    userTweet.setId(documentUser.getString("_id"));
                    userTweet.setName(documentUser.getString("name"));
                    userTweet.setEmail(documentUser.getString("email"));
                    userTweet.setPassword(documentUser.getString("password"));
                    tweet.setUser(userTweet);
                    list.add(tweet);
                }
            } finally {
                cursor.close();
            }
            return list;
        }

    @Override
    public void createTweet(Tweet tweet) {
        Tweet newTweet = new Tweet(tweet.getText(), tweet.getUser());
        Document document = new Document()
                .append("user", newTweet.getUser())
                .append("text", newTweet.getText())
                .append("date", newTweet.getDate());
        getCollection().insertOne(document);

    }

    @Override
    public Optional<Tweet> getTweetById(String id) {
        ObjectId objectId = new ObjectId(id);
        Document tweetDocument = (Document) getCollection().find(new Document("_id", objectId)).first();

        Tweet tweet = new Tweet();
        tweet.setId(String.valueOf(tweetDocument.getObjectId("_id")));
        tweet.setText(tweetDocument.getString("text"));
        tweet.setDate(tweetDocument.getDate("date"));

        User userTweet = new User();
        Document documentUser = (Document) tweetDocument.get("user");
        userTweet.setId(documentUser.getString("_id"));
        userTweet.setName(documentUser.getString("name"));
        userTweet.setEmail(documentUser.getString("email"));
        userTweet.setPassword(documentUser.getString("password"));
        tweet.setUser(userTweet);
        return Optional.of(tweet);
    }

    @Override
    public Tweet updateTweet(String id, Tweet tweet) {
        return null;
    }

    @Override
    public void deleteTweet(String id) {
        ObjectId objectId = new ObjectId(id);
        System.out.println("holaaaaaaa"  + id);
        getCollection().deleteOne(Filters.eq("_id", objectId));

    }
}
