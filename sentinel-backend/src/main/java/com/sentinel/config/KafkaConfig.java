package com.sentinel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.admin.AdminClient; // it is a kafka admin client that we will use to interact with kafka cluster. it is used to create topics, delete topics, list topics, etc.
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value("${kafka.bootstrap-servers}") // it is a spring annotation that injects the value of the property
                                         // kafka.bootstrap-servers from the application.properties file into this
                                         // variable. so, we can use this variable to get the value of the bootstrap
                                         // servers when we create the kafka admin client bean.
    private String bootstrapServers;

    @Bean
    public AdminClient kafkaAdminClient() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return AdminClient.create(config); // it creates a kafka admin client using the configuration map that we
                                           // created. it uses the bootstrap servers that we injected from the
                                           // application.properties file to connect to the kafka cluster. so, whenever
                                           // we call this bean it will return the same instance of the kafka admin
                                           // client that was created when the app started.
    }
}
