package me.padej.eventmanager.gui.sumo.stp.arena;

import me.padej.eventmanager.gui.sumo.stp.StpPasteGUI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class sumoXmasGUI implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 36, "§8Вставка арен§7/§8§nsumo_xmas");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(3, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(4, createItem(Material.CHEST_MINECART, "§6*", "§7Вставка всей арены sumo_xmas"));
        gui.setItem(5, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(6, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        // Строка 2
        gui.setItem(9, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(10, createItem(Material.WHITE_DYE, "§6ne1-2", "§7Вставка арены sumo_xmas"));
        gui.setItem(11, createItem(Material.LIGHT_GRAY_DYE, "§6ne2", "§7Вставка арены sumo_xmas"));
        gui.setItem(12, createItem(Material.GRAY_DYE, "§6ne2-3", "§7Вставка арены sumo_xmas"));
        gui.setItem(13, createItem(Material.RED_DYE, "§6ne3", "§7Вставка арены sumo_xmas"));
        gui.setItem(14, createItem(Material.ORANGE_DYE, "§6ne3-4", "§7Вставка арены sumo_xmas"));
        gui.setItem(15, createItem(Material.YELLOW_DYE, "§6ne4", "§7Вставка арены sumo_xmas"));
        gui.setItem(16, createItem(Material.LIME_DYE, "§6ne4-5", "§7Вставка арены sumo_xmas"));
        gui.setItem(17, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));

        // Строка 3
        gui.setItem(18, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(19, createItem(Material.GREEN_DYE, "§6ne5", "§7Вставка арены sumo_xmas"));
        gui.setItem(20, createItem(Material.CYAN_DYE, "§6ne5-6", "§7Вставка арены sumo_xmas"));
        gui.setItem(21, createItem(Material.LIGHT_BLUE_DYE, "§6ne6", "§7Вставка арены sumo_xmas"));
        gui.setItem(22, createItem(Material.BLUE_DYE, "§6ne6-7", "§7Вставка арены sumo_xmas"));
        gui.setItem(23, createItem(Material.PURPLE_DYE, "§6ne7", "§7Вставка арены sumo_xmas"));
        gui.setItem(24, createItem(Material.MAGENTA_DYE, "§6ne7-8", "§7Вставка арены sumo_xmas"));
        gui.setItem(25, createItem(Material.PINK_DYE, "§6ne8", "§7Вставка арены sumo_xmas"));
        gui.setItem(26, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));

        // Строка 4
        gui.setItem(27, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(28, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(29, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(30, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(31, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(32, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(33, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(34, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(35, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§8Вставка арен§7/§8§nsumo_xmas") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(player.getGameMode() != GameMode.SPECTATOR);

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case CHEST_MINECART:
                        player.performCommand("spt paste sumo_xmas *");
                        break;
                    case WHITE_DYE:
                        player.performCommand("spt paste sumo_xmas ne1-2");
                        break;
                    case LIGHT_GRAY_DYE:
                        player.performCommand("spt paste sumo_xmas ne2");
                        break;
                    case GRAY_DYE:
                        player.performCommand("spt paste sumo_xmas ne2-3");
                        break;
                    case RED_DYE:
                        player.performCommand("spt paste sumo_xmas ne3");
                        break;
                    case ORANGE_DYE:
                        player.performCommand("spt paste sumo_xmas ne3-4");
                        break;
                    case YELLOW_DYE:
                        player.performCommand("spt paste sumo_xmas ne4");
                        break;
                    case LIME_DYE:
                        player.performCommand("spt paste sumo_xmas ne4-5");
                        break;
                    case GREEN_DYE:
                        player.performCommand("spt paste sumo_xmas ne5");
                        break;
                    case CYAN_DYE:
                        player.performCommand("spt paste sumo_xmas ne5-6");
                        break;
                    case LIGHT_BLUE_DYE:
                        player.performCommand("spt paste sumo_xmas ne6");
                        break;
                    case BLUE_DYE:
                        player.performCommand("spt paste sumo_xmas ne6-7");
                        break;
                    case PURPLE_DYE:
                        player.performCommand("spt paste sumo_xmas ne7");
                        break;
                    case MAGENTA_DYE:
                        player.performCommand("spt paste sumo_xmas ne7-8");
                        break;
                    case PINK_DYE:
                        player.performCommand("spt paste sumo_xmas ne8");
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                StpPasteGUI.openGUI(player);
            }
        }
    }
}
