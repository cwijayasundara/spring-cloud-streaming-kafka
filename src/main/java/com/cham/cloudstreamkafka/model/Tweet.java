package com.cham.cloudstreamkafka.model;

public class Tweet {

    private int tweetId;
    private String user;
    private String message;

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", user='" + user + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
