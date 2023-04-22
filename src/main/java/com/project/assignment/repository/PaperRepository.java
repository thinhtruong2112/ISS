package com.project.assignment.repository;

import com.project.assignment.entity.PaperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends JpaRepository<PaperEntity, Long> {
}
