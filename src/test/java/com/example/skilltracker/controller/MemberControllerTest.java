package com.example.skilltracker.controller;

import com.example.skilltracker.dto.GetMemberDto;
import com.example.skilltracker.dto.MemberDto;
import com.example.skilltracker.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    @Test
    public void test_createMember_with_200(){

        // given
        MemberDto dto = MemberDto.builder().build();
        given(memberService.addMember(dto)).willReturn(GetMemberDto.builder().build());

        // when
        ResponseEntity<GetMemberDto> result = memberController.createMember(dto);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(memberService, times(1)).addMember(dto);
    }

    @Test
    public void test_getMember_with_200(){
        // given
        given(memberService.getMember(1)).willReturn(GetMemberDto.builder().build());

        // when
        ResponseEntity<GetMemberDto> result = memberController.getMember(1);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(memberService, times(1)).getMember(1);
    }

    @Test
    public void test_getMember_with_404(){
        // given
        given(memberService.getMember(1)).willReturn(null);

        // when
        ResponseEntity<GetMemberDto> result = memberController.getMember(1);

        // then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(memberService, times(1)).getMember(1);
    }

    @Test
    public void test_addMemberSkills_with_200(){
        // given
        given(memberService.addMemberSkills(any(), any())).willReturn(GetMemberDto.builder().memberId(1).build());

        // when
        ResponseEntity<GetMemberDto> result = memberController.addMemberSkills(any(), any());

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(memberService, times(1)).addMemberSkills(any(), any());
    }

    @Test
    public void test_addMemberSkills_with_422(){
        // given
        given(memberService.addMemberSkills(any(), any())).willReturn(null);

        // when
        ResponseEntity<GetMemberDto> result = memberController.addMemberSkills(any(), any());

        // then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
        verify(memberService, times(1)).addMemberSkills(any(), any());
    }

    @Test
    public void test_deleteMemberSkills_with_422(){
        // given
        given(memberService.deleteMemberSkills(any(), any())).willReturn(null);

        // when
        ResponseEntity<GetMemberDto> result = memberController.deleteMemberSkills(any(), any());

        // then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
        verify(memberService, times(1)).deleteMemberSkills(any(), any());
    }

    @Test
    public void test_deleteMemberSkills_with_200(){
        // given
        given(memberService.deleteMemberSkills(any(), any())).willReturn(GetMemberDto.builder().memberId(1).build());

        // when
        ResponseEntity<GetMemberDto> result = memberController.deleteMemberSkills(any(), any());

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(memberService, times(1)).deleteMemberSkills(any(), any());
    }
}
