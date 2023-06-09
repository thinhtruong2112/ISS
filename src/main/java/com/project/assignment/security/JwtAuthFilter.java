package com.project.assignment.security;

import com.project.assignment.config.UserContextHolder;
import com.project.assignment.constant.UserEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JpaUserDetailsService jpaUserDetailsService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String username;
        String jwtToken = null;

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            jwtToken = authHeader.substring(7);
            username = jwtUtils.extractUsername(jwtToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);
                if (jwtUtils.validateToken(jwtToken, userDetails)) {
                    switch (username) {
                        case "author_1":
                            UserContextHolder.setBranchContext(UserEnum.AUTHOR_1);
                            break;
                        case "author_2":
                            UserContextHolder.setBranchContext(UserEnum.AUTHOR_2);
                            break;
                        case "reviewer_1":
                            UserContextHolder.setBranchContext(UserEnum.REVIEWER_1);
                            break;
                        case "reviewer_2":
                            UserContextHolder.setBranchContext(UserEnum.REVIEWER_2);
                            break;
                        case "editor_1":
                            UserContextHolder.setBranchContext(UserEnum.EDITOR_1);
                            break;
                        case "editor_2":
                            UserContextHolder.setBranchContext(UserEnum.EDITOR_2);
                            break;
                        case "track_chair_1":
                            UserContextHolder.setBranchContext(UserEnum.TRACK_CHAIR_1);
                            break;
                        case "track_chair_2":
                            UserContextHolder.setBranchContext(UserEnum.TRACK_CHAIR_2);
                            break;
                        case "program_chair_1":
                            UserContextHolder.setBranchContext(UserEnum.PROGRAM_CHAIR_1);
                            break;
                        case "program_chair_2":
                            UserContextHolder.setBranchContext(UserEnum.PROGRAM_CHAIR_2);
                            break;
                    }
                    UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {}

        filterChain.doFilter(request, response);
    }
}
