package com.project.assignment.service;

import com.project.assignment.repository.ReviewerPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewerPaperService {
    @Autowired
    private ReviewerPaperRepository reviewerPaperRepository;
}
