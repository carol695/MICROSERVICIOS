package org.acme.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.acme.model.Stream;
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
public class StreamServiceImp implements StreamService{

    @Inject
    MongoClient mongoClient;

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("arep-laboratorio").getCollection("streams");
    }

    @Override
    public List<Stream> getAllStreams() {
        return null;
    }

    @Override
    public void createStream(Stream stream) {

    }

    @Override
    public Stream getStreamById(ObjectId id) {
        Document streamDocument = (Document) getCollection().find(new Document("_id", id)).first();
        Stream stream = new Stream();
        stream.setId(String.valueOf(streamDocument.getObjectId("_id")));
        List<Document> tweets = streamDocument.getList("tweetList", Document.class);
        List<Tweet> tweetList = new ArrayList<>();
        tweets.forEach(document -> {
            Tweet newTweet = new Tweet();
            newTweet.setId(String.valueOf(document.getObjectId("_id")));

            Document document1 = (Document) document.get("user");
            User user1 = new User(document1);
            newTweet.setUser(user1);
            newTweet.setDate(document.getDate("Date"));
            newTweet.setText(document.getString("text"));
            tweetList.add(newTweet);
        });
        stream.setTweetList(tweetList);
        return stream;
    }

    @Override
    public Stream updateStream(String id, Stream stream) {
        return null;
    }

    @Override
    public void deleteStream(String id) {
        ObjectId objectId = new ObjectId(id);
        getCollection().deleteOne(Filters.eq("_id", objectId));
    }
}
