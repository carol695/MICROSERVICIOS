package org.acme.model;

import java.util.List;

public class Stream {
        private String id;

        private String title;

        private List<Tweet> tweetList;

        public Stream(String id, String title, List<Tweet> tweetList){
            this.id = id;
            this.title = title;
            this.tweetList = tweetList;
        }

        public Stream() {

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Tweet> getTweetList() {
            return tweetList;
        }

        public void setTweetList(List<Tweet> tweetList) {
            this.tweetList = tweetList;
        }
}
