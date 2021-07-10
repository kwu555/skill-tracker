package com.example.skilltracker.repo;

import com.example.skilltracker.entity.MemberSkillsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMemberSkillsRepo extends PagingAndSortingRepository<MemberSkillsEntity, Integer> {

    Integer countBySkillId(Integer skillId);

    List<MemberSkillsEntity> findBySkillId(Integer skillId);

    List<MemberSkillsEntity> findBySkillId(Integer skillId, Pageable pageable);

    Integer countBySkillIdAndSkillLevel(Integer skillId, Integer skillLevel);

    List<MemberSkillsEntity> findBySkillIdAndSkillLevel(Integer skillId, Integer skillLevel, Pageable pageable);

    List<MemberSkillsEntity> findBySkillIdAndSkillLevel(Integer skillId, Integer skillLevel);

    Integer countBySkillLevel(Integer skillLevel);

    List<MemberSkillsEntity> findBySkillLevel(Integer skillLevel, Pageable pageable);

    List<MemberSkillsEntity> findBySkillLevel(Integer skillLevel);

    void deleteBySkillId(Integer id);

    void deleteByMemberIdAndSkillId(Integer memberId, Integer skillId);
}
