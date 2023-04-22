package com.project.assignment.response;

import com.project.assignment.dto.PaperDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaperResponse extends BaseResponse {
    private PaperDto data;
}
