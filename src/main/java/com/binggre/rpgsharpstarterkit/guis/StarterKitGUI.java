package com.binggre.rpgsharpstarterkit.guis;

import com.binggre.rpgsharpstarterkit.objects.StarterKit;
import com.binggre.rpgsharpstarterkit.objects.StarterKitLoader;
import com.hj.rpgsharp.rpg.apis.rpgsharp.utils.MetadataUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StarterKitGUI {

    public static void openInventory(Player player, String jobName) {
        Inventory inventory = Bukkit.createInventory(null, (6 * 9), jobName + "직업 기본 아이템 설정");
        StarterKit starterKit = StarterKitLoader.get((String) MetadataUtil.getMetadata(player, "StarterKit"));
        int slot = 0;
        for (ItemStack itemStack : starterKit.getItemList()) {
            inventory.setItem(slot, itemStack);
            slot++;
        }
        player.openInventory(inventory);
    }
}