package com.binggre.rpgsharpstarterkit.objects;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class StarterKit {

    private final String jobName;
    private final List<String> commandList;
    private final List<ItemStack> itemList;

    public StarterKit(String jobName, List<String> commandList, List<ItemStack> itemList) {
        this.jobName = jobName;
        this.commandList = commandList;
        this.itemList = itemList;
    }

    public String getJobName() {
        return jobName;
    }

    public List<String> getCommandList() {
        return commandList;
    }

    public List<ItemStack> getItemList() {
        return itemList;
    }

    public void addCommand(String command) {
        getCommandList().add(command);
    }

    public void removeCommand(String command) {
        getCommandList().remove(command);
    }

    public void addItem(ItemStack itemStack) {
        getItemList().add(itemStack);
    }
}