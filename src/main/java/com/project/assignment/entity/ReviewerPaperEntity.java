package com.project.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "reviewer_paper")
public class ReviewerPaperEntity {
    @EmbeddedId
    private ReviewerPaperKey id;

    @ManyToOne
    @MapsId("reviewer_id")
    @JoinColumn(name = "reviewer_id")
    private ReviewerEntity reviewer;

    @ManyToOne
    @MapsId("paper_id")
    @JoinColumn(name = "paper_id")
    private PaperEntity paper;

    @Column(name = "appropriateness", nullable = false)
    private String appropriateness;

    @Column(name = "contribution", nullable = false)
    private String contribution;

    @Column(name = "correctness", nullable = false)
    private String correctness;
}
