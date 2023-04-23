package com.project.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReviewerPaperKey implements Serializable {
    @Column(name = "reviewer_id")
    private Long reviewerId;
    @Column(name = "paper_id")
    private Long paperId;
}
