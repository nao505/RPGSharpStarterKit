package com.binggre.rpgsharpstarterkit.io;

import com.binggre.rpgsharpstarterkit.RPGSharpStarterKit;
import com.binggre.rpgsharpstarterkit.objects.StarterKit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class StarterKitWriter {

    public static void write(StarterKit kit) {
        String jobName = kit.getJobName();
        File file = new File(RPGSharpStarterKit.getInstance().getDataFolder() + "\\" + jobName + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        yml.set("명령어", kit.getCommandList());
        yml.set("아이템", kit.getItemList());
        try {
            yml.save(file );
        } catch (IOException ignore) {
        }
    }

}
