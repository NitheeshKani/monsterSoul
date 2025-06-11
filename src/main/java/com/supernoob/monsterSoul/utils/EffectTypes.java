package com.supernoob.monsterSoul.utils;

public enum EffectTypes {
    DAMAGE, // DEFAULT
    HEAL,
    POISON, // DoT effect cannot be removed
    PARALYZE, // ban movement alone
    FLAME, // DoT effect can be removed using Ice
    SLEEP, // ban movement and attack
    SHIELD
}
