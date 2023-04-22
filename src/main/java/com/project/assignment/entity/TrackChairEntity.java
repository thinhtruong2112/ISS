package com.project.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "track_chair")
public class TrackChairEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private TrackEntity track;
}
