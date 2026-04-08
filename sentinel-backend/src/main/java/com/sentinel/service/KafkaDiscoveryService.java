package com.sentinel.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;

import java.util.concurrent.ExecutionException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class KafkaDiscoveryService {
    private final AdminClient adminClient;

    public Set<String> getLiveTopics() throws ExecutionException, InterruptedException {
        ListTopicsResult topics = adminClient.listTopics(); // it is a method that returns a list of all topics that are
                                                            // present in the kafka cluster. it uses the kafka admin
                                                            // client to interact with the kafka cluster and get the
                                                            // list of topics.
        return topics.names().get(); // it is a method that returns a set of all topic names that are present in the
                                     // kafka cluster. it uses the list of topics that we got from the previous
                                     // method and gets the names of the topics and returns them as a set. so,
                                     // whenever we call this method it will return a set of all topic names that are
                                     // present in the kafka cluster.
    }

    public Set<String> getLiveConsumerGroups() throws ExecutionException, InterruptedException {
        return adminClient.listConsumerGroups().valid().get().stream() // it is a method that returns a set of all
                                                                       // consumer group ids that are present in the
                                                                       // kafka cluster. it uses the kafka admin client
                                                                       // to interact with the kafka cluster and get the
                                                                       // list of consumer groups and then gets the
                                                                       // valid consumer groups and then gets the group
                                                                       // ids of the valid consumer groups and returns
                                                                       // them as a set. so, whenever we call this
                                                                       // method it will return a set of all consumer
                                                                       // group ids that are present in the kafka
                                                                       // cluster.
                .map(group -> group.groupId())
                .collect(java.util.stream.Collectors.toSet());
    }
}
