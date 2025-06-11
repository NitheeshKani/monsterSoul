package com.supernoob.monsterSoul.utils.DTO;

import com.supernoob.monsterSoul.entity.Skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillError {
    private int code;
    private String message;
    private Skill skill;

}
