package com.project.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaperDto {
    private Long id;
    private String name;
    private Long totalPage;
    private String finalResult;
    private String status;
    private String trackName;
    private String authorListName;
}
