package com.binggre.rpgsharpstarterkit.io;

import com.binggre.rpgsharpstarterkit.RPGSharpStarterKit;
import com.binggre.rpgsharpstarterkit.objects.StarterKit;
import com.binggre.rpgsharpstarterkit.objects.StarterKitLoader;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StarterKitReader {

    public static void readAll() {
        StarterKitLoader.getMap().clear();
        read().forEach(StarterKitLoader::put);
    }

    private static List<StarterKit> read() {
        List<StarterKit> list = new ArrayList<>();
        String path = RPGSharpStarterKit.getInstance().getDataFolder().getPath();
        File[] files = new File(path).listFiles();
        if (files == null) return list;

        for (File file : files) {
            String jobName = file.getName().replace(".yml", "");
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
            StarterKit kit = new StarterKit(jobName, yml.getStringList("명령어"), (List<ItemStack>) yml.getList("아이템"));
            list.add(kit);
        }
        return list;
    }
}
