package com.cham.cloudstreamkafka.service;

import com.cham.cloudstreamkafka.model.Tweet;
import com.cham.cloudstreamkafka.repository.TweetHazelcastRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HazelcastTweetService {

    @Resource
    private TweetHazelcastRepository tweetHazelcastRepository;

    public void saveTweet(Tweet tweet){
        try {
            tweetHazelcastRepository.save(tweet);
        }catch(Exception e){
            System.out.println(" The issue is " + e.getCause());
        }
    }

    /*public Flux<Tweet> getAllTweets(){
        return tweetHazelcastRepository.findAll();
    }*/
}
