package com.project.assignment.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BaseErrorDetail {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
}

