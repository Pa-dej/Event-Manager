package me.padej.eventmanager.gui.sumo.stp;

import me.padej.eventmanager.gui.sumo.stp.arena.*;
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

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class StpPasteGUI implements Listener {

    private final JavaPlugin plugin;
    public StpPasteGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§6StagePlatform§7/§6§nВставка арен");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(2, createItem(Material.NETHERITE_INGOT, "§6sumo_classic", "§7Настройка вставки арены: sumo_classic"));
        gui.setItem(3, createItem(Material.PUMPKIN_PIE, "§6sumo_halloween", "§7Настройка вставки арены: sumo_halloween"));
        gui.setItem(4, createItem(Material.SNOWBALL, "§6sumo_ice", "§7Настройка вставки арены: sumo_ice"));
        gui.setItem(5, createItem(Material.PINK_PETALS, "§6sumo_lotus", "§7Настройка вставки арены: sumo_lotus"));
        gui.setItem(6, createItem(Material.SWEET_BERRIES, "§6sumo_xmas", "§7Настройка вставки арены: sumo_xmas"));
        gui.setItem(7, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§6StagePlatform§7/§6§nВставка арен") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

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
                    case SNOWBALL:
                        sumoIceGUI.openGUI(player);
                        break;
                    case PINK_PETALS:
                        sumoLotusGUI.openGUI(player);
                        break;
                    case SWEET_BERRIES:
                        sumoXmasGUI.openGUI(player);
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                StpGUI.openGUI(player);
            }
        }
    }
}
