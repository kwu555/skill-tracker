package com.example.skilltracker.mapper;

import com.example.skilltracker.dto.SkillDto;
import com.example.skilltracker.entity.SkillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ISkillMapper {

    ISkillMapper INSTANCE = Mappers.getMapper(ISkillMapper.class);

    SkillDto toDto(SkillEntity entity);
}
