package me.padej.eventmanager.gui.sumo.stp;

import me.padej.eventmanager.gui.sumo.stp.arena.*;
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

public class StpPasteGUI implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 36, "§8StagePlatform§7/§8§nВставка арен");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(3, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(4, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(5, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(6, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));

        // Строка 2
        gui.setItem(9, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(10, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(11, createItem(Material.NETHERITE_INGOT, "§6sumo_classic", "§7Настройка вставки арены: §6sumo_classic"));
        gui.setItem(12, createItem(Material.PUMPKIN_PIE, "§6sumo_halloween", "§7Настройка вставки арены: §6sumo_halloween"));
        gui.setItem(13, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(14, createItem(Material.NETHER_STAR, "§6sumo_star", "§7Настройка вставки арены: §6sumo_star"));
        gui.setItem(15, createItem(Material.PINK_PETALS, "§6sumo_Pink", "§7Настройка вставки арены: §6sumo_Pink"));
        gui.setItem(16, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(17, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));

        // Строка 3
        gui.setItem(18, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(19, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(20, createItem(Material.PACKED_ICE, "§6sumo_ice", "§7Настройка вставки арены: §6sumo_ice"));
        gui.setItem(21, createItem(Material.SPRUCE_SAPLING, "§6sumo_xmas", "§7Настройка вставки арены: §6sumo_xmas"));
        gui.setItem(22, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(23, createItem(Material.END_CRYSTAL, "§6sumo_starP", "§7Настройка вставки арены: §6sumo_starP"));
        gui.setItem(24, createItem(Material.CHERRY_SAPLING, "§6sumo_lotus", "§7Настройка вставки арены: §6sumo_lotus"));
        gui.setItem(25, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(26, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));

        // Строка 4
        gui.setItem(27, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(28, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(29, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(30, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(31, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(32, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(33, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(34, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(35, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§8StagePlatform§7/§8§nВставка арен") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(player.getGameMode() != GameMode.SPECTATOR);

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case NETHERITE_INGOT:
                        sumoClassicGUI.openGUI(player);
                        break;
                    case PUMPKIN_PIE:
                        sumoHalloweenGUI.openGUI(player);
                        break;
                    case NETHER_STAR:
                        sumoStarGUI.openGUI(player);
                        break;
                    case PINK_PETALS:
                        sumoPinkGUI.openGUI(player);
                        break;
                    case PACKED_ICE:
                        sumoIceGUI.openGUI(player);
                        break;
                    case SPRUCE_SAPLING:
                        sumoXmasGUI.openGUI(player);
                        break;
                    case END_CRYSTAL:
                        sumoStarPGUI.openGUI(player);
                        break;
                    case CHERRY_SAPLING:
                        sumoLotusGUI.openGUI(player);
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                StpGUI.openGUI(player);
            }
        }
    }
}
