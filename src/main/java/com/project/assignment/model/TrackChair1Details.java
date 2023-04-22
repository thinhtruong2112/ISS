package com.project.assignment.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "track_chair_1.datasource")
public class TrackChair1Details {
    private String url;
    private String password;
    private String username;
    // Generates Getters and Setters...
}