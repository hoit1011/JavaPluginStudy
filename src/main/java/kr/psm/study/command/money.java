package kr.psm.study.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class money implements CommandExecutor {
    private Map<Player, Double> playerMoneyMap = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("플레이어만 이 명령어를 사용할 수 있습니다.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            double money = getPlayerMoney(player);
            player.sendMessage("당신이 가진 돈은 " + money + "입니다.");
        } else if (args.length == 2) {
            String action = args[0];
            double amount;
            try {
                amount = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("유효한 숫자를 입력해주세요.");
                return true;
            }
            if (action.equalsIgnoreCase("추가")) {
                addPlayerMoney(player, amount);
                player.sendMessage("돈 " + amount + "을(를) 추가했습니다.");
            } else if (action.equalsIgnoreCase("제거")) {
                removePlayerMoney(player, amount);
                player.sendMessage("돈 " + amount + "을(를) 제거했습니다.");
            } else {
                player.sendMessage("잘못된 동작입니다. 사용법: /돈 [추가/제거] [금액]");
            }
        } else {
            player.sendMessage("잘못된 명령어 사용입니다. 사용법: /돈 [추가/제거] [금액]");
        }
        return true;
    }

    private void savePlayerMoney(Player player, double money) {
        playerMoneyMap.put(player, money);
    }

    private double getPlayerMoney(Player player) {
        return playerMoneyMap.getOrDefault(player, 0.0);
    }

    private void addPlayerMoney(Player player, double amount) {
        double currentMoney = getPlayerMoney(player);
        double newMoney = currentMoney + amount;

        savePlayerMoney(player, newMoney);
    }

    private void removePlayerMoney(Player player, double amount) {
        double currentMoney = getPlayerMoney(player);
        double newMoney = currentMoney - amount;

        savePlayerMoney(player, newMoney);
    }
}
