package com.example.skilltracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkillDto {

    private Integer id;
    private String name;
}
