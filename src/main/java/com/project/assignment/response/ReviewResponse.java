package com.project.assignment.response;

import com.project.assignment.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReviewResponse extends BaseResponse {
    private ReviewDto data;
}
