package com.example.skilltracker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetSearchSkillResult {
    Integer count;
    List<MemberDto> result;
}
