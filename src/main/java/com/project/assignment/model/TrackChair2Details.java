package com.project.assignment.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "track_chair_2.datasource")
public class TrackChair2Details {
    private String url;
    private String password;
    private String username;
    // Generates Getters and Setters...
}