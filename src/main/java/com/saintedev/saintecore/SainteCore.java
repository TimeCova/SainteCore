package com.saintedev.saintecore;

import com.saintedev.saintecore.Commands.CheckLvlTest;
import com.saintedev.saintecore.Listeners.LevelListener;
import com.saintedev.saintecore.Managers.LevelManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class SainteCore extends JavaPlugin {
    private Logger log = Bukkit.getLogger();
    @Override
    public void onEnable() {
        LevelListener levelListener = new LevelListener(this);
        LevelManager levelManager = new LevelManager();
        log.info("Initialization process started.");
        getServer().getPluginManager().registerEvents(new LevelListener(this), this);
        CheckLvlTest checkLvlTest = new CheckLvlTest(levelListener, levelManager);
        getCommand("checklvltest").setExecutor(checkLvlTest);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
