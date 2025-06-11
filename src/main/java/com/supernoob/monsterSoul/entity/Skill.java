package com.supernoob.monsterSoul.entity;

import java.util.UUID;

import com.supernoob.monsterSoul.utils.EffectTypes;
import com.supernoob.monsterSoul.utils.RarityType;
import com.supernoob.monsterSoul.utils.SoulType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private int damage;
    private int attackSpeed;
    private String attackPattern;
    private String particleImgUrl;
    private String description;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private SoulType soulType;

    @Enumerated(EnumType.STRING)
    private EffectTypes effect;

    @Enumerated(EnumType.STRING)
    private RarityType rarity;

}
