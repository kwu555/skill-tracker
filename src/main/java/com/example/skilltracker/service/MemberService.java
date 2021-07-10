package com.example.skilltracker.service;

import com.example.skilltracker.dto.AddMemberSkillDto;
import com.example.skilltracker.dto.GetMemberDto;
import com.example.skilltracker.dto.MemberDto;
import com.example.skilltracker.entity.MemberEntity;
import com.example.skilltracker.entity.MemberSkillsEntity;
import com.example.skilltracker.mapper.IMemberMapper;
import com.example.skilltracker.mapper.IMemberSkillMapper;
import com.example.skilltracker.repo.IMemberRepo;
import com.example.skilltracker.repo.IMemberSkillsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private IMemberRepo memberRepo;

    @Autowired
    private IMemberSkillsRepo memberSkillsRepo;

    /**
     *  method to add a new member
     *
     * @param dto - request
     * @return - new memberDto
     */
    public GetMemberDto addMember(MemberDto dto){

        MemberEntity entity = IMemberMapper.INSTANCE.toJpa(dto);
        return IMemberMapper.INSTANCE.toDto(memberRepo.save(entity));
    }

    /**
     *  method to get member by memberId
     *
     * @param memberId - memberId
     * @return - GetMemberDto
     */
    public GetMemberDto getMember(Integer memberId){

        Optional<MemberEntity> result = memberRepo.findById(memberId);

        return result.map(IMemberMapper.INSTANCE::toDto).orElse(null);
    }

    /**
     *  method to add new skills to a member
     *
     * @param memberId - memberId
     * @param skills - new skills
     * @return - updated GetMemberDto
     */
    public GetMemberDto addMemberSkills(Integer memberId, List<AddMemberSkillDto> skills){

        Optional<MemberEntity> result = memberRepo.findById(memberId);

        if(result.isPresent()){

            List<MemberSkillsEntity> newSkills = skills.stream().map(k -> IMemberSkillMapper.INSTANCE.toJpa(memberId, k))
                    .collect(Collectors.toList());

            MemberEntity memberEntity = result.get();
            memberEntity.getSkills().addAll(newSkills);
            return IMemberMapper.INSTANCE.toDto(memberRepo.save(memberEntity));
        }

        return null;
    }

    /**
     *  method to delete member skills
     *
     * @param memberId - memberId
     * @param skillId - skillId
     * @return - GetMemberDto
     */
    @Transactional
    public GetMemberDto deleteMemberSkills(Integer memberId, Integer skillId){

        Optional<MemberEntity> result = memberRepo.findById(memberId);

        if(result.isPresent()){
            MemberEntity entity = result.get();
            Optional<MemberSkillsEntity> skill = result.get().getSkills().stream()
                    .filter(k->k.getSkill().getId().equals(skillId)).findFirst();
            if(skill.isPresent()){
                entity.getSkills().remove(skill.get());

                GetMemberDto returnResult = IMemberMapper.INSTANCE.toDto(memberRepo.save(entity));
                memberSkillsRepo.deleteByMemberIdAndSkillId(memberId, skillId);
                return returnResult;
            }
        }

        return null;
    }
}
