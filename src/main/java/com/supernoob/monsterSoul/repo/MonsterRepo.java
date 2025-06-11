package com.supernoob.monsterSoul.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supernoob.monsterSoul.entity.Monster;
import com.supernoob.monsterSoul.utils.RarityType;
import com.supernoob.monsterSoul.utils.Region;

@Repository
public interface MonsterRepo extends JpaRepository<Monster, UUID> {

    List<Monster> findAllByRarityAndRegion(RarityType rarity, Region region);

    List<Monster> findAllByRegion(Region region);

}
