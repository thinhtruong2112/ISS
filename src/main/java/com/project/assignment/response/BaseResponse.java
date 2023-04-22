package com.project.assignment.response;

import lombok.Data;


@Data
public class BaseResponse {
    private String message;
    public void setMessage(String message) {
        this.message = message;
    }
}
