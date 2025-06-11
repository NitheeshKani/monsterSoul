package com.supernoob.monsterSoul.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supernoob.monsterSoul.entity.Skill;

@Repository
public interface SkillRepo extends JpaRepository<Skill, UUID> {

}
