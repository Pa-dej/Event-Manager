package me.padej.eventmanager.gui.sumo;

import me.padej.eventmanager.gui.MainGUI;
import me.padej.eventmanager.gui.sumo.stp.StpGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class SuGUI implements Listener {

    private final JavaPlugin plugin;
    public SuGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§aSumo§7/§a§nSumoUtils");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.LIME_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(3, createItem(Material.LIME_DYE, "§aВключить плагин", "§7Включить методы плагина"));
        gui.setItem(4, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(5, createItem(Material.RED_DYE, "§cВыключить плагин", "§7Выключить методы плагина"));
        gui.setItem(6, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.LIME_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§aSumo§7/§a§nSumoUtils") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case LIME_DYE:
                        player.performCommand("su enable");
                        break;
                    case RED_DYE:
                        player.performCommand("su disable");
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                SumoGUI.openGUI(player);
            }
        }
    }
}
