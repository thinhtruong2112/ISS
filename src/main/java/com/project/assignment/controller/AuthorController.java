package com.project.assignment.controller;

import com.project.assignment.dto.AuthorDto;
import com.project.assignment.exception.InternalServerException;
import com.project.assignment.response.AuthorListResponse;
import com.project.assignment.service.AuthorService;
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
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    public ResponseEntity<?> getAllAuthors() {
        try {
            List<AuthorDto> authorDtoList = authorService.getAllAuthors();
            AuthorListResponse authorListResponse = new AuthorListResponse(authorDtoList);
            authorListResponse.setMessage("Get all authors successful.");
            return new ResponseEntity<>(authorListResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
