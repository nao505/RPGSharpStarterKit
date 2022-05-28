package com.binggre.rpgsharpstarterkit.command;

import com.binggre.rpgsharpstarterkit.guis.StarterKitGUI;
import com.binggre.rpgsharpstarterkit.objects.StarterKit;
import com.binggre.rpgsharpstarterkit.objects.StarterKitLoader;
import com.hj.rpgsharp.rpg.apis.rpgsharp.RPGPlayerAPI;
import com.hj.rpgsharp.rpg.apis.utils.MetadataUtil;
import com.hj.rpgsharp.rpg.objects.RPGPlayer;
import com.hj.rpgsharp.rpg.plugins.character.objects.JobLoader;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class StarterKitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!sender.isOp()) return false;

        if (args.length == 0) {
            sender.sendMessage("");
            sender.sendMessage("/기본아이템 아이템설정 <직업명> - 해당직업의 기본 아이템을 설정합니다.");
            sender.sendMessage("/기본아이템 명령어추가 <명령어> <직업명> - 직업의 기본 명령어를 설정합니다.");
            sender.sendMessage("/기본아이템 명령어삭제 <명령어> <직업명> - 직업의 기본 명령어를 제거합니다.");
            sender.sendMessage("");
            sender.sendMessage("/기본아이템 삭제 <직업명> - 해당 직업의 기본 아이템을 제거합니다.");
            sender.sendMessage("/기본아이템 지급 <직업명> <닉네임> - 기본 아이템을 지급합니다.");
            sender.sendMessage("");
            return false;
        }
        switch (args[0]) {
            case "아이템설정": {
                if (args.length != 2) {
                    sender.sendMessage("/기본아이템 아이템설정 <직업명> - 해당 직업의 기본 아이템을 설정합니다.");
                    break;
                }
                Player player = (Player) sender;
                String jobName = space(1, args);
                if (!JobLoader.getColoredJobMap().containsKey(jobName)) {
                    sender.sendMessage("존재하지 않는 직업입니다.");
                    break;
                }
                MetadataUtil.setMetadata(player, "StarterKit", jobName);
                if (!StarterKitLoader.containsKey(jobName)) {
                    Inventory inventory = Bukkit.createInventory(null, (6 * 9), "기본 아이템 설정");
                    player.openInventory(inventory);
                    break;
                }
                StarterKitGUI.openInventory(player, jobName);
                break;
            }
            case "명령어추가": {
                if (args.length != 3) {
                    sender.sendMessage("/기본아이템 명령어추가 <명령어> <직업명> - 직업의 기본 명령어를 설정합니다.");
                    break;
                }
                String jobName = space(2, args);
                if (!StarterKitLoader.containsKey(jobName)) {
                    sender.sendMessage("먼저 아이템을 설정해 주세요.");
                    sender.sendMessage("/기본아이템 아이템설정 <직업>");
                    break;
                }
                if (!JobLoader.getColoredJobMap().containsKey(jobName)) {
                    sender.sendMessage("존재하지 않는 직업입니다.");
                    break;
                }
                String command = args[1];
                StarterKit kit = StarterKitLoader.get(jobName);
                kit.addCommand(command);
                sender.sendMessage(jobName + "직업에 '" + command + "' 명령어를 추가했습니다.");
                break;
            }

            case "명령어삭제": {
                if (args.length != 3) {
                    sender.sendMessage("/기본아이템 명령어삭제 <명령어> <직업명> - 직업의 기본 명령어를 제거합니다.");
                    break;
                }
                String jobName = space(2, args);
                if (!StarterKitLoader.containsKey(jobName)) {
                    sender.sendMessage("먼저 아이템을 설정해 주세요.");
                    sender.sendMessage("/기본아이템 설정 <직업>");
                    break;
                }
                if (!JobLoader.getColoredJobMap().containsKey(jobName)) {
                    sender.sendMessage("존재하지 않는 직업입니다.");
                    break;
                }
                String command = args[1];
                StarterKit kit = StarterKitLoader.get(jobName);
                kit.removeCommand(command);
                sender.sendMessage(jobName + "직업에 '" + command + "' 명령어를 제거했습니다.");
                break;
            }
            case "삭제": {
                if (args.length != 2) {
                    sender.sendMessage("/기본아이템 삭제 <직업명> - 해당 직업의 기본 아이템을 제거합니다.");
                    break;
                }
                String jobName = space(1, args);
                if (!StarterKitLoader.containsKey(jobName)) {
                    sender.sendMessage("존재하지 않는 킷 입니다.");
                    break;
                }
                StarterKitLoader.remove(jobName);
                sender.sendMessage(jobName + "직업의 기본 아이템, 명령어를 제거했습니다.");
                break;
            }
            case "지급": {
                if (args.length != 3) {
                    sender.sendMessage("/기본아이템 지급 <닉네임> <직업명> - 기본 아이템을 지급합니다.");
                    break;
                }
                RPGPlayer rpgPlayer = RPGPlayerAPI.getInstance().getRPGPlayer((Object) args[1]);
                if (rpgPlayer == null) {
                    sender.sendMessage("접속중이지 않은 플레이어입니다.");
                    break;
                }
                String jobName = space(2, args);
                if (!StarterKitLoader.containsKey(jobName)) {
                    sender.sendMessage("존재하지 않는 킷 입니다.");
                    break;
                }
                StarterKit kit = StarterKitLoader.get(jobName);
                Player target = rpgPlayer.getPlayer();
                kit.getCommandList().forEach(command -> runCommand(target, command, true));
                kit.getItemList().forEach(item -> target.getInventory().addItem(item));
                sender.sendMessage(target.getName() + "님에게 기본 아이템을 지급하였습니다.");
                break;
            }
        }
        return false;
    }

    private static String space(int argNum, String[] args) {
        return String.join(" ", Arrays.copyOfRange(args, argNum, args.length));
    }

    public static void runCommand(Player player, String command, boolean admin) {
        if (admin && !player.isOp()) {
            player.setOp(true);
            Bukkit.dispatchCommand(player, command);
            player.setOp(false);
        } else {
            Bukkit.dispatchCommand(player, command);
        }
    }
}
