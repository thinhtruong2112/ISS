package com.project.assignment.response;

import com.project.assignment.dto.ReviewerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReviewerListResponse extends BaseResponse {
    private List<ReviewerDto> data;
}
