package com.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.sentinel.service.DockerDiscoveryService;
import com.sentinel.service.KafkaDiscoveryService;
import com.sentinel.repository.ServiceNodeRepository;
import com.sentinel.model.ServiceNode;

@SpringBootApplication
public class SentinelBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(
            DockerDiscoveryService docker,
            ServiceNodeRepository repository) {
        return args -> {
            System.out.println("Discovering Docker Containers...");
            docker.discoverContainers().forEach(container -> {
                ServiceNode node = repository.findByName(container.getName())
                        .orElse(ServiceNode.builder().name(container.getName()).build());

                node.setImage(container.getImage());
                node.setStatus(container.getStatus());
                node.setType("CONTAINER");

                repository.save(node);
                System.out.println("Saved/Updated service: " + node.getName());
            });
        };
    }
}
