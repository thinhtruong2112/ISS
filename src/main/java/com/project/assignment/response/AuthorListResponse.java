package com.project.assignment.response;

import com.project.assignment.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthorListResponse extends BaseResponse {
    private List<AuthorDto> data;
}
