package me.padej.eventmanager.gui;

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

public class ParkourDuelGUI implements Listener {

    private final JavaPlugin plugin;
    private boolean countdownActive = false;

    public ParkourDuelGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§5Менеджер ивентов§7/§e§nParkourDuel");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.YELLOW_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(3, createItem(Material.LIME_DYE, "§aВключить плагин", "§7Включить методы плагина"));
        gui.setItem(4, createItem(Material.FIREWORK_ROCKET, "§eНачать", "§7Start ParkourDuel"));
        gui.setItem(5, createItem(Material.RED_DYE, "§cВыключить плагин", "§7Выключить методы плагина"));
        gui.setItem(6, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.YELLOW_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§5Менеджер ивентов§7/§e§nParkourDuel") && !event.isCancelled()) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);

                switch (clickedItem.getType()) {
                    case FIREWORK_ROCKET:
                        start(player);
                        break;
                    case LIME_DYE:
                        player.performCommand("parkourutils enable");
                        break;
                    case RED_DYE:
                        player.performCommand("parkourutils disable");
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                MainGUI.openGUI(player);
            }
        }
    }

    private void start(Player player) {
        if (countdownActive) {
            player.sendActionBar("§cОтсчет уже активен.");
            return;
        }

        countdownActive = true;
        player.sendActionBar("§eОтсчет начинается...");

        new BukkitRunnable() {
            int countdown = 5;

            @Override
            public void run() {
                if (countdown > 0) {
                    // Бродкаст отсчета в радиусе 20 блоков
                    for (Player nearbyPlayer : player.getWorld().getPlayers()) {
                        if (nearbyPlayer.getLocation().distance(player.getLocation()) <= 20) {
                            nearbyPlayer.sendTitle("§e" + countdown, "", 10, 10, 10);
                            nearbyPlayer.playSound(nearbyPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                        }
                    }
                    countdown--;
                } else {
                    // Бродкаст сообщения о старте в радиусе 20 блоков
                    for (Player nearbyPlayer : player.getWorld().getPlayers()) {
                        if (nearbyPlayer.getLocation().distance(player.getLocation()) <= 20) {
                            nearbyPlayer.sendTitle("§aВперед!", "", 10, 40, 10);
                            nearbyPlayer.playSound(nearbyPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        }
                    }
                    countdownActive = false;
                    cancel();
                    activateBarrier(player.getLocation());
                }
            }
        }.runTaskTimer(plugin, 0, 20); // Запуск каждую секунду (20 тиков)
    }

    private void activateBarrier(Location location) {
        World world = location.getWorld();
        int x1 = 1189;
        int y1 = 84;
        int z1 = -805;
        int x2 = 1171;
        int y2 =  84;
        int z2 = -806;

        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                for (int z = Math.min(z1, z2); z <= Math.max(z1, z2); z++) {
                    Block block = world.getBlockAt(x, y, z);
                    block.setType(Material.AIR);
                }
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                        for (int z = Math.min(z1, z2); z <= Math.max(z1, z2); z++) {
                            Block block = world.getBlockAt(x, y, z);
                            block.setType(Material.BARRIER);
                        }
                    }
                }
            }
        }.runTaskLater(plugin, 20 * 2); // Замена через 20 секунд (20 тиков * 20 секунд)
    }
}
