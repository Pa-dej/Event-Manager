package me.padej.eventmanager.gui.sumoteam;

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

public class DivisionGUI implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§8SumoTeam§7/§8§nDivision");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.YELLOW_STAINED_GLASS_PANE));
        gui.setItem(2, createItem(Material.CHEST_MINECART, "§eСохранить", "§7Сохранить состав игроков"));
        gui.setItem(3, createItem(Material.HOPPER_MINECART, "§eЗагрузить", "§7Загрузить состав игроков"));
        gui.setItem(4, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(5, createItem(Material.NETHERITE_SCRAP, "§eDuo", "§7Вид распределения игроков: duo"));
        gui.setItem(6, createItem(Material.NETHERITE_INGOT, "§eMax", "§7Вид распределения игроков: max"));
        gui.setItem(7, createEmptyNamedItem(Material.YELLOW_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§8SumoTeam§7/§8§nDivision") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(player.getGameMode() != GameMode.SPECTATOR);

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case CHEST_MINECART:
                        player.performCommand("st division save");
                        break;
                    case HOPPER_MINECART:
                        player.performCommand("st division load");
                        break;
                    case REPEATER:
                        player.performCommand("st division duo");
                        break;
                    case NETHERITE_INGOT:
                        player.performCommand("st division max");
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                SumoTeamGUI.openGUI(player);
            }
        }
    }
}
