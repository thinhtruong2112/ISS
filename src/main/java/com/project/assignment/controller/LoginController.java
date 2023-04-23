package com.project.assignment.controller;

import com.project.assignment.dto.JwtDto;
import com.project.assignment.exception.InternalServerException;
import com.project.assignment.exception.UnauthorizedException;
import com.project.assignment.request.LoginRequest;
import com.project.assignment.response.JwtResponse;
import com.project.assignment.security.CustomUser;
import com.project.assignment.security.JpaUserDetailsService;
import com.project.assignment.security.JwtUtils;
import com.project.assignment.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword(),
                new ArrayList<>()));
            CustomUser user = (CustomUser) jpaUserDetailsService.loadUserByUsername(request.getUsername());
            String accessToken = jwtUtils.generateAccessToken(user);
            String refreshToken = jwtUtils.generateRefreshToken(user);
            JwtDto jwtDto = new JwtDto(user.getId(), user.getName(), user.getUsername(),
                user.getRole(), accessToken, refreshToken);
            JwtResponse jwtResponse = new JwtResponse(jwtDto);
            jwtResponse.setMessage("Login successful.");
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }
}
