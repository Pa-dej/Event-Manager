package me.padej.eventmanager.gui;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Strider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static me.padej.eventmanager.utils.ItemUtils.*;

public class LavaRaceGUI implements Listener {

    private final JavaPlugin plugin;
    private boolean countdownActive = false;

    public LavaRaceGUI(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "§5Менеджер ивентов§7/§6§nLavaRace");

        // Строка 1
        gui.setItem(0, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(1, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(2, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(3, createItem(Material.STRIDER_SPAWN_EGG, "§6Создать лавомерку", "§7Создает лавомерку с седлом"));
        gui.setItem(4, createItem(Material.FIREWORK_ROCKET, "§6Начать", "§7Start LavaRace"));
        gui.setItem(5, createItem(Material.WARPED_FUNGUS_ON_A_STICK, "§6Выдать удочки", "§7Выдать удочки всем игрокам в радиусе 20 блоков"));
        gui.setItem(6, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));
        gui.setItem(7, createEmptyNamedItem(Material.ORANGE_STAINED_GLASS_PANE));
        gui.setItem(8, createEmptyNamedItem(Material.BLACK_STAINED_GLASS_PANE));

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§5Менеджер ивентов§7/§6§nLavaRace") && !event.isCancelled()) {
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
                    case WARPED_FUNGUS_ON_A_STICK:
                        giveWarpedFungusOnAStick(player);
                        break;
                    case STRIDER_SPAWN_EGG:
                        spawnStrider(player);
                        break;
                }
            } else {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
                MainGUI.openGUI(player);
            }
        }
    }

    private void spawnStrider(Player player) {
        Location playerLocation = player.getLocation();

        Entity strider = player.getWorld().spawnEntity(playerLocation, EntityType.STRIDER);
        ((Strider) strider).setSaddle(true);

        player.sendActionBar("§aСоздана лавомерка!");
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
                            nearbyPlayer.sendTitle("§6" + countdown, "", 10, 10, 10);
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
        int x1 = 897;
        int y1 = 96;
        int z1 = -477;
        int x2 = 909;
        int y2 =  96;
        int z2 = -477;

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

    private boolean isInRadius(double centerX, double centerY, double centerZ, double targetX, double targetY, double targetZ, double radius) {
        // Проверка, находится ли точка в указанном радиусе от центра
        double distanceSquared = Math.pow(centerX - targetX, 2) + Math.pow(centerY - targetY, 2) + Math.pow(centerZ - targetZ, 2);
        double radiusSquared = Math.pow(radius, 2);
        return distanceSquared <= radiusSquared;
    }

    private void giveWarpedFungusOnAStick(Player player) {
        // Радиус в блоках
        int radius = 20;

        // Получение координат игрока
        double playerX = player.getLocation().getX();
        double playerY = player.getLocation().getY();
        double playerZ = player.getLocation().getZ();

        // Получение всех сущностей в радиусе
        for (org.bukkit.entity.Entity nearbyEntity : player.getNearbyEntities(radius, radius, radius)) {
            if (nearbyEntity instanceof Player) {
                Player nearbyPlayer = (Player) nearbyEntity;
                if (nearbyPlayer != player) {
                    // Получение координат игрока в радиусе
                    double nearbyX = nearbyPlayer.getLocation().getX();
                    double nearbyY = nearbyPlayer.getLocation().getY();
                    double nearbyZ = nearbyPlayer.getLocation().getZ();

                    // Проверка, находится ли игрок в указанном радиусе
                    if (isInRadius(playerX, playerY, playerZ, nearbyX, nearbyY, nearbyZ, radius)) {
                        ItemStack fishingRod = createItem(Material.WARPED_FUNGUS_ON_A_STICK, "§7Удочка", "§7Для LavaRace");
                        nearbyPlayer.getInventory().addItem(fishingRod);
                        nearbyPlayer.sendActionBar("§aВам выдана удочка для LavaRace!");
                    }
                }
            }
        }
        player.sendActionBar("§aУдочки успешно выданы");
    }
}
