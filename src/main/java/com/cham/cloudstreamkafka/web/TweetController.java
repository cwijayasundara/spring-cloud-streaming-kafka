package com.cham.cloudstreamkafka.web;

import com.cham.cloudstreamkafka.model.Tweet;
import com.cham.cloudstreamkafka.service.KafkaTweetProducer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class TweetController {

    private final KafkaTweetProducer kafkaTweetProducer;

    public TweetController(KafkaTweetProducer kafkaTweetProducer) {
        this.kafkaTweetProducer = kafkaTweetProducer;
    }

    @GetMapping("/tweet")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void tweets(@RequestParam("message") String message) {

        System.out.println("Inside TweetController.tweets..");

        for (int count=1; count<10;count++) {
            Tweet tweet = new Tweet();
            tweet.setTweetId(count);
            tweet.setUser("Chaminda");
            tweet.setMessage(message);
            kafkaTweetProducer.publishTweets(tweet);
        }
    }
}
