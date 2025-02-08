package me.padej.eventmanager.main;

import me.padej.eventmanager.gui.MainGUI;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.padej.eventmanager.messages.sendRandomMessage;

public class onCommand implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("gm");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("gm")) {
            completions.add("0");
            completions.add("2");
            completions.add("3");
        }

        return completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (player.hasPermission("sumoutils.event")) {
            if (args.length == 2 && args[0].equalsIgnoreCase("gm")) {
                handleGameModeCommand(player, args[1]);
            } else {
                MainGUI.openGUI(player);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
        }

        return true;
    }

    private void handleGameModeCommand(Player player, String mode) {
        int gameMode;
        try {
            gameMode = Integer.parseInt(mode);
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Invalid game mode. Please use a number (0, 1, 2, or 3).");
            return;
        }

        switch (gameMode) {
            case 0:
            case 2:
            case 3:
                player.setGameMode(GameMode.getByValue(gameMode));
                player.sendActionBar(ChatColor.GREEN + "Game mode set to " + gameMode);
                break;
            case 1:
                sendRandomMessage(player);
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_LOCKED, 1, 1);
                break;
            default:
                player.sendActionBar(ChatColor.RED + "Invalid game mode. Please use 0, 1, 2, or 3.");
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_LOCKED, 1, 1);
        }
    }
}
