package com.cham.cloudstreamkafka;

import com.cham.cloudstreamkafka.stream.TweetStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(TweetStreams.class)
public class CloudStreamKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStreamKafkaApplication.class, args);
	}
}
