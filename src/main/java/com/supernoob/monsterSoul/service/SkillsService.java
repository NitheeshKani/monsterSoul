package com.supernoob.monsterSoul.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supernoob.monsterSoul.entity.Skill;
import com.supernoob.monsterSoul.repo.SkillRepo;
import com.supernoob.monsterSoul.utils.DTO.SkillError;

@Service
public class SkillsService {

    @Autowired
    private SkillRepo skillRepo;

    public SkillError createSkills(Skill skill) {
        skillRepo.save(skill);
        SkillError skillError = new SkillError(200, "Successfully created", skill);
        return skillError;
    }

    public SkillError updateSkills(Skill skill) {
        skillRepo.save(skill);
        SkillError skillError = new SkillError(200, "Successfully updated", skill);
        return skillError;
    }

    public SkillError deleteSkills(UUID skillId) {
        skillRepo.deleteById(skillId);
        SkillError skillError = new SkillError(200, "Successfully deleted", null);
        return skillError;
    }

    public SkillError getSkills(UUID skillId) {
        Skill skill = skillRepo.findById(skillId).orElse(null);
        if (skill == null) {
            return new SkillError(400, "Failed - Skill not found", null);
        }
        return new SkillError(200, "Success", skill);
    }
}
