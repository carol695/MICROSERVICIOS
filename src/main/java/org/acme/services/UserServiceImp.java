package org.acme.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.acme.model.Stream;
import org.acme.model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class UserServiceImp implements UserService {


    @Inject
    MongoClient mongoClient;

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("arep-laboratorio").getCollection("users");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                User user = new User();
                user.setId(String.valueOf(document.getObjectId("_id")));
                user.setName(document.getString("name"));
                user.setEmail(document.getString("email"));
                user.setPassword(document.getString("password"));
                list.add(user);
            }
        } finally {
            cursor.close();
        }
        return list;
    }



    @Override
    public void createUser(User user) {

        Document document = new Document()
                .append("name", user.getName())
                .append("email", user.getEmail())
                .append("password", user.getPassword());
        getCollection().insertOne(document);
    }

    @Override
    public User getUserById(ObjectId id) {
            Document userDocument = (Document) getCollection().find(new Document("_id", id)).first();
            User user = new User();
            user.setId(String.valueOf(userDocument.getObjectId("_id")));
            user.setName(userDocument.getString("nombre"));
            user.setEmail(userDocument.getString("email"));
            user.setPassword(userDocument.getString("password"));
            return user;
    }

    @Override
    public Stream updateUser(String id, User user) {
        return null;
    }

    @Override
    public void deleteUser(String id) {
        ObjectId objectId = new ObjectId(id);
        getCollection().deleteOne(Filters.eq("_id", objectId));
    }


}

