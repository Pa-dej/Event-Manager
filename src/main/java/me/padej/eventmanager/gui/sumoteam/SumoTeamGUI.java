package me.padej.eventmanager.gui.sumoteam;

import me.padej.eventmanager.gui.MainGUI;
import me.padej.eventmanager.gui.sumoteam.DivisionGUI;
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

public class SumoTeamGUI implements Listener {

    private final JavaPlugin plugin;
    public SumoTeamGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§5Менеджер ивентов§7/§e§nSumoTeam");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.YELLOW_STAINED_GLASS_PANE));
        gui.setItem(2, createItem(Material.FIREWORK_ROCKET, "§eНачать", "§7Start SumoTeam"));
        gui.setItem(3, createItem(Material.BARRIER, "§eОстановить", "§7Stop SumoTeam"));
        gui.setItem(4, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(5, createItem(Material.COMPARATOR, "§eРаспределить", "§7Настройка распределения игроков"));
        gui.setItem(6, createItem(Material.MINECART, "§eВернуть", "§7Вернуть игроков По умолчанию"));
        gui.setItem(7, createEmptyNamedItem(Material.YELLOW_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§5Менеджер ивентов§7/§e§nSumoTeam") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case FIREWORK_ROCKET:
                        player.performCommand("st start");
                        break;
                    case BARRIER:
                        player.performCommand("st stop");
                        break;
                    case COMPARATOR:
                        DivisionGUI.openGUI(player);
                        break;
                    case MINECART:
                        player.performCommand("st return");
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                MainGUI.openGUI(player);
            }
        }
    }
}
