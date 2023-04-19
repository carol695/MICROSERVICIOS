package org.acme.model;

import java.util.Date;

public class Tweet {
    private String id;

    private String text;

    private User user;

    private Date date;

    public Tweet(String id, String text, User user,  Date date){
        this.id = id;
        this.text = text;
        this.user = user;
        this.date = date;
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
