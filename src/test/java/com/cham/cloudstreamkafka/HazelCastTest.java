package com.cham.cloudstreamkafka;

import com.cham.cloudstreamkafka.config.HazelcastClientConfiguration;
import com.cham.cloudstreamkafka.repository.TweetHazelcastRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HazelcastClientConfiguration.class)
public class HazelCastTest {

    @Resource
    private TweetHazelcastRepository tweetHazelcastRepository;

    @Test
    public void testStart(){
        tweetHazelcastRepository.findAll();
    }

}