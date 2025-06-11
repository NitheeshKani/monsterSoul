package com.supernoob.monsterSoul.utils.DTO;

import com.supernoob.monsterSoul.entity.Monster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonsterError {
    private int code;
    private String message;
    private Monster monster;
}
