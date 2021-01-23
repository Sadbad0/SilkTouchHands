package com.visualcody.silktouchhands;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    public static Plugin plugin;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = getConfig();
        getServer().getPluginManager().registerEvents(new events(), this);
        getLogger().info("Enabled SilkTouchHands "+getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled SilkTouchHands "+getDescription().getVersion());
    }




}
