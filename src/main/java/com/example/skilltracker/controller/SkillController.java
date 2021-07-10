package com.example.skilltracker.controller;

import com.example.skilltracker.dto.GetSkillResult;
import com.example.skilltracker.dto.SkillDto;
import com.example.skilltracker.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    // TODO SWAGGER to be completed
    @GetMapping
    public ResponseEntity<GetSkillResult> getSkills(@RequestParam(name = "page", required = false) Integer page,
                                                    @RequestParam(name ="size", required = false) Integer size){

        GetSkillResult result;

        if(page == null || size == null){
            result = skillService.getAllSkills();
        }else{
            result = skillService.getSkills(PageRequest.of(page, size));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/{skillName}")
    public ResponseEntity<SkillDto> createSkill(@PathVariable(name = "skillName") String skillName){

        SkillDto result = skillService.createSkill(skillName.toUpperCase());

        if(result == null){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{skillId}/{skillName}")
    public ResponseEntity<SkillDto> updateSkill(@PathVariable(name = "skillId") Integer id,
                                                @PathVariable(name = "skillName") String skillName){

        SkillDto result = skillService.updateSkill(id, skillName.toUpperCase());

        if(result == null){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{skillId}")
    public ResponseEntity deleteSkill(@PathVariable(name = "skillId") Integer id){

        boolean result = skillService.deleteSkill(id);

        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
