package com.cham.cloudstreamkafka.stream;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface TweetStreams {

    String INPUT = "tweet-in";
    String OUTPUT = "tweet-out";

    @Input(INPUT)
    SubscribableChannel inboundTweets();

    @Output(OUTPUT)
    MessageChannel outboundTweets();
}
