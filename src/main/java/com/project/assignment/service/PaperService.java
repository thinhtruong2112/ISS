package com.project.assignment.service;

import com.project.assignment.config.UserContextHolder;
import com.project.assignment.constant.UserEnum;
import com.project.assignment.dto.AuthorDto;
import com.project.assignment.dto.PaperDto;
import com.project.assignment.dto.ReviewDto;
import com.project.assignment.dto.ReviewerDto;
import com.project.assignment.entity.AuthorEntity;
import com.project.assignment.entity.PaperEntity;
import com.project.assignment.entity.ReviewerPaperEntity;
import com.project.assignment.entity.TrackEntity;
import com.project.assignment.exception.InternalServerException;
import com.project.assignment.repository.*;
import com.project.assignment.request.UpdatePaperRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PaperService {
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ReviewerPaperRepository reviewerPaperRepository;

    public List<PaperDto> getAllPapers() {
        List<PaperEntity> paperEntityList = paperRepository.findAll();
        List<PaperDto> paperDtoList = paperEntityList.stream().map(
            paperEntity -> new PaperDto(
            paperEntity.getId(), paperEntity.getName(), paperEntity.getTotalPage(),
            paperEntity.getFinalResult(), paperEntity.getStatus(), paperEntity.getTrack().getName(),
            paperEntity.getAuthorList().stream().map(authorEntity -> new AuthorDto(
            authorEntity.getId(), userRepository.findById(authorEntity.getId()).get().getName()
            )).collect(Collectors.toList()), paperEntity.getTrack().getId(),
            reviewerPaperRepository.findByPaperId(paperEntity.getId()).stream().map(
                reviewerPaperEntity -> new ReviewerDto(reviewerPaperEntity.getId().getReviewerId(),
            userRepository.findById(reviewerPaperEntity.getId().getReviewerId()).get().getName())
            ).collect(Collectors.toList()))
        ).collect(Collectors.toList());
        return paperDtoList;
    }

    public PaperDto getPaperById(Long id) {
        PaperEntity paperEntity = paperRepository.findById(id).orElseThrow();
        PaperDto paperDto = new PaperDto(paperEntity.getId(), paperEntity.getName(),
            paperEntity.getTotalPage(), paperEntity.getFinalResult(),
            paperEntity.getStatus(), paperEntity.getTrack().getName(),
            paperEntity.getAuthorList().stream().map(authorEntity -> new AuthorDto(
            authorEntity.getId(), userRepository.findById(authorEntity.getId()).get().getName()
            )).collect(Collectors.toList()), paperEntity.getTrack().getId(),
            reviewerPaperRepository.findByPaperId(paperEntity.getId()).stream().map(
            reviewerPaperEntity -> new ReviewerDto(reviewerPaperEntity.getId().getReviewerId(),
            userRepository.findById(reviewerPaperEntity.getId().getReviewerId()).get().getName())
            ).collect(Collectors.toList()));
        return paperDto;
    }

    public PaperDto updatePaperById(Long id, UpdatePaperRequest request) {
        PaperEntity paperEntity = paperRepository.findById(id).orElseThrow();
        paperEntity.setName(request.getName());
        TrackEntity trackEntity = trackRepository.findById(request.getTrackId()).orElseThrow();
        paperEntity.setTrack(trackEntity);
        paperEntity.setTotalPage(request.getTotalPage());
        if (UserContextHolder.getBranchContext() != UserEnum.EDITOR_1 &&
            UserContextHolder.getBranchContext() != UserEnum.EDITOR_2) {
            if (!Objects.equals(paperEntity.getStatus(), request.getStatus())) {
                throw new InternalServerException("Can not update status, only editor can update status!");
            }
            if (!Objects.equals(paperEntity.getFinalResult(), request.getFinalResult())) {
                throw new InternalServerException("Can not update status, only editor can update final result!");
            }
        } else {
            paperEntity.setStatus(request.getStatus());
            paperEntity.setFinalResult(request.getFinalResult());
        }
        paperRepository.save(paperEntity);
        List<Long> ids = request.getAuthorList().stream().map(AuthorDto::getId).collect(Collectors.toList());
        List<AuthorEntity> authorEntityList = authorRepository.findAllById(ids);
        paperEntity.setAuthorList(authorEntityList);
        paperRepository.save(paperEntity);
        PaperDto paperDto = new PaperDto(paperEntity.getId(), paperEntity.getName(),
            paperEntity.getTotalPage(), paperEntity.getFinalResult(),
            paperEntity.getStatus(), paperEntity.getTrack().getName(),
            authorEntityList.stream().map(
            authorEntity -> new AuthorDto(authorEntity.getId(),
            userRepository.findById(authorEntity.getId()).get().getName()))
            .collect(Collectors.toList()), paperEntity.getTrack().getId(),
            reviewerPaperRepository.findByPaperId(paperEntity.getId()).stream().map(
            reviewerPaperEntity -> new ReviewerDto(reviewerPaperEntity.getId().getReviewerId(),
            userRepository.findById(reviewerPaperEntity.getId().getReviewerId()).get().getName())
            ).collect(Collectors.toList()));
        return paperDto;
    }

    public List<ReviewDto> getAllReviewsByPaperId(Long id) {
        List<ReviewerPaperEntity> reviewerPaperEntityList = reviewerPaperRepository.findByPaperId(id);
        List<ReviewDto> reviewDtoList = reviewerPaperEntityList.stream().map(
            reviewerPaperEntity -> new ReviewDto(
            reviewerPaperEntity.getId().getReviewerId(),
            userRepository.findById(reviewerPaperEntity.getId().getReviewerId()).get().getName(),
            reviewerPaperEntity.getAppropriateness(), reviewerPaperEntity.getContribution(),
            reviewerPaperEntity.getCorrectness())
        ).collect(Collectors.toList());
        return reviewDtoList;
    }
}
