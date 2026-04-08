package com.sentinel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

@Configuration // it basically tells spring boot that this class is a configuration class and
               // it will be used to create beans. so,whenever apps start it will create a bean
               // of docker client and we can use it in our service class to interact with
               // docker daemon., also app reads this class first.
public class DockerConfig {
    @Bean // it tells spring boot that this method will return a bean that will be managed
          // by spring container. so,whenever we call this method it will return the same
          // instance of docker client that was created when the app started.
    public DockerClient dockerClient() {
        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ApacheDockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .build();

        return DockerClientImpl.getInstance(config)
                .withDockerCmdExecFactory(
                        new com.github.dockerjava.core.DefaultDockerCmdExecFactory(httpClient, objectMapper));
    }
}