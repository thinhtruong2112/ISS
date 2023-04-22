package com.project.assignment.service;

import com.project.assignment.dto.AuthorDto;
import com.project.assignment.entity.AuthorEntity;
import com.project.assignment.repository.AuthorRepository;
import com.project.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private UserRepository userRepository;

    public List<AuthorDto> getAllAuthors() {
        List<AuthorEntity> authorEntityList = authorRepository.findAll();
        List<AuthorDto> authorDtoList = authorEntityList.stream().map(
            authorEntity -> new AuthorDto(authorEntity.getId(),
            userRepository.findById(authorEntity.getId()).get().getName())
        ).collect(Collectors.toList());
        return authorDtoList;
    }
}
