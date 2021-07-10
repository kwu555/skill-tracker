package com.example.skilltracker.repo;

import com.example.skilltracker.entity.MemberEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepo extends PagingAndSortingRepository<MemberEntity, Integer> {
}
