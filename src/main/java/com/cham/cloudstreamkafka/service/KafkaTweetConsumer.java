package com.cham.cloudstreamkafka.service;

import com.cham.cloudstreamkafka.model.Tweet;
import com.cham.cloudstreamkafka.stream.TweetStreams;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Queue;

import static java.util.Collections.singletonList;

@Component
public class KafkaTweetConsumer {

    @Autowired
    private HazelcastTweetService hazelcastTweetService;

    @StreamListener(TweetStreams.OUTPUT)
    public void consumeTweets(@Payload Tweet tweet) {
        System.out.println("Received tweet .." + tweet);

        //save to hazelcast
       // hazelcastTweetService.saveTweet(tweet);

        final ClientConfig clientConfig = new ClientConfig();
        final ClientNetworkConfig networkConfig = new ClientNetworkConfig();
        networkConfig.setAddresses(singletonList("127.0.0.1"));
        clientConfig.setNetworkConfig(networkConfig);
        HazelcastInstance client =  HazelcastClient.newHazelcastClient(clientConfig);

        Queue<String> tweetQueue = client.getQueue("tweet");
        tweetQueue.offer(tweet.toString());
        System.out.println("Queue size: " + tweetQueue.size());

        // get from hazelcast
        /*List<Tweet> tweets = hazelcastTweetService.getAllTweets().collectList().block();
        Iterator itr = tweets.iterator();
        while(itr.hasNext()) {
            System.out.println("Elements from Hazelcast is " + itr.next());
        }*/
    }
}
