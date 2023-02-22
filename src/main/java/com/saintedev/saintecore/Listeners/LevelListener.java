package com.saintedev.saintecore.Listeners;

import com.saintedev.saintecore.Managers.LevelManager;
import com.saintedev.saintecore.SainteCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import sun.audio.AudioPlayer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LevelListener implements Listener {
    
    private final Map<EntityType, Integer> xp = new HashMap<>();
    private final File configFile;
    private final FileConfiguration config;
    public LevelListener(SainteCore plugin) {
        xp.put(EntityType.SKELETON, 10);
        xp.put(EntityType.ZOMBIE, 15);
        xp.put(EntityType.CREEPER, 20);
        xp.put(EntityType.SPIDER, 5);

        configFile = new File(plugin.getDataFolder(), "player_xp.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }
        @EventHandler
        public void onEntityDeath(EntityDeathEvent event) {
            Player player = event.getEntity().getKiller();
            if (player == null) {
                return;
            }
            int playerXp = config.getInt(player.getUniqueId().toString(), 0);

            EntityType entityType = event.getEntityType();
            if (xp.containsKey(entityType)) {
                playerXp += xp.get(entityType);
                config.set(player.getUniqueId().toString(), playerXp);
                try {
                    config.save(configFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    public int getPlayerXp(UUID playerUuid) {
        return config.getInt(playerUuid.toString(), 0);
    }
}
