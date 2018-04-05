package com.cham.cloudstreamkafka.service;

import com.cham.cloudstreamkafka.model.Tweet;
import com.cham.cloudstreamkafka.stream.TweetStreams;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaTweetConsumer {

    @StreamListener(TweetStreams.OUTPUT)
    public void consumeTweets(@Payload Tweet tweet) {
        System.out.println("Received tweet .." + tweet);
    }
}
