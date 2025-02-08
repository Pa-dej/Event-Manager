package me.padej.eventmanager.gui.sumo;

import me.padej.eventmanager.gui.MainGUI;
import me.padej.eventmanager.gui.sumo.stp.StpGUI;
import me.padej.eventmanager.utils.CountdownUtils;
import me.padej.eventmanager.utils.FillRegion;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static me.padej.eventmanager.utils.ItemUtils.createEmptyNamedItem;
import static me.padej.eventmanager.utils.ItemUtils.createItem;

public class SumoGUI implements Listener {

    private boolean countdownActive = false;
    private final JavaPlugin plugin;
    public SumoGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§5Менеджер ивентов§7/§a§nSumo");

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
        if (event.getView().getTitle().equals("§5Менеджер ивентов§7/§a§nSumo") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

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
            updatePlatform();
            return null;
        });
    }

    void updatePlatform() {
        fillAir();

        new BukkitRunnable() {
            @Override
            public void run() {
                fillBarrier();
            }
        }.runTaskLater(plugin, 20); // Запуск через 1 секунду (20 тиков)
    }

    void fillAir() {
        FillRegion.fillRegion(147, 143, -555, 159, 143, -543, Material.AIR);
    }

    void fillBarrier() {
        FillRegion.fillRegion(147, 143, -555, 159, 143, -543, Material.BARRIER);
    }
}
