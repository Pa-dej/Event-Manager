package me.padej.eventmanager.gui.spleef;

import me.padej.eventmanager.gui.MainGUI;
import me.padej.eventmanager.gui.spleef.arena.SpleefArenaGUI;
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

public class SpleefMainGUI implements Listener {

    public SpleefMainGUI(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§5Менеджер ивентов§7/§b§nSpleef");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        gui.setItem(2, createItem(Material.IRON_SHOVEL, "§bSpleefArena", "§7Настройка сплиф арены"));
        gui.setItem(3, createItem(Material.POWDER_SNOW_BUCKET, "§bSpleefUtils", "§7Настройка плагина SpleefUtils"));
        gui.setItem(4, createItem(Material.FIREWORK_ROCKET, "§bНачать", "§7Start Spleef"));
        gui.setItem(5, createItem(Material.OAK_DOOR, "§bОткрыть", "§7Открывает проход на арену"));
        gui.setItem(6, createItem(Material.IRON_DOOR, "§bЗакрыть", "§7Закрывает проход на арену"));
        gui.setItem(7, createEmptyNamedItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§5Менеджер ивентов§7/§b§nSpleef") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case IRON_SHOVEL:
                        SpleefArenaGUI.openGUI(player);
                        break;
                    case POWDER_SNOW_BUCKET:
                        SpleefUtilsGUI.openGUI(player);
                        break;
                    case OAK_DOOR:
                        openDoor();
                        break;
                    case IRON_DOOR:
                        closeDoor();
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
            fillSnowBlock();
            return null;
        });
    }

    void fillSnowBlock() {
        FillRegion.fillRegion(833, 87, -687, 902, 87, -618, Material.SNOW_BLOCK);
    }

    void openDoor() {
        FillRegion.fillRegion(832, 102, -655, 832, 107, -650, Material.AIR);
    }

    void closeDoor() {
        FillRegion.fillRegion(832, 102, -655, 832, 107, -650, Material.BLUE_STAINED_GLASS);
    }
}
