package com.project.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "track")
public class TrackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "track")
    private List<PaperEntity> paperList;

    @OneToMany(mappedBy = "track")
    private List<ReviewerEntity> reviewerList;

    @OneToMany(mappedBy = "track")
    private List<TrackChairEntity> trackChairList;
}
