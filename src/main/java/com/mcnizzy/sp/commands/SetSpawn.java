package com.mcnizzy.sp.commands;

import com.mcnizzy.sp.spawny.Spawny;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

    Spawny plugin;
    public SetSpawn(Spawny plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();
        Player player = (Player) sender;
        if(command.getName().equalsIgnoreCase("setspawn")) {
            if(sender instanceof Player) {
                Location principal_location = player.getLocation();

                config.set("Config.Spawn.x", principal_location.getX());
                config.set("Config.Spawn.y", principal_location.getY());
                config.set("Config.Spawn.z", principal_location.getZ());
                config.set("Config.Spawn.yaw", principal_location.getYaw());
                config.set("Config.Spawn.pitch", principal_location.getPitch());
                config.set("Config.Spawn.world", principal_location.getWorld().getName());
                plugin.saveConfig();
                player.sendMessage(plugin.getName() +
                        ChatColor.translateAlternateColorCodes('&',
                        messages.getString("Messages.t-setspawn").replaceAll("%player%",
                                player.getName())));

                return true;
            }
            return true;
        }else{
            sender.sendMessage("["+plugin.getName()+"] " +
                    ChatColor.translateAlternateColorCodes('&',
                            messages.getString("Messages.t-console-exist").replaceAll("%player%",
                                    player.getName())));
        }
        return true;
    }
}
