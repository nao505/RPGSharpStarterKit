package com.binggre.rpgsharpstarterkit.listeners;

import com.binggre.rpgsharpstarterkit.io.StarterKitWriter;
import com.binggre.rpgsharpstarterkit.objects.StarterKit;
import com.binggre.rpgsharpstarterkit.objects.StarterKitLoader;
import com.hj.rpgsharp.rpg.apis.utils.MetadataUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryCloseListener implements Listener {

    final String KEY = "StarterKit";

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (!player.hasMetadata(KEY)) return;

        String jobName = (String) MetadataUtil.getMetadata(player, KEY);
        Inventory inventory = event.getInventory();

        List<ItemStack> itemList = new ArrayList<>();
        int size = 0;
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null || itemStack.getType() == Material.AIR) continue;
            itemList.add(itemStack);
            size ++;
        }
        if (size == 0) {
            return;
        }
        StarterKit kit = new StarterKit(jobName, new ArrayList<>(), itemList);
        StarterKitLoader.put(kit);
        StarterKitWriter.write(kit);
        player.sendMessage(kit.getJobName() + "직업의 아이템이 설정되었습니다.");
        MetadataUtil.removeMetadata(player, KEY);
    }
}