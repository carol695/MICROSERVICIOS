package org.acme.model;

import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.Date;

@MongoEntity(database = "arep-laboratorio", collection = "tweets")

public class Tweet {
    private String id;

    private String text;

    private User user;

    private Date date;

    public Tweet(String text, User user){
        this.text = text;
        this.user = user;
        this.date = new Date();
    }

    public Tweet() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public  User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
