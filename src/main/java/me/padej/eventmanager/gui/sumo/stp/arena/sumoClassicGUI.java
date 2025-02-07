package me.padej.eventmanager.gui.sumo.stp.arena;

import me.padej.eventmanager.gui.MainGUI;
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

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class sumoClassicGUI implements Listener {
    private final JavaPlugin plugin;
    public sumoClassicGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§6Вставка арен§7/§6§nsumo_classic");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(2, createItem(Material.CHEST_MINECART, "§6*", "§7Вставка всей арены sumo_classic"));
        gui.setItem(3, createItem(Material.LIME_DYE, "§6green", "§7Вставка арены sumo_classic"));
        gui.setItem(4, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(5, createItem(Material.ORANGE_DYE, "§6orange", "§7Вставка арены sumo_classic"));
        gui.setItem(6, createItem(Material.YELLOW_DYE, "§6yellow", "§7Вставка арены sumo_classic"));
        gui.setItem(7, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§6Вставка арен§7/§6§nsumo_classic") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case CHEST_MINECART:
                        player.performCommand("stp paste sumo_classic *");
                        break;
                    case LIME_DYE:
                        player.performCommand("stp paste sumo_classic green");
                        break;
                    case ORANGE_DYE:
                        player.performCommand("stp paste sumo_classic orange");
                        break;
                    case YELLOW_DYE:
                        player.performCommand("stp paste sumo_classic yellow");
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                StpPasteGUI.openGUI(player);
            }
        }
    }
}
