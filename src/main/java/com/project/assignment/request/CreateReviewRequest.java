package com.project.assignment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateReviewRequest {
    private Long reviewerId;
    private String appropriateness;
    private String contribution;
    private String correctness;
}
