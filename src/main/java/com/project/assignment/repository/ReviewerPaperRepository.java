package com.project.assignment.repository;

import com.project.assignment.entity.ReviewerPaperEntity;
import com.project.assignment.entity.ReviewerPaperKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewerPaperRepository extends JpaRepository<ReviewerPaperEntity, ReviewerPaperKey> {
    @Query("SELECT rp FROM ReviewerPaperEntity rp WHERE rp.paper.id = :paperId")
    List<ReviewerPaperEntity> findByPaperId(Long paperId);
}
