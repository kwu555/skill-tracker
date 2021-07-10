package com.example.skilltracker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetSkillResult {
    private Integer count;
    private List<SkillDto> skills;
}
