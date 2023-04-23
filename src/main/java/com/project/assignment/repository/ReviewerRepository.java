package com.project.assignment.repository;

import com.project.assignment.entity.ReviewerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepository extends JpaRepository<ReviewerEntity, Long> {
}
