package com.example.skilltracker.controller;

import com.example.skilltracker.dto.GetSkillResult;
import com.example.skilltracker.dto.SkillDto;
import com.example.skilltracker.service.SkillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class SkillControllerTest {

    @InjectMocks
    private SkillController skillController;

    @Mock
    private SkillService skillService;

    @Test
    public void test_createSkill_with_200(){

        // given
        given(skillService.createSkill(eq("TEST"))).willReturn(SkillDto.builder().build());

        // when
        ResponseEntity<SkillDto> result = skillController.createSkill("test");

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(skillService, times(1)).createSkill(eq("TEST"));
    }

    @Test
    public void test_createSkill_with_422(){

        // given
        given(skillService.createSkill(eq("TEST"))).willReturn(null);

        // when
        ResponseEntity<SkillDto> result = skillController.createSkill("test");

        // then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
        verify(skillService, times(1)).createSkill(eq("TEST"));
    }

    @Test
    public void test_getSkills_with_page_with_200(){

        // given
        given(skillService.getSkills(any())).willReturn(GetSkillResult.builder().build());

        // when
        ResponseEntity<GetSkillResult> result = skillController.getSkills(1, 2);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(skillService, times(1)).getSkills(any());
    }

    @Test
    public void test_getSkills_with_all_with_200(){

        // given
        given(skillService.getAllSkills()).willReturn(GetSkillResult.builder().build());

        // when
        ResponseEntity<GetSkillResult> result = skillController.getSkills(null, null);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(skillService, times(1)).getAllSkills();
    }

    @Test
    public void test_updateSkill_with_200(){

        // given
        given(skillService.updateSkill(eq(1), eq("TEST"))).willReturn(SkillDto.builder().build());

        // when
        ResponseEntity<SkillDto> result = skillController.updateSkill(1, "test");

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(skillService, times(1)).updateSkill(1, "TEST");
    }

    @Test
    public void test_updateSkill_with_422(){

        // given
        given(skillService.updateSkill(eq(1), eq("TEST"))).willReturn(null);

        // when
        ResponseEntity<SkillDto> result = skillController.updateSkill(1, "test");

        // then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
        verify(skillService, times(1)).updateSkill(1, "TEST");
    }

    @Test
    public void test_deleteSkill_with_200(){

        // given
        given(skillService.deleteSkill(eq(1))).willReturn(true);

        // when
        ResponseEntity result = skillController.deleteSkill(1);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(skillService, times(1)).deleteSkill(1);

    }

    @Test
    public void test_deleteSkill_with_404(){

        // given
        given(skillService.deleteSkill(eq(1))).willReturn(false);

        // when
        ResponseEntity result = skillController.deleteSkill(1);

        // then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
        verify(skillService, times(1)).deleteSkill(1);
    }
}
