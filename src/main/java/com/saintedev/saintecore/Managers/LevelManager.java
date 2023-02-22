package com.saintedev.saintecore.Managers;
public class LevelManager {
    public int getPlayerLevel(int playerXp) {
        int playerLevel = 1; // Default to level 1 if xp is zero or negative

        int xpThreshold = 500; // Initial xp threshold for level 1

        while (playerXp >= xpThreshold) {
            playerLevel++;
            xpThreshold *= 1.5;
        }

        return playerLevel;
    }
}