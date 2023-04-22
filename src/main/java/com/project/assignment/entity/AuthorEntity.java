package com.project.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "author")
public class AuthorEntity {
    @Id
    private Long id;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "organization", nullable = false)
    private String organization;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany(mappedBy = "authorList")
    private List<PaperEntity> paperList;
}
