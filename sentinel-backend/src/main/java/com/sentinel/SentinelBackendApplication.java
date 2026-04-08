package com.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.sentinel.service.DockerDiscoveryService;

@SpringBootApplication
public class SentinelBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(DockerDiscoveryService service) {
        return args -> {
            System.out.println("Discovering Docker Containers...");
            service.discoverContainers()
                    .forEach(c -> System.out.println(c.getName() + " - " + c.getImage() + " - " + c.getState()));
        };
    }

}