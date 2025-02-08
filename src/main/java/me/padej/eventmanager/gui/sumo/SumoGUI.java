package me.padej.eventmanager.gui.sumo;

import me.padej.eventmanager.gui.MainGUI;
import me.padej.eventmanager.gui.sumo.stp.StpGUI;
import me.padej.eventmanager.utils.BarrierUpdater;
import me.padej.eventmanager.utils.CountdownUtils;
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
import org.bukkit.scheduler.BukkitRunnable;

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class SumoGUI extends BarrierUpdater implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§8Менеджер ивентов§7/§8§nSumo");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.LIME_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(3, createItem(Material.BAMBOO, "§aSumoUtils", "§7Настройка плагина SumoUtils"));
        gui.setItem(4, createItem(Material.FIREWORK_ROCKET, "§aНачать", "§7Start Sumo"));
        gui.setItem(5, createItem(Material.WOODEN_AXE, "§6StagePlatform", "§7Настройка плагина StagePlatform"));
        gui.setItem(6, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.LIME_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§8Менеджер ивентов§7/§8§nSumo") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(player.getGameMode() != GameMode.SPECTATOR);

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case BAMBOO:
                        SuGUI.openGUI(player);
                        break;
                    case WOODEN_AXE:
                        StpGUI.openGUI(player);
                        break;
                    case FIREWORK_ROCKET:
                        startCountdown(player);
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                MainGUI.openGUI(player);
            }
        }
    }

    public void startCountdown(Player whoStarted) {
        CountdownUtils.startCountdown(whoStarted, 5, () -> {
            updatePlatform(147, 143, -555, 159, 143, -543);
            return null;
        });
    }

    @Override
    public void updatePlatform(int x1, int y1, int z1, int x2, int y2, int z2) {
        super.updatePlatform(x1, y1, z1, x2, y2, z2);
    }
}
