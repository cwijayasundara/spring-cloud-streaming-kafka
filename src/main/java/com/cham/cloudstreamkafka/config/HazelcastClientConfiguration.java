package com.cham.cloudstreamkafka.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.HazelcastKeyValueAdapter;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;
import org.springframework.data.keyvalue.core.KeyValueTemplate;

@Configuration
@EnableHazelcastRepositories(basePackages={"com.cham.cloudstreamkafka"})
public class HazelcastClientConfiguration {

   @Bean
    public HazelcastInstance hazelcastInstance() throws Exception {
        ClientConfig clientConfig = new XmlClientConfigBuilder("hazelcast-client.xml").build();
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

    @Bean
    public HazelcastKeyValueAdapter hazelcastKeyValueAdapter(HazelcastInstance hazelcastInstance) {
        return new HazelcastKeyValueAdapter(hazelcastInstance);
    }

    @Bean
    public KeyValueTemplate keyValueTemplate(HazelcastKeyValueAdapter hazelcastKeyValueAdapter) {
        return new KeyValueTemplate(hazelcastKeyValueAdapter);
    }

    /*@Bean
    @Qualifier("client")
    public HazelcastInstance hazelcastClientInstance() {
        final ClientConfig clientConfig = new ClientConfig();
        final ClientNetworkConfig networkConfig = new ClientNetworkConfig();
        networkConfig.setAddresses(singletonList("127.0.0.1"));
        clientConfig.setNetworkConfig(networkConfig);
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

    @Bean
    public KeyValueTemplate keyValueTemplate() {
        return new KeyValueTemplate(new HazelcastKeyValueAdapter(hazelcastClientInstance()));
    }*/
}
