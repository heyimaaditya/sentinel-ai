package com.sentinel.model;

import lombok.Builder;
import lombok.Data;

@Data //it is a lombok annotation that generates getters, setters, toString, equals and hashCode methods for the class. so, we don't have to write them manually.
@Builder //it is a lombok annotation that generates a builder pattern for the class. so, we can
public class ContainerInfo {
    private String id;
    private String name;
    private String image;
    private String state;
    private String status;
}