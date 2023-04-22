package com.project.assignment.service;

import com.project.assignment.dto.PaperDto;
import com.project.assignment.entity.PaperEntity;
import com.project.assignment.repository.PaperRepository;
import com.project.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaperService {
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private UserRepository userRepository;

    public List<PaperDto> getAllPapers() {
        List<PaperEntity> paperEntityList = paperRepository.findAll();
        List<PaperDto> paperDtoList = paperEntityList.stream().map(
            paperEntity -> new PaperDto(
            paperEntity.getId(), paperEntity.getName(), paperEntity.getTotalPage(),
            paperEntity.getFinalResult(), paperEntity.getStatus(), paperEntity.getTrack().getName(),
            paperEntity.getAuthorList().stream().map(authorEntity ->
            userRepository.findById(authorEntity.getId()).get().getName()).toList().toString())
        ).collect(Collectors.toList());
        return paperDtoList;
    }

    public PaperDto getPaperById(Long id) {
        PaperEntity paperEntity = paperRepository.findById(id).orElseThrow();
        PaperDto paperDto = new PaperDto(paperEntity.getId(), paperEntity.getName(),
            paperEntity.getTotalPage(), paperEntity.getFinalResult(),
            paperEntity.getStatus(), paperEntity.getTrack().getName(),
            paperEntity.getAuthorList().stream().map(authorEntity ->
            userRepository.findById(authorEntity.getId()).get().getName())
            .toList().toString());
        return paperDto;
    }
}
