package me.padej.eventmanager.gui.sumo.stp.arena;

import me.padej.eventmanager.gui.sumo.stp.StpPasteGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static me.padej.eventmanager.utils.ItemUtils.*;
import static me.padej.eventmanager.utils.ItemUtils.createItemWithoutAttributes;

public class sumoHalloweenGUI implements Listener {
    private final JavaPlugin plugin;
    public sumoHalloweenGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 27, "§6Вставка арен§7/§6§nsumo_halloween");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(3, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(4, createItem(Material.CHEST_MINECART, "§6*", "§7Вставка всей арены sumo_halloween"));
        gui.setItem(5, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(6, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        // Строка 2
        gui.setItem(9, createItem(Material.RED_DYE, "§6hl1-2", "§7Вставка арены sumo_halloween"));
        gui.setItem(10, createItem(Material.ORANGE_DYE, "§6 hl2", "§7Вставка арены sumo_halloween"));
        gui.setItem(11, createItem(Material.YELLOW_DYE, "§6hl2-3", "§7Вставка арены sumo_halloween"));
        gui.setItem(12, createItem(Material.LIME_DYE, "§6hl3", "§7Вставка арены sumo_halloween"));
        gui.setItem(13, createItem(Material.GREEN_DYE, "§6hl3-4", "§7Вставка арены sumo_halloween"));
        gui.setItem(14, createItem(Material.CYAN_DYE, "§6hl4", "§7Вставка арены sumo_halloween"));
        gui.setItem(15, createItem(Material.LIGHT_BLUE_DYE, "§6hl4-5", "§7Вставка арены sumo_halloween"));
        gui.setItem(16, createItem(Material.BLUE_DYE, "§6hl5", "§7Вставка арены sumo_halloween"));
        gui.setItem(17, createItem(Material.PURPLE_DYE, "§6hl5-6", "§7Вставка арены sumo_halloween"));

        // Строка 3
        gui.setItem(18, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(19, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(20, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(21, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(22, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(23, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(24, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(25, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(26, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§6Вставка арен§7/§6§nsumo_halloween") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case CHEST_MINECART:
                        player.performCommand("stp paste sumo_halloween *");
                        break;
                    case RED_DYE:
                        player.performCommand("stp paste sumo_halloween hl1-2");
                        break;
                    case ORANGE_DYE:
                        player.performCommand("stp paste sumo_halloween hl2");
                        break;
                    case YELLOW_DYE:
                        player.performCommand("stp paste sumo_halloween hl2-3");
                        break;
                    case LIME_DYE:
                        player.performCommand("stp paste sumo_halloween hl3");
                        break;
                    case GREEN_DYE:
                        player.performCommand("stp paste sumo_halloween hl3-4");
                        break;
                    case CYAN_DYE:
                        player.performCommand("stp paste sumo_halloween hl4");
                        break;
                    case LIGHT_BLUE_DYE:
                        player.performCommand("stp paste sumo_halloween hl4-5");
                        break;
                    case BLUE_DYE:
                        player.performCommand("stp paste sumo_halloween hl5");
                        break;
                    case PURPLE_DYE:
                        player.performCommand("stp paste sumo_halloween hl5-6");
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                StpPasteGUI.openGUI(player);
            }
        }
    }
}
