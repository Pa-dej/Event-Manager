package me.padej.eventmanager.gui.spleef.arena;

import me.padej.eventmanager.utils.FillRegion;
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

public class SpleefFillArenaGUI implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§8SpleefArena§7/§8§nЗаполнение арены");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        gui.setItem(2, createItem(Material.CHEST_MINECART, "§bУдалить все", "§7Очистить всю арену"));
        gui.setItem(3, createItem(Material.RED_DYE, "§bR = 6", "§7Заполнить окружность с R = 6 снегом"));
        gui.setItem(4, createItem(Material.ORANGE_DYE, "§bR = 11", "§7Заполнить окружность с R = 11 снегом"));
        gui.setItem(5, createItem(Material.YELLOW_DYE, "§bR = 17", "§7Заполнить окружность с R = 17  снегом"));
        gui.setItem(6, createItem(Material.LIME_DYE, "§bR = 35", "§7Заполнить окружность с R = 35  снегом"));
        gui.setItem(7, createEmptyNamedItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§8SpleefArena§7/§8§nЗаполнение арены") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(player.getGameMode() != GameMode.SPECTATOR);

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case CHEST_MINECART:
                        FillRegion.fillRegion(833, 87, -687, 902, 87, -618, Material.AIR);
                        break;
                    case RED_DYE:
                        FillRegion.fillRegion(863, 87, -647, 873, 87, -657, Material.SNOW_BLOCK);
                        break;
                    case ORANGE_DYE:
                        FillRegion.fillRegion(858, 87, -642, 878, 87, -662, Material.SNOW_BLOCK);
                        break;
                    case YELLOW_DYE:
                        FillRegion.fillRegion(853, 87, -636, 883, 87, -667, Material.SNOW_BLOCK);
                        break;
                    case LIME_DYE:
                        FillRegion.fillRegion(833, 87, -687, 902, 87, -618, Material.SNOW_BLOCK);
                        break;

                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                SpleefArenaGUI.openGUI(player);
            }
        }
    }
}
