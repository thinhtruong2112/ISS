package com.project.assignment.response;

import com.project.assignment.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReviewListResponse extends BaseResponse {
    private List<ReviewDto> data;
}
