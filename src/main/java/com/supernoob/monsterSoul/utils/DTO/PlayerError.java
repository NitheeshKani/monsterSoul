package com.supernoob.monsterSoul.utils.DTO;

import com.supernoob.monsterSoul.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerError {
    private int code;
    private String message;
    private Player player;

}