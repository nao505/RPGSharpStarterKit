package com.binggre.rpgsharpstarterkit.listeners;

import com.binggre.rpgsharpstarterkit.command.StarterKitCommand;
import com.binggre.rpgsharpstarterkit.objects.StarterKit;
import com.binggre.rpgsharpstarterkit.objects.StarterKitLoader;
import com.hj.rpgsharp.rpg.apis.rpgsharp.events.character.CharacterCreateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class CharacterListener implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onCharacterCreate(CharacterCreateEvent event) {
        if (event.isCancelled()) return;
        String jobName = event.getJob();
        if (!StarterKitLoader.containsKey(jobName)) return;

        StarterKit kit = StarterKitLoader.get(jobName);
        Player player = event.getPlayer();
        kit.getCommandList().forEach(command -> StarterKitCommand.runCommand(player, command, true));
        kit.getItemList().forEach(item -> player.getInventory().addItem(item));
    }
}