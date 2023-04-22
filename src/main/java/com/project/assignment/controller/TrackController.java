package com.project.assignment.controller;

import com.project.assignment.dto.TrackDto;
import com.project.assignment.exception.InternalServerException;
import com.project.assignment.response.TrackListResponse;
import com.project.assignment.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tracks")
public class TrackController {
    @Autowired
    private TrackService trackService;

    @GetMapping("")
    public ResponseEntity<?> getAllTracks() {
        try {
            List<TrackDto> trackDtoList = trackService.getAllTracks();
            TrackListResponse trackListResponse = new TrackListResponse(trackDtoList);
            trackListResponse.setMessage("Get all tracks successful.");
            return new ResponseEntity<>(trackListResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
