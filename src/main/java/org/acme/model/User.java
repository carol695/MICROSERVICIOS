package org.acme.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.Document;
@MongoEntity(database = "arep-laboratorio", collection = "users")
public class User {
    private String id;
    private String name;
    private String email;
    private String password;

    public User(String id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email= email;
        this.password = password;
    }

    public User() {

    }

    public User(Document document){
        setId(String.valueOf(document.getObjectId("_id")));
        setName(document.getString("name"));
        setEmail(document.getString("email"));
        setPassword(document.getString("password"));

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
