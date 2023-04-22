package com.project.assignment.response;

import com.project.assignment.dto.TrackDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TrackListResponse extends BaseResponse {
    private List<TrackDto> data;
}
