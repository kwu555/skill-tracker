package com.example.skilltracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberSkillDto {
    private Integer skillId;
    private String skillName;
    private Integer skillLevel;
}
