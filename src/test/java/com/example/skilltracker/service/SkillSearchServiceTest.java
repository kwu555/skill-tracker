package com.example.skilltracker.service;

import com.example.skilltracker.dto.GetSearchSkillResult;
import com.example.skilltracker.entity.MemberEntity;
import com.example.skilltracker.entity.MemberSkillsEntity;
import com.example.skilltracker.entity.SkillEntity;
import com.example.skilltracker.repo.IMemberSkillsRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
public class SkillSearchServiceTest {

    @InjectMocks
    private SkillSearchService skillSearchService;

    @Mock
    private IMemberSkillsRepo memberSkillsRepo;

    @Test
    public void testGetMemberWithSkill_with_all(){
        // given
        SkillEntity skill = new SkillEntity();
        skill.setId(1);
        skill.setName("JAVA");
        MemberEntity member = new MemberEntity();
        member.setSurname("Test");
        member.setForename("K");
        MemberSkillsEntity memberSkill = new MemberSkillsEntity();
        memberSkill.setMember(member);
        memberSkill.setSkillLevel(4);
        memberSkill.setSkill(skill);

        given(memberSkillsRepo.findBySkillId(any())).willReturn(Collections.singletonList(memberSkill));

        // when
        GetSearchSkillResult result = skillSearchService.getMemberBySkill(1, null);

        // then
        assertEquals(1, result.getCount().intValue());
        assertEquals("Test", result.getResult().get(0).getSurname());
    }

    @Test
    public void testGetMemberWithSkill_with_paging(){
        // given
        SkillEntity skill = new SkillEntity();
        skill.setId(1);
        skill.setName("JAVA");
        MemberEntity member = new MemberEntity();
        member.setSurname("Test");
        member.setForename("K");
        MemberSkillsEntity memberSkill = new MemberSkillsEntity();
        memberSkill.setMember(member);
        memberSkill.setSkillLevel(4);
        memberSkill.setSkill(skill);

        given(memberSkillsRepo.findBySkillId(any(),any())).willReturn(Collections.singletonList(memberSkill));
        given(memberSkillsRepo.countBySkillId(any())).willReturn(2);

        // when
        GetSearchSkillResult result = skillSearchService.getMemberBySkill(1, PageRequest.of(1, 1));

        // then
        assertEquals(2, result.getCount().intValue());
        assertEquals("Test", result.getResult().get(0).getSurname());
    }

    // TODO more tests to be written
}
