package com.project.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "paper")
public class PaperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "total_page", nullable = false)
    private Long totalPage;

    @Column(name = "final_result", nullable = false)
    private String finalResult;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private TrackEntity track;

    @ManyToMany
    @JoinTable(name = "paper_author",
        joinColumns = @JoinColumn(name = "paper_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false))
    private List<AuthorEntity> authorList;
}
