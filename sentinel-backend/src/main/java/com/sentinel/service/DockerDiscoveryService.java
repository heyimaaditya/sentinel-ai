package com.sentinel.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.sentinel.model.ContainerInfo;
import lombok.RequiredArgsConstructor;

@Service //it tells spring boot that this class is a service class and it will be used to handle the business logic of the application. so,whenever we call this class it will create an instance of this class and we can use it to interact with docker daemon.
@RequiredArgsConstructor //it is a lombok annotation that generates a constructor with required arguments for the class

public class DockerDiscoveryService {
    private final DockerClient dockerClient; //it is a docker client that we will use to interact with docker daemon. it is injected by spring boot when the app starts.

    public List<ContainerInfo> discoverContainers() {
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec(); //it is a method that returns a list of all containers that are running on the docker daemon. it uses the docker client to interact with the docker daemon and get the list of containers.
        return containers.stream()
                .map(container -> ContainerInfo.builder() //it is a method that maps the list of containers to a list of container info. it uses the builder pattern to create an instance of container info for each container in the list.
                        .id(container.getId())
                        .name(container.getNames()[0].replace("/", "")) //it gets the first name of the container as the name of the container info. it is because a container can have multiple names but we are only interested in the first name.
                        .image(container.getImage())
                        .state(container.getState())
                        .status(container.getStatus())
                        .build())
                .collect(Collectors.toList()); //it collects the stream of container info into a list and returns it.
    }
}