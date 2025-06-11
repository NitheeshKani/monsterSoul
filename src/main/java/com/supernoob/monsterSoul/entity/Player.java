package com.supernoob.monsterSoul.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.supernoob.monsterSoul.utils.Region;
import com.supernoob.monsterSoul.utils.SoulType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String playerName;
    private int winStreak;

    @JsonIgnore
    private long createdTime;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SoulType soulType;

    @Enumerated(EnumType.STRING)
    private Region currentRegion;

    @ManyToOne
    private Monster monster;

    @ManyToMany
    @JoinTable(name = "player_skills", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "player_defeated_monsters", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "monster_id"))
    private Set<Monster> defeatedMonsters = new HashSet<>();

    // Skills learnt in battle (distinct from current skills)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "player_learnt_skills", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> learntSkills = new HashSet<>();

}
