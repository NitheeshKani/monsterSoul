package com.supernoob.monsterSoul.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supernoob.monsterSoul.entity.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, UUID> {

    Player findByPlayerName(String playerName);

}
