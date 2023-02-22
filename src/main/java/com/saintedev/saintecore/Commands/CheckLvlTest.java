package com.saintedev.saintecore.Commands;

import com.saintedev.saintecore.Listeners.LevelListener;
import com.saintedev.saintecore.Managers.LevelManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckLvlTest implements CommandExecutor {
    private LevelListener levelListener;
    private LevelManager levelManager;

    public CheckLvlTest(LevelListener levelListener, LevelManager levelManager) {
        this.levelListener = levelListener;
        this.levelManager = levelManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int playerXp = levelListener.getPlayerXp(player.getUniqueId());
            int playerLevel = levelManager.getPlayerLevel(playerXp);
            sender.sendMessage("Your level is: " + playerLevel);
        }
        return true;
    }
}

