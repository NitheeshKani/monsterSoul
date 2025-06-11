package com.supernoob.monsterSoul.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.supernoob.monsterSoul.entity.Player;
import com.supernoob.monsterSoul.repo.PlayerRepo;
import com.supernoob.monsterSoul.utils.Cryption;
import com.supernoob.monsterSoul.utils.Region;
import com.supernoob.monsterSoul.utils.DTO.PlayerError;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    public PlayerError createNewPlayer(Player player) {
        // encrypt password
        try {
            String rawPass = player.getPassword();
            long timeNow = System.currentTimeMillis();
            String encryptedPass = Cryption.encrypt(rawPass, timeNow);

            // set default values
            // TODO: add default skills
            player.setCreatedTime(timeNow);
            player.setWinStreak(0);
            player.setCurrentRegion(Region.VELORIA_PLAINS);
            player.setPassword(encryptedPass);

            playerRepo.save(player);

        } catch (DataIntegrityViolationException e) {
            return new PlayerError(400, "Failed - Player Already Exists", null);
        }
        return new PlayerError(200, "Success", player);

    }

    public PlayerError AuthPlayer(String playerName, String password) {
        Player player = playerRepo.findByPlayerName(playerName);
        if (player == null) {
            return new PlayerError(400, "Failed - Player Not Found", null);

        }

        String encryptedPass = Cryption.encrypt(password, player.getCreatedTime());
        if (player.getPassword().equals(encryptedPass)) {
            return new PlayerError(200, "Success", player);
        }
        return new PlayerError(400, "Failed - Wrong Password", null);
    }

    public PlayerError updatePlayer(UUID id, Player newData) {
        try {
            Player existing = playerRepo.findById(id).orElse(null);
            if (existing == null) {
                return new PlayerError(404, "Failed - Player not found", null);
            }

            // Ignore playerName from request — it should not change
            // Update other fields only
            // existing.setSoulType(newData.getSoulType());
            existing.setCurrentRegion(newData.getCurrentRegion());
            existing.setWinStreak(newData.getWinStreak());
            existing.setDefeatedMonsters(newData.getDefeatedMonsters());
            existing.setLearntSkills(newData.getLearntSkills());
            existing.setSkills(newData.getSkills());
            // Update other fields as needed

            playerRepo.save(existing);
            return new PlayerError(200, "Player updated successfully", existing);

        } catch (Exception e) {
            return new PlayerError(500, "Failed - " + e.getMessage(), null);
        }
    }

    public PlayerError deletePlayer(UUID id) {
        try {
             playerRepo.deleteById(id);
            return new PlayerError(200, "Player deleted successfully", null);
        } catch (Exception e) {
            return new PlayerError(500, "Failed - " + e.getMessage(), null);
        }
    }

}
