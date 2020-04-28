package com.mcnizzy.sp.spawny;

import com.mcnizzy.sp.commands.Reload;
import com.mcnizzy.sp.commands.SetSpawn;
import com.mcnizzy.sp.commands.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public final class Spawny extends JavaPlugin {

    private FileConfiguration messages = null;
    private File messagesFile = null;
    String pathMessages;
    String pathConfig;
    PluginDescriptionFile PDFile = getDescription();
    public String Version = PDFile.getVersion();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("<======================[ PLUGIN ]=======================>");
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.DARK_AQUA + "The " + PDFile.getName() + ChatColor.DARK_AQUA +
                        " plugin was created by MrNizzy");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"Version: "+PDFile.getVersion());
        Bukkit.getConsoleSender().sendMessage("<======================[ ENABLED ]======================>");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.DARK_AQUA + "The plugin " + PDFile.getName() +
                        ChatColor.DARK_AQUA + " has been disabled.");
    }

    public void assignmentCommands(){
        //this.getCommand("cr").setExecutor(new General(this));
        this.getCommand("SpawnyReloadConfig").setExecutor(new Reload(this));
        this.getCommand("setspawn").setExecutor(new SetSpawn(this));
        this.getCommand("spawn").setExecutor(new Spawn(this));
        //this.getCommand("healthme").setExecutor(new healthme());
    }
    public void registerEvents() {
        //PluginManager pm = getServer().getPluginManager();
        //pm.registerEvents(new movement(), this);
        //pm.registerEvents(new BlockBreak(), this);
    }

    public void registerConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        pathConfig = config.getPath();
        if(!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public FileConfiguration getMessages() {
        if(messages == null){
            reloadMessages();
        }
        return messages;
    }

    public void reloadMessages(){
        if(messages == null){
            messagesFile = new File(getDataFolder(), "locale/messages.yml");
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(this.getResource("locale/messages.yml"),"UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                messages.setDefaults(defConfig);
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public void saveMessages(){
        try{
            messages.save(messagesFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void registerMessages(){
        messagesFile = new File(this.getDataFolder(), "locale/messages.yml");
        if(!messagesFile.exists()){
            this.getMessages().options().copyDefaults(true);
            saveMessages();
        }
    }
}
