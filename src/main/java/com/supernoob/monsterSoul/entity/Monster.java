package com.supernoob.monsterSoul.entity;

import java.util.Set;
import java.util.UUID;

import com.supernoob.monsterSoul.utils.MonsterType;
import com.supernoob.monsterSoul.utils.RarityType;
import com.supernoob.monsterSoul.utils.Region;
import com.supernoob.monsterSoul.utils.SoulType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "monster")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;
    private int baseHp;
    private int speed;
    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MonsterType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SoulType soulType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RarityType rarity;

    @ManyToMany
    @JoinTable(name = "monster_skill", joinColumns = @JoinColumn(name = "monster_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

}
