package com.mcnizzy.sp.commands;

import com.mcnizzy.sp.spawny.Spawny;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    Spawny plugin;

    public Spawn(Spawny plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Variables
        FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();
        Player player = (Player) sender;
        //Exist
        if (command.getName().equalsIgnoreCase("spawn")) {
            //Player Message
            if (sender instanceof Player) {
                if (config.contains("Config.Spawn.x")) {
                    Location principal_location = new Location(
                            plugin.getServer().getWorld(config.getString("Config.Spawn.world")),
                            Double.valueOf(config.getString("Config.Spawn.x")),
                            Double.valueOf(config.getString("Config.Spawn.y")),
                            Double.valueOf(config.getString("Config.Spawn.z")),
                            Float.valueOf(config.getString("Config.Spawn.yaw")),
                            Float.valueOf(config.getString("Config.Spawn.pitch")));

                    player.sendMessage("[" + plugin.getName() + "] " +
                            ChatColor.translateAlternateColorCodes('&',
                                    messages.getString("Messages.t-spawn").replaceAll("%player%",
                                            player.getName())));
                    player.teleport(principal_location);
                } else {  //Console Message
                    player.sendMessage("[" + plugin.getName() + "] " +
                            ChatColor.translateAlternateColorCodes('&',
                                    messages.getString("Messages.t-spawn-exist").replaceAll("%player%",
                                            player.getName())));
                    return true;
                }
                return true;
            } else {
                sender.sendMessage("[" + plugin.getName() + "] " +
                        ChatColor.translateAlternateColorCodes('&',
                                messages.getString("Messages.t-console-exist").replaceAll("%player%",
                                        player.getName())));
            }
            return true;
        }
        return true;
    }
}
