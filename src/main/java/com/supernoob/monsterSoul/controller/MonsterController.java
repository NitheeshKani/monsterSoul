package com.supernoob.monsterSoul.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supernoob.monsterSoul.entity.Monster;
import com.supernoob.monsterSoul.service.MonsterService;
import com.supernoob.monsterSoul.utils.RarityType;
import com.supernoob.monsterSoul.utils.Region;
import com.supernoob.monsterSoul.utils.DTO.MonsterError;

@RestController
@RequestMapping("/api/monster")
public class MonsterController {

    @Autowired
    private MonsterService monsterService;

    @GetMapping("/rand")
    public ResponseEntity<?> getRandMonster(@RequestParam RarityType rarity, @RequestParam Region region) {
        MonsterError result = monsterService.getRandMonster(rarity, region);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
        return ResponseEntity.ok().body(result.getMonster());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMonsterById(@PathVariable UUID id) {
        MonsterError result = monsterService.getMonster(id);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
        return ResponseEntity.ok().body(result.getMonster());
    }

    @PostMapping
    public ResponseEntity<?> createMonster(@RequestBody Monster entity) {
        MonsterError result = monsterService.createMonster(entity);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok().body(result.getMonster());
    }

    @PutMapping
    public ResponseEntity<?> updateMonster( @RequestBody Monster newData) {
        MonsterError result = monsterService.updateMonster(newData);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
        return ResponseEntity.ok().body(result.getMonster());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMonster(@PathVariable UUID id) {
        MonsterError result = monsterService.deleteMonster(id);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
        return ResponseEntity.ok().body(result.getMessage());
    }


}
