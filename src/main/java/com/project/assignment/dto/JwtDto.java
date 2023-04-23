package com.project.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class JwtDto {
    private Long id;
    private String name;
    private String username;
    private String role;
    private String accessToken;
    private String refreshToken;
}
