package com.supernoob.monsterSoul.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supernoob.monsterSoul.entity.Player;
import com.supernoob.monsterSoul.service.PlayerService;
import com.supernoob.monsterSoul.utils.DTO.PlayerError;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    /**
     * Creates a new player with encrypted password and current timestamp.
     *
     * @param player the Player object to be created. The player's password is
     *               encrypted
     *               and the creation time is set to the current system time before
     *               saving
     *               to the repository.
     * @apiNote {
     *          "playerName": "Player Name",
     *          "password": "Password",
     *          "soulType": "Soul Type",
     *          }
     */
    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody Player body) {
        PlayerError result = playerService.createNewPlayer(body);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
        return ResponseEntity.ok().body(result.getPlayer());
    }

    @GetMapping
    public ResponseEntity<?> authPlayerByName(@RequestParam String name, @RequestParam String password) {
        PlayerError result = playerService.AuthPlayer(name, password);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
        return ResponseEntity.ok().body(result.getPlayer());
    }

    /**
     * Updates an existing player's information.
     *
     * @param entity the Player object containing updated player information to be
     *               saved.
     * @apiNote {
     *          "currentRegion": "Player Name",
     *          "winStreak": "Password",
     *          "defeatedMonsters": [],
     *          "learntSkills": [],
     *          "skills": [],
     *          }
     */

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable UUID id, @RequestBody Player entity) {
        PlayerError result = playerService.updatePlayer(id, entity);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok().body(result.getPlayer());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable UUID id) {
        PlayerError result = playerService.deletePlayer(id);
        if (result.getCode() == 400) {
            return ResponseEntity.badRequest().body(result.getMessage());

        }
        return ResponseEntity.ok().body(result.getMessage());
    }

}
