package com.example.skilltracker.service;

import com.example.skilltracker.dto.GetSkillResult;
import com.example.skilltracker.dto.SkillDto;
import com.example.skilltracker.entity.SkillEntity;
import com.example.skilltracker.mapper.ISkillMapper;
import com.example.skilltracker.repo.IMemberSkillsRepo;
import com.example.skilltracker.repo.ISkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SkillService {

    @Autowired
    private ISkillRepo skillRepo;

    @Autowired
    private IMemberSkillsRepo memberSkillsRepo;

    /**
     *  get all skills from the table
     * @return - GetSkillResult
     */
    public GetSkillResult getAllSkills(){

        List<SkillDto> result = StreamSupport.stream(skillRepo.findAll().spliterator(), false)
                .map(ISkillMapper.INSTANCE:: toDto)
                .collect(Collectors.toList());

        return GetSkillResult.builder().count(result.size()).skills(result).build();
    }

    /**
     * get skill from table with pagination
     *
     * @param pageable - pageable
     * @return - GetSkillResult
     */
    public GetSkillResult getSkills(Pageable pageable){

        List<SkillDto> result = skillRepo.findAll(pageable).stream().map(ISkillMapper.INSTANCE:: toDto).collect(Collectors.toList());
        return GetSkillResult.builder().count((int) skillRepo.count()).skills(result).build();
    }

    /**
     *  method to create new skill
     *
     * @param name - new skill name
     * @return - SkillDto
     */
    public SkillDto createSkill(String name){

        if(skillRepo.findByName(name).isPresent()){
            return null;
        }

        SkillEntity entity = new SkillEntity();
        entity.setName(name);
        return ISkillMapper.INSTANCE.toDto(skillRepo.save(entity));
    }

    /**
     *  method to update skill name
     *
     * @param skillId - skillId
     * @param name - new Name
     * @return - SkillDto
     */
    public SkillDto updateSkill(Integer skillId, String name){

        Optional<SkillEntity> entity = skillRepo.findById(skillId);
        if(entity.isPresent()){
            SkillEntity result = entity.get();
            result.setName(name);
            return ISkillMapper.INSTANCE.toDto(skillRepo.save(result));
        }

        return null;
    }

    /**
     *  method to delete skill, data from member will also be deleted
     *
     * @param skillId - skillId
     * @return - boolean, true means deleted successful, false means error
     */
    @Transactional
    public boolean deleteSkill(Integer skillId){

        Optional<SkillEntity> entity = skillRepo.findById(skillId);
        if(entity.isPresent()){
            memberSkillsRepo.deleteBySkillId(skillId);
            skillRepo.deleteById(skillId);
            return true;
        }else{
            return false;
        }
    }

}
