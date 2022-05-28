package com.binggre.rpgsharpstarterkit.listeners;

import com.binggre.rpgsharpstarterkit.io.StarterKitReader;
import com.hj.rpgsharp.rpg.apis.rpgsharp.events.RPGSharpReloadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RPGSharpReloadListener implements Listener {

    @EventHandler
    public void onRPGSharpReload(RPGSharpReloadEvent event) {
        StarterKitReader.readAll();
    }
}