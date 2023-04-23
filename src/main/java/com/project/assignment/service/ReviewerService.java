package com.project.assignment.service;

import com.project.assignment.config.UserContextHolder;
import com.project.assignment.dto.ReviewerDto;
import com.project.assignment.entity.PaperEntity;
import com.project.assignment.entity.ReviewerEntity;
import com.project.assignment.entity.ReviewerPaperEntity;
import com.project.assignment.entity.ReviewerPaperKey;
import com.project.assignment.repository.PaperRepository;
import com.project.assignment.repository.ReviewerPaperRepository;
import com.project.assignment.repository.ReviewerRepository;
import com.project.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewerService {
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private ReviewerRepository reviewerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewerPaperRepository reviewerPaperRepository;

    public List<ReviewerDto> getAllReviewers() {
        List<ReviewerEntity> reviewerEntityList = reviewerRepository.findAll();
        List<ReviewerDto> reviewerDtoList = reviewerEntityList.stream().map(
            reviewerEntity -> new ReviewerDto(reviewerEntity.getId(),
            userRepository.findById(reviewerEntity.getId()).get().getName())
        ).collect(Collectors.toList());
        return reviewerDtoList;
    }

    public List<ReviewerDto> assignReviewers(Long id, List<Long> reviewerIds) {
        List<ReviewerPaperEntity> reviewerPaperEntityList = reviewerPaperRepository.findByPaperId(id);
        List<Long> addReviewerIds = new ArrayList<>();
        List<Long> remainReviewIds = new ArrayList<>();
        for (ReviewerPaperEntity reviewerPaperEntity : reviewerPaperEntityList) {
            Long currentId = reviewerPaperEntity.getReviewer().getId();
            if (!reviewerIds.contains(currentId)) {
                reviewerPaperRepository.delete(reviewerPaperEntity);
            } else {
                remainReviewIds.add(currentId);
            }
        }

        for (Long currentId : reviewerIds) {
            if (!remainReviewIds.contains(currentId)) {
                addReviewerIds.add(currentId);
            }
        }

        for (Long currentId : addReviewerIds) {
            ReviewerPaperEntity reviewerPaperEntity = new ReviewerPaperEntity(
            new ReviewerPaperKey(currentId, id),
            reviewerRepository.findById(currentId).get(),
            paperRepository.findById(id).get(),
        "Not available", "Not available", "Not available"
            );
            reviewerPaperRepository.save(reviewerPaperEntity);
        }
        reviewerPaperEntityList = reviewerPaperRepository.findByPaperId(id);
        List<ReviewerDto> reviewerDtoList = reviewerPaperEntityList.stream().map(
            reviewerPaperEntity -> new ReviewerDto(reviewerPaperEntity.getId().getReviewerId(),
            userRepository.findById(reviewerPaperEntity.getId().getReviewerId()).get().getName())
        ).collect(Collectors.toList());
        return reviewerDtoList;
    }
}
