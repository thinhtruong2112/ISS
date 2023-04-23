package com.project.assignment.controller;

import com.project.assignment.dto.PaperDto;
import com.project.assignment.dto.ReviewDto;
import com.project.assignment.dto.ReviewerDto;
import com.project.assignment.exception.InternalServerException;
import com.project.assignment.request.UpdatePaperRequest;
import com.project.assignment.response.PaperListResponse;
import com.project.assignment.response.PaperResponse;
import com.project.assignment.response.ReviewListResponse;
import com.project.assignment.response.ReviewerListResponse;
import com.project.assignment.service.PaperService;
import com.project.assignment.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/papers")
public class PaperController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private ReviewerService reviewerService;

    @GetMapping("")
    public ResponseEntity<?> getAllPapers() {
        try {
            List<PaperDto> paperDtoList = paperService.getAllPapers();
            PaperListResponse paperListResponse = new PaperListResponse(paperDtoList);
            paperListResponse.setMessage("Get all papers successful.");
            return new ResponseEntity<>(paperListResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaperById(@PathVariable Long id) {
        try {
            PaperDto paperDto = paperService.getPaperById(id);
            PaperResponse paperResponse = new PaperResponse(paperDto);
            paperResponse.setMessage("Get paper with id " + id + " successful");
            return new ResponseEntity<>(paperResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaperById(@PathVariable Long id,
        @RequestBody UpdatePaperRequest request) {
        try {
            PaperDto paperDto = paperService.updatePaperById(id, request);
            PaperResponse paperResponse = new PaperResponse(paperDto);
            paperResponse.setMessage("Update paper successful.");
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> assignReviewers(
            @PathVariable Long id,
            @RequestBody List<Long> reviewerIds) {
        try {
            List<ReviewerDto> reviewerDtoList = reviewerService.assignReviewers(id, reviewerIds);
            ReviewerListResponse reviewerListResponse = new ReviewerListResponse(reviewerDtoList);
            reviewerListResponse.setMessage("Assign reviewers successful.");
            return new ResponseEntity<>(reviewerListResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<?> getAllReviews(@PathVariable Long id) {
        try {
            List<ReviewDto> reviewDtoList = paperService.getAllReviewsByPaperId(id);
            ReviewListResponse reviewListResponse = new ReviewListResponse(reviewDtoList);
            reviewListResponse.setMessage("Get all reviews by paper id successful.");
            return new ResponseEntity<>(reviewListResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
