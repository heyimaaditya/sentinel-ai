package com.sentinel.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.*;

@Entity // it is a JPA annotation that tells spring boot that this class is an entity
        // class and it will be used to create a table in the database. so, whenever we
        // call this class it will create a table in the database with the name
        // service_nodes and the columns will be id, name, image, status, type,
        // description and last_seen.
@Table(name = "service_nodes") // it is a JPA annotation that tells spring boot that the name of the table in
                               // the database will be service_nodes. so, whenever we call this class it will
                               // create a table in the database with the name service_nodes and the columns
                               // will be id, name, image, status, type, description and last_seen.
@Data
@AllArgsConstructor // it is a lombok annotation that generates a constructor with all arguments for
                    // the class. so, we can use this constructor to create an instance of this
                    // class by passing all the arguments.
@Builder
public class ServiceNode {
    @Id // it is a JPA annotation that tells spring boot that this field will be the
        // primary key of the table in the database. so, whenever we call this class it
        // will create a table in the database with the name service_nodes and the
        // columns will be id, name, image, status, type, description and last_seen and
        // the id column will be the primary key of the table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // it is a JPA annotation that tells spring boot that the value
                                                        // of this field will be generated automatically by the database
                                                        // when we insert a new record into the table. so, whenever we
                                                        // call this class it will create a table in the database with
                                                        // the name service_nodes and the columns will be id, name,
                                                        // image, status, type, description and last_seen and the id
                                                        // column will be the primary key of the table and its value
                                                        // will be generated automatically by the database when we
                                                        // insert a new record into the table.
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String image;
    private String status;
    private String type;

    @Column(columnDefinition = "TEXT") // it is a JPA annotation that tells spring boot that the column in the database
                                       // will be of type TEXT. so, whenever we call this class it will create a table
                                       // in the database with the name service_nodes and the columns will be id, name,
                                       // image, status, type, description and last_seen and the description column
                                       // will be of type TEXT in the database.
    private String description;

    private LocalDateTime lastSeen;

    @PrePersist // it is a JPA annotation that tells spring boot to execute this method before
                // persisting an entity of this class into the database.
    @PreUpdate // it is a JPA annotation that tells spring boot to execute this method before
               // updating an entity of this class in the database.
    protected void onUpdate() {
        lastSeen = LocalDateTime.now();
    }

}
