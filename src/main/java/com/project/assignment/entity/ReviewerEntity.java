package com.project.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "reviewer")
public class ReviewerEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private TrackEntity track;

    @ManyToMany
    @JoinTable(name = "reviewer_paper",
            joinColumns = @JoinColumn(name = "reviewer_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "paper_id", nullable = false))
    private List<PaperEntity> paperList;
}
