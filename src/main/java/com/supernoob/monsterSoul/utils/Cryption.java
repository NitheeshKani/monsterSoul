package com.supernoob.monsterSoul.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Cryption {
    public static String encrypt(String password, long key) {
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = longToBytes(key);

        byte[] encrypted = new byte[passwordBytes.length];

        for (int i = 0; i < passwordBytes.length; i++) {
            encrypted[i] = (byte) (passwordBytes[i] ^ keyBytes[i % keyBytes.length]);
        }

        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt is same as encrypt for XOR
    public static String decrypt(String encryptedBase64, long key) {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);
        byte[] keyBytes = longToBytes(key);

        byte[] decrypted = new byte[encryptedBytes.length];

        for (int i = 0; i < encryptedBytes.length; i++) {
            decrypted[i] = (byte) (encryptedBytes[i] ^ keyBytes[i % keyBytes.length]);
        }

        return new String(decrypted, StandardCharsets.UTF_8);
    }

    // Convert long to 8-byte array
    private static byte[] longToBytes(long key) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((key >> (8 * i)) & 0xFF);
        }
        return bytes;
    }
}
