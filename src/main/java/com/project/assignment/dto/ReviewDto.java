package com.project.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReviewDto {
    private Long reviewerId;
    private String reviewerName;
    private String appropriateness;
    private String contribution;
    private String correctness;
}
