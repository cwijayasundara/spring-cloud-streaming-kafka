package com.cham.cloudstreamkafka.RnD;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Queue;

import static java.util.Collections.singletonList;

public class GettingStartedHazelcast {
    public static void main(String[] args) {

        final ClientConfig clientConfig = new ClientConfig();
        final ClientNetworkConfig networkConfig = new ClientNetworkConfig();
        networkConfig.setAddresses(singletonList("127.0.0.1"));
        clientConfig.setNetworkConfig(networkConfig);
        HazelcastInstance client =  HazelcastClient.newHazelcastClient(clientConfig);

        Map<Integer, String> mapCustomers = client.getMap("customers");
        mapCustomers.put(1, "Joe");
        mapCustomers.put(2, "Ali");
        mapCustomers.put(3, "Avi");

        System.out.println("Customer with key 1: "+ mapCustomers.get(1));
        System.out.println("Map Size:" + mapCustomers.size());

        Queue<String> queueEmployees = client.getQueue("employees");
        queueEmployees.offer("Tom");
        queueEmployees.offer("Mary");
        queueEmployees.offer("Jane");

        System.out.println("First employee: " + queueEmployees.poll());
        System.out.println("Second employee: "+ queueEmployees.peek());
        System.out.println("Queue size: " + queueEmployees.size());
    }
}

