package com.project.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaperDto {
    private Long id;
    private String name;
    private Long totalPage;
    private String finalResult;
    private String status;
    private String trackName;
    private List<AuthorDto> authorList;
    private Long trackId;
    private List<ReviewerDto> reviewerList;
}
