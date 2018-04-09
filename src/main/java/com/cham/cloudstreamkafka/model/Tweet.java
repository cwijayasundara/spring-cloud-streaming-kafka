package com.cham.cloudstreamkafka.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.io.Serializable;
import java.util.Objects;

@KeySpace("tweet")
public class Tweet implements Serializable {

    @Id
    private int tweetId;
    private String user;
    private String message;

    public Tweet(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return tweetId == tweet.tweetId &&
                Objects.equals(user, tweet.user) &&
                Objects.equals(message, tweet.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tweetId, user, message);
    }

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
