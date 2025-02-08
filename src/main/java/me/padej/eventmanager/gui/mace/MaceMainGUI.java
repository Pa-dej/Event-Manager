package me.padej.eventmanager.gui.mace;

import me.padej.eventmanager.gui.MainGUI;
import me.padej.eventmanager.main.EventManager;
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

public class MaceMainGUI implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§8Менеджер ивентов§7/§8§nMace");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(3, createItem(Material.OAK_DOOR, "§bOpen", "§7Открыть"));
        gui.setItem(4, createItem(Material.FIREWORK_ROCKET, "§bStart", "§7Start Mace"));
        gui.setItem(5, createItem(Material.IRON_DOOR, "§bClose", "§7Закрыть"));
        gui.setItem(6, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§8Менеджер ивентов§7/§8§nMace") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(player.getGameMode() != GameMode.SPECTATOR);

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case OAK_DOOR:
                        fillAir();
                        break;
                    case FIREWORK_ROCKET:
                        startCountdown(player);
                        break;
                    case IRON_DOOR:
                        fillOrangeStainedGlass();
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
            return null; // В Java нужно вернуть null, так как лямбда в Kotlin ожидает Unit
        });
    }

    void updatePlatform() {
        fillAir();

        new BukkitRunnable() {
            @Override
            public void run() {
                fillOrangeStainedGlass();
            }
        }.runTaskLater(EventManager.getInstance(), 20); // Запуск через 1 секунду (20 тиков)
    }

    private void fillAir() {
        // Main square
        FillRegion.fillRegion(-411, 138, 6618, -403, 138, 6626, Material.AIR);

        // Corners
        FillRegion.fillRegion(-409, 138, 6617, -405, 138, 6627, Material.AIR);
        FillRegion.fillRegion(-412, 138, 6620, -402, 138, 6624, Material.AIR);
    }

    private void fillOrangeStainedGlass() {
        // Main square
        FillRegion.fillRegion(-411, 138, 6618, -403, 138, 6626, Material.ORANGE_STAINED_GLASS);

        // Corners
        FillRegion.fillRegion(-409, 138, 6617, -405, 138, 6627, Material.ORANGE_STAINED_GLASS);
        FillRegion.fillRegion(-412, 138, 6620, -402, 138, 6624, Material.ORANGE_STAINED_GLASS);
    }
}
