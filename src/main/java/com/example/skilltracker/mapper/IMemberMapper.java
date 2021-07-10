package com.example.skilltracker.mapper;

import com.example.skilltracker.dto.MemberDto;
import com.example.skilltracker.dto.GetMemberDto;
import com.example.skilltracker.dto.MemberSkillDto;
import com.example.skilltracker.entity.MemberEntity;
import com.example.skilltracker.entity.MemberSkillsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IMemberMapper {

    IMemberMapper INSTANCE = Mappers.getMapper(IMemberMapper.class);

    MemberEntity toJpa(MemberDto dto);

    @Mapping(source = "id", target = "memberId")
    @Mapping(source = "skills", target = "skills", qualifiedByName = "mapSkillsToDto")
    GetMemberDto toDto(MemberEntity entity);

    @Mapping(source = "member.forename", target = "forename")
    @Mapping(source = "member.surname", target = "surname")
    @Mapping(source = "skillLevel", target = "skillLevel")
    @Mapping(source = "skill.id", target = "skillId")
    @Mapping(source = "skill.name", target = "skillName")
    MemberDto toDto(MemberSkillsEntity entity);

    @Named("mapSkillsToDto")
    default List<MemberSkillDto> mapToParticipantToDTO(List<MemberSkillsEntity> skillsEntities){
        if(skillsEntities == null){
            return Collections.emptyList();
        }
        return skillsEntities.stream().map(IMemberSkillMapper.INSTANCE::toDto).collect(Collectors.toList());
    }
}
