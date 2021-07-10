package com.example.skilltracker.service;

import com.example.skilltracker.dto.GetSearchSkillResult;
import com.example.skilltracker.dto.MemberDto;
import com.example.skilltracker.entity.MemberSkillsEntity;
import com.example.skilltracker.mapper.IMemberMapper;
import com.example.skilltracker.repo.IMemberSkillsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillSearchService {

    @Autowired
    private IMemberSkillsRepo memberSkillsRepo;

    /**
     *  get member by skills
     *
     * @param skillId - skillId
     * @param pageable - pageable nullable
     * @return GetSearchSkillResult
     */
    public GetSearchSkillResult getMemberBySkill(Integer skillId, Pageable pageable){

        List<MemberSkillsEntity> entities;
        Integer total;

        if(pageable == null){
            entities = memberSkillsRepo.findBySkillId(skillId);
            total = entities.size();
        }else{
            entities = memberSkillsRepo.findBySkillId(skillId, pageable);
            total = memberSkillsRepo.countBySkillId(skillId);
        }

        List<MemberDto> result = entities.stream().map(IMemberMapper.INSTANCE::toDto).collect(Collectors.toList());
        return GetSearchSkillResult.builder().count(total).result(result).build();
    }

    /**
     *  find member by certain skills
     *
     * @param skillId - skillId
     * @param skillLevel - skillLevel
     * @param pageable - pageable nullable
     * @return GetSearchSkillResult
     */
    public GetSearchSkillResult getMemberWithSkillAndLevel(Integer skillId, Integer skillLevel, Pageable pageable){

        List<MemberSkillsEntity> entities;

        if(pageable == null){
            entities = memberSkillsRepo.findBySkillIdAndSkillLevel(skillId, skillLevel);
        }else{
            entities = memberSkillsRepo.findBySkillIdAndSkillLevel(skillId, skillLevel, pageable);
        }

        List<MemberDto> result = entities.stream().map(IMemberMapper.INSTANCE::toDto).collect(Collectors.toList());
        return GetSearchSkillResult.builder().count(memberSkillsRepo.countBySkillIdAndSkillLevel(skillId, skillLevel))
                .result(result).build();
    }

    /**
     *  get member by skillLevel
     *
     * @param skillLevel - skillLevel
     * @param pageable - pageable nulllable
     * @return - GetSearchSkillResult
     */
    public GetSearchSkillResult getMemberWithSkillLevel(Integer skillLevel, Pageable pageable){

        List<MemberSkillsEntity> entities;

        if(pageable == null){
            entities = memberSkillsRepo.findBySkillLevel(skillLevel);
        }else{
            entities = memberSkillsRepo.findBySkillLevel(skillLevel, pageable);
        }

        List<MemberDto> result = entities.stream().map(IMemberMapper.INSTANCE::toDto).collect(Collectors.toList());
        return GetSearchSkillResult.builder().count(memberSkillsRepo.countBySkillLevel(skillLevel))
                .result(result).build();
    }
}
