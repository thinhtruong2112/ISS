package com.project.assignment.service;

import com.project.assignment.dto.TrackDto;
import com.project.assignment.entity.TrackEntity;
import com.project.assignment.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackService {
    @Autowired
    private TrackRepository trackRepository;

    public List<TrackDto> getAllTracks() {
        List<TrackEntity> trackEntityList = trackRepository.findAll();
        List<TrackDto> trackDtoList = trackEntityList.stream().map(
                trackEntity -> new TrackDto(trackEntity.getId(), trackEntity.getName())
        ).collect(Collectors.toList());
        return trackDtoList;
    }
}
