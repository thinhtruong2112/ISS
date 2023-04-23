package com.project.assignment.controller;

import com.project.assignment.dto.ReviewerDto;
import com.project.assignment.exception.InternalServerException;
import com.project.assignment.response.ReviewerListResponse;
import com.project.assignment.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/reviewers")
public class ReviewerController {
    @Autowired
    private ReviewerService reviewerService;

    @GetMapping("")
    public ResponseEntity<?> getAllReviewers() {
        try {
            List<ReviewerDto> reviewerDtoList = reviewerService.getAllReviewers();
            ReviewerListResponse reviewerListResponse = new ReviewerListResponse(reviewerDtoList);
            reviewerListResponse.setMessage("Get all reviewers successful.");
            return new ResponseEntity<>(reviewerListResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
