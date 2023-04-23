package com.project.assignment.request;

import com.project.assignment.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UpdatePaperRequest {
    private String name;
    private Long trackId;
    private Long totalPage;
    private String status;
    private List<AuthorDto> authorList;
    private String finalResult;
}
