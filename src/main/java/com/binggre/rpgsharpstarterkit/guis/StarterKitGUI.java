package com.binggre.rpgsharpstarterkit.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class StarterKitGUI {

    public static void openInventory(Player player, String jobName) {
        Inventory inventory = Bukkit.createInventory(null, (6 * 9), jobName + "직업 기본 아이템 설정");
        player.openInventory(inventory);
    }
}