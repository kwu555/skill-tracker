package com.example.skilltracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {
    private String forename;
    private String surname;
    private Integer skillId;
    private String skillName;
    private Integer skillLevel;
}
