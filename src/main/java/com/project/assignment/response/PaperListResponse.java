package com.project.assignment.response;

import com.project.assignment.dto.PaperDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaperListResponse extends BaseResponse {
    private List<PaperDto> data;
}
