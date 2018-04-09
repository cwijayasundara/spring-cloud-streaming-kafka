package com.cham.cloudstreamkafka.repository;

import com.cham.cloudstreamkafka.model.Tweet;
import org.springframework.data.hazelcast.repository.HazelcastRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  TweetHazelcastRepository extends HazelcastRepository<Tweet, String> {

}
