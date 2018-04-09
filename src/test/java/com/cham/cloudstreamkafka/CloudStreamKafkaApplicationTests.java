package com.cham.cloudstreamkafka;

import com.cham.cloudstreamkafka.model.Tweet;
import com.cham.cloudstreamkafka.service.KafkaTweetConsumer;
import com.cham.cloudstreamkafka.service.KafkaTweetProducer;
import info.batey.kafka.unit.KafkaUnit;
import kafka.server.KafkaServerStartable;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudStreamKafkaApplicationTests {

	private KafkaUnit kafkaUnitServer;

	@Autowired
	private KafkaTweetProducer kafkaTweetProducer;

	private KafkaTweetConsumer kafkaTweetConsumer;

	@Before
	public void setUp() throws Exception{
		kafkaUnitServer = new KafkaUnit(5000, 5001);
		kafkaUnitServer.setKafkaBrokerConfig("log.segment.bytes", "1024");
		kafkaUnitServer.startup();
		//given
		String testTopic = "spring-cloud-stream";
		kafkaUnitServer.createTopic(testTopic);
	}

	@After
	public void shutDown() throws Exception{
		System.out.println("Inside the shutDown.. ");
		Field f = kafkaUnitServer.getClass().getDeclaredField("broker");
		f.setAccessible(true);
		KafkaServerStartable broker = (KafkaServerStartable) f.get(kafkaUnitServer);
		assertEquals(1024, (int) broker.serverConfig().logSegmentBytes());
		kafkaUnitServer.deleteAllTopics();
		kafkaUnitServer.shutdown();
	}

	@Test
	public void shouldTestIfKafkaConsumerProducedMessages() throws Exception {
		//given
		Tweet tweet = new Tweet();
		tweet.setTweetId(1);
		tweet.setUser("Chaminda");
		tweet.setMessage("I love Scala");
		//when
		kafkaTweetProducer.publishTweets(tweet);
		//then
		List<String> messages = kafkaUnitServer.readMessages("spring-cloud-stream", 1);
		//assertEquals(1, messages.size());
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void kafkaServerIsAvailable() throws Exception {
		assertKafkaStartsAndSendsMessage(kafkaUnitServer);
	}

	protected void assertKafkaStartsAndSendsMessage(final KafkaUnit kafkaUnit) throws Exception {
		//given
		String testTopic = "TestTopic";
		kafkaUnit.createTopic(testTopic);
		ProducerRecord<String, String> keyedMessage = new ProducerRecord<>(testTopic,
				"key",
				"value");
		//when
		kafkaUnit.sendMessages(keyedMessage);
		List<String> messages = kafkaUnit.readMessages(testTopic, 1);
		//then
		assertEquals(Collections.singletonList("value"), messages);
	}


}
