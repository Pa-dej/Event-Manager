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

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class sumoLotusGUI implements Listener {
    private final JavaPlugin plugin;
    public sumoLotusGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§6Вставка арен§7/§6§nsumo_lotus");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(1, createItem(Material.CHEST_MINECART, "§6*", "§7Вставка всей арены sumo_lotus"));
        gui.setItem(2, createItem(Material.RED_DYE, "§6L1-2", "§7Вставка арены sumo_lotus"));
        gui.setItem(3, createItem(Material.ORANGE_DYE, "§6L2", "§7Вставка арены sumo_lotus"));
        gui.setItem(4, createItem(Material.YELLOW_DYE, "§6L2-3", "§7Вставка арены sumo_lotus"));
        gui.setItem(5, createItem(Material.LIME_DYE, "§6L3", "§7Вставка арены sumo_lotus"));
        gui.setItem(6, createItem(Material.GREEN_DYE, "§6L3-4", "§7Вставка арены sumo_lotus"));
        gui.setItem(7, createItem(Material.CYAN_DYE, "§6L4", "§7Вставка арены sumo_lotus"));
        gui.setItem(8, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§6Вставка арен§7/§6§nsumo_lotus") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case CHEST_MINECART:
                        player.performCommand("stp paste sumo_lotus *");
                        break;
                    case RED_DYE:
                        player.performCommand("stp paste sumo_lotus l1-2");
                        break;
                    case ORANGE_DYE:
                        player.performCommand("stp paste sumo_lotus l2");
                        break;
                    case YELLOW_DYE:
                        player.performCommand("stp paste sumo_lotus l2-3");
                        break;
                    case LIME_DYE:
                        player.performCommand("stp paste sumo_lotus l3");
                        break;
                    case GREEN_DYE:
                        player.performCommand("stp paste sumo_lotus l3-4");
                        break;
                    case CYAN_DYE:
                        player.performCommand("stp paste sumo_lotus l4");
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                StpPasteGUI.openGUI(player);
            }
        }
    }
}
