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

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class sumoPinkGUI implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§6Вставка арен§7/§6§nsumo_pink");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(2, createItem(Material.CHEST_MINECART, "§6*", "§7Вставка всей арены sumo_pink"));
        gui.setItem(3, createItem(Material.LIME_DYE, "§6Pink2", "§7Вставка арены sumo_pink"));
        gui.setItem(4, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(5, createItem(Material.ORANGE_DYE, "§6Pink3", "§7Вставка арены sumo_pink"));
        gui.setItem(6, createItem(Material.YELLOW_DYE, "§6Pink4", "§7Вставка арены sumo_pink"));
        gui.setItem(7, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§6Вставка арен§7/§6§nsumo_pink") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case CHEST_MINECART:
                        player.performCommand("stp paste sumo_Pink *");
                        break;
                    case LIME_DYE:
                        player.performCommand("stp paste sumo_Pink Pink2");
                        break;
                    case ORANGE_DYE:
                        player.performCommand("stp paste sumo_Pink Pink3");
                        break;
                    case YELLOW_DYE:
                        player.performCommand("stp paste sumo_Pink Pink4");
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                StpPasteGUI.openGUI(player);
            }
        }
    }
}
