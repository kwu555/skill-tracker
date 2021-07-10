package com.example.skilltracker.controller;

import com.example.skilltracker.dto.GetSearchSkillResult;
import com.example.skilltracker.service.SkillSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/api/search")
public class SkillSearchController {

    @Autowired
    private SkillSearchService skillSearchService;

    // TODO SWAGGER to be completed
    @GetMapping(value="/skills/{skillId}")
    public ResponseEntity<GetSearchSkillResult> searchSkill(@PathVariable("skillId") Integer skillId,
                                                          @RequestParam(name = "page", required = false) Integer page,
                                                          @RequestParam(name ="size", required = false) Integer size){

        Pageable pageable = null;

        if(page != null && size != null){
            pageable = PageRequest.of(page, size);
        }

        GetSearchSkillResult result = skillSearchService.getMemberBySkill(skillId, pageable);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value="/skills/{skillId}/level/{skillLevel}")
    public ResponseEntity<GetSearchSkillResult> searchSkillAndLevel(@PathVariable("skillId") Integer skillId,
                                                                    @PathVariable("skillLevel") Integer skillLevel,
                                                                    @RequestParam(name = "page", required = false) Integer page,
                                                                    @RequestParam(name ="size", required = false) Integer size){

        Pageable pageable = null;

        if(page != null && size != null){
            pageable = PageRequest.of(page, size);
        }

        GetSearchSkillResult result = skillSearchService.getMemberWithSkillAndLevel(skillId, skillLevel, pageable);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value="/level/{skillLevel}")
    public ResponseEntity<GetSearchSkillResult> searchLevel(@PathVariable("skillLevel") Integer skillLevel,
                                                            @RequestParam(name = "page", required = false) Integer page,
                                                            @RequestParam(name ="size", required = false) Integer size){

        Pageable pageable = null;

        if(page != null && size != null){
            pageable = PageRequest.of(page, size);
        }

        GetSearchSkillResult result = skillSearchService.getMemberWithSkillLevel(skillLevel, pageable);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
