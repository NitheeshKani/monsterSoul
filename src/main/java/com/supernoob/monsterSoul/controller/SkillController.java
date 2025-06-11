package com.supernoob.monsterSoul.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supernoob.monsterSoul.entity.Skill;
import com.supernoob.monsterSoul.service.SkillsService;
import com.supernoob.monsterSoul.utils.DTO.SkillError;

@RestController
@RequestMapping("/api/skill")
public class SkillController {

    @Autowired
    private SkillsService skillService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSkills(@PathVariable UUID id) {
        SkillError result = skillService.getSkills(id);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());

        }
        return ResponseEntity.ok().body(result.getSkill());
    }

    @PostMapping
    public ResponseEntity<?> createSkill(@RequestBody Skill skill) {
        SkillError result = skillService.createSkills(skill);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
        return ResponseEntity.ok().body(result.getSkill());
    }

    @PutMapping
    public ResponseEntity<?> updateSkill(@RequestBody Skill skill) {
        SkillError result = skillService.updateSkills(skill);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
        return ResponseEntity.ok().body(result.getSkill());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable UUID id) {
        SkillError result = skillService.deleteSkills(id);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
        return ResponseEntity.ok().body(result.getMessage());
    }

}
