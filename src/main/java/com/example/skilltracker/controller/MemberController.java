package com.example.skilltracker.controller;

import com.example.skilltracker.dto.MemberDto;
import com.example.skilltracker.dto.AddMemberSkillDto;
import com.example.skilltracker.dto.GetMemberDto;
import com.example.skilltracker.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // TODO SWAGGER to be completed
    @PostMapping
    public ResponseEntity<GetMemberDto> createMember(@RequestBody MemberDto newMember){

        GetMemberDto result = memberService.addMember(newMember);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<GetMemberDto> getMember(@PathVariable("id") Integer memberId){

        GetMemberDto result = memberService.getMember(memberId);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/{id}/skills")
    public ResponseEntity<GetMemberDto> addMemberSkills(@PathVariable("id") Integer memberId,
                                                        @RequestBody List<AddMemberSkillDto> skills){

        GetMemberDto result = memberService.addMemberSkills(memberId, skills);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}/skills/{skillId}")
    public ResponseEntity<GetMemberDto> deleteMemberSkills(@PathVariable("id") Integer memberId,
                                                           @PathVariable("skillId") Integer skillId){

        GetMemberDto result = memberService.deleteMemberSkills(memberId, skillId);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
