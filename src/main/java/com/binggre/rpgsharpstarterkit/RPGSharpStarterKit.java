package com.binggre.rpgsharpstarterkit;

import com.binggre.rpgsharpstarterkit.command.StarterKitCommand;
import com.binggre.rpgsharpstarterkit.io.StarterKitReader;
import com.binggre.rpgsharpstarterkit.io.StarterKitWriter;
import com.binggre.rpgsharpstarterkit.listeners.CharacterListener;
import com.binggre.rpgsharpstarterkit.listeners.InventoryCloseListener;
import com.binggre.rpgsharpstarterkit.listeners.RPGSharpReloadListener;
import com.binggre.rpgsharpstarterkit.objects.StarterKit;
import com.binggre.rpgsharpstarterkit.objects.StarterKitLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RPGSharpStarterKit extends JavaPlugin {

    private static RPGSharpStarterKit instance;

    public static RPGSharpStarterKit getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getCommand("기본아이템").setExecutor(new StarterKitCommand());
        Bukkit.getPluginManager().registerEvents(new CharacterListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new RPGSharpReloadListener(), this);

        StarterKitReader.readAll();
    }

    @Override
    public void onDisable() {
        for (StarterKit kit : StarterKitLoader.getMap().values()) {
            StarterKitWriter.write(kit);
        }
    }
}
