package com.example.skilltracker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetMemberDto {

    private Integer memberId;
    private String forename;
    private String surname;
    private List<MemberSkillDto> skills;
}
