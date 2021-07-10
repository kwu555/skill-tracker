package com.example.skilltracker.repo;

import com.example.skilltracker.entity.SkillEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISkillRepo extends PagingAndSortingRepository<SkillEntity, Integer> {

    Optional<SkillEntity> findByName(String name);
}
