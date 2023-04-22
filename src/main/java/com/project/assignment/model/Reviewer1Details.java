package com.project.assignment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component @Getter @Setter
@ConfigurationProperties(prefix = "reviewer1.datasource")
public class Reviewer1Details {

    private String url;
    private String password;
    private String username;
    // Generates Getters and Setters...
}