package com.project.assignment.response;

import com.project.assignment.dto.JwtDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class JwtResponse extends BaseResponse {
    private JwtDto data;
}
