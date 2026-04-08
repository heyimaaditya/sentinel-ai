package com.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.sentinel.service.DockerDiscoveryService;
import com.sentinel.service.KafkaDiscoveryService;

@SpringBootApplication
public class SentinelBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(DockerDiscoveryService docker, KafkaDiscoveryService kafka) {
        return args -> {
            System.out.println("--- DOCKER ---");
            docker.discoverContainers().forEach(c -> System.out.println("Container: " + c.getName()));

            System.out.println("--- KAFKA ---");
            try {
                kafka.getLiveTopics().forEach(t -> System.out.println("Topic: " + t));
            } catch (Exception e) {
                System.out.println("Kafka not ready yet or empty.");
            }
        };
    }

}