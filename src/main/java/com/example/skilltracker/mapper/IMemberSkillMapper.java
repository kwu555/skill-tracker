package com.example.skilltracker.mapper;

import com.example.skilltracker.dto.AddMemberSkillDto;
import com.example.skilltracker.dto.MemberSkillDto;
import com.example.skilltracker.entity.MemberSkillsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IMemberSkillMapper {

    IMemberSkillMapper INSTANCE = Mappers.getMapper(IMemberSkillMapper.class);

    @Mapping(source = "skill.id", target = "skillId")
    @Mapping(source = "skill.name", target = "skillName")
    MemberSkillDto toDto(MemberSkillsEntity entity);

    @Mapping(source = "dto.skillId", target = "skill.id")
    @Mapping(source = "memberId", target = "member.id")
    MemberSkillsEntity toJpa(Integer memberId, AddMemberSkillDto dto);
}
