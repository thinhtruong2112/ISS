package com.project.assignment.controller;

import com.project.assignment.config.UserContextHolder;
import com.project.assignment.constant.UserEnum;
import com.project.assignment.dto.PaperDto;
import com.project.assignment.exception.InternalServerException;
import com.project.assignment.response.PaperListResponse;
import com.project.assignment.response.PaperResponse;
import com.project.assignment.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/papers")
public class PaperController {
    @Autowired
    private PaperService paperService;

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
}
