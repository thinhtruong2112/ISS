package com.project.assignment.controller;

import com.project.assignment.dto.JwtDto;
import com.project.assignment.exception.BadRequestException;
import com.project.assignment.exception.InternalServerException;
import com.project.assignment.response.JwtResponse;
import com.project.assignment.security.CustomUser;
import com.project.assignment.security.JpaUserDetailsService;
import com.project.assignment.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/token")
public class RefreshTokenController {
    private final static String BEARER_TYPE = "Bearer ";
    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            String refreshToken = authorizationHeader.substring(BEARER_TYPE.length());
            String username = jwtUtils.extractUsername(refreshToken);
            UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);
            if (jwtUtils.validateToken(refreshToken, userDetails)) {
                String accessToken = jwtUtils.generateAccessToken(userDetails);
                JwtDto jwtDto = new JwtDto(((CustomUser) userDetails).getName(),
                        userDetails.getUsername(), ((CustomUser) userDetails).getRole(),
                        accessToken, refreshToken);
                JwtResponse jwtResponse = new JwtResponse(jwtDto);
                jwtResponse.setMessage("Get new access token successful.");
                return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
            } else {
                throw new BadRequestException("Invalid refresh token");
            }
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
