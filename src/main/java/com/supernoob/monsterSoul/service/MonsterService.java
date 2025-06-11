package com.supernoob.monsterSoul.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supernoob.monsterSoul.entity.Monster;
import com.supernoob.monsterSoul.repo.MonsterRepo;
import com.supernoob.monsterSoul.utils.RarityType;
import com.supernoob.monsterSoul.utils.Region;
import com.supernoob.monsterSoul.utils.DTO.MonsterError;

@Service
public class MonsterService {
    @Autowired
    private MonsterRepo monsterRepo;

    public MonsterError getMonster(UUID id) {
        Monster result = monsterRepo.findById(id).orElse(null);
        if (result == null) {
            return new MonsterError(400, "Failed - Monster not found", null);
        }
        return new MonsterError(200, "Success", result);
    }

    public MonsterError getRandMonster(RarityType rarity, Region region) {
        List<Monster> result = monsterRepo.findAllByRarityAndRegion(rarity, region);
        if (result == null) {
            return new MonsterError(400, "Failed - Monster not found", null);
        }
        int rand = (int) (Math.random() * result.size());
        return new MonsterError(200, "Success", result.get(rand));
    }

    public MonsterError getAllMonsterInRegion(Region region) {
        List<Monster> result = monsterRepo.findAllByRegion(region);
        if (result == null) {
            return new MonsterError(400, "Failed - Monster not found", null);
        }
        return new MonsterError(200, "Success", null);
    }

    public MonsterError createMonster(Monster monster) {
        try {

            monsterRepo.save(monster);
        } catch (Exception e) {
            return new MonsterError(400, "Failed - " + e.getMessage(), null);
        }

        return new MonsterError(200, "Success", monster);
    }

    public MonsterError deleteMonster(UUID id) {
        try {
            monsterRepo.deleteById(id);
        } catch (Exception e) {
            return new MonsterError(400, "Failed - " + e.getMessage(), null);
        }
        return new MonsterError(200, "Success", null);
    }

    public MonsterError updateMonster(Monster newData) {
        try {
            monsterRepo.save(newData);
        } catch (Exception e) {
            return new MonsterError(400, "Failed - " + e.getMessage(), null);
        }
        return new MonsterError(200, "Success", newData);
    }

}
