package com.cham.cloudstreamkafka.service;

import com.cham.cloudstreamkafka.model.Tweet;
import com.cham.cloudstreamkafka.stream.TweetStreams;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class KafkaTweetProducer {

    private final TweetStreams tweetStreams;

    public KafkaTweetProducer(TweetStreams tweetStreams) {
        this.tweetStreams = tweetStreams;
    }

    public void publishTweets(final Tweet tweet) {
        System.out.println("Sending tweet " + tweet);
        MessageChannel messageChannel = tweetStreams.outboundTweets();
        messageChannel.send(MessageBuilder
                .withPayload(tweet)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
