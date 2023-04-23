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
}
