package com.project.assignment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component @Getter @Setter
@ConfigurationProperties(prefix = "author1.datasource")
public class Author1Details {

    private String url;
    private String password;
    private String username;
    // Generates Getters and Setters...
}