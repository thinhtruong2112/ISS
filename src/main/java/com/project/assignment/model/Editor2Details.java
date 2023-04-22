package com.project.assignment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component @Getter @Setter
@ConfigurationProperties(prefix = "editor2.datasource")
public class Editor2Details {

    private String url;
    private String password;
    private String username;
    // Generates Getters and Setters...
}